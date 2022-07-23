package com.ashokit.service;

import java.awt.Color;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ashokit.entity.CitizenAppsEntity;
import com.ashokit.entity.CoTriggersEntity;
import com.ashokit.entity.EligibilityDtlsEntity;
import com.ashokit.repository.CitizenAppsRepository;
import com.ashokit.repository.CoTriggersRepository;
import com.ashokit.repository.EligibilityDtlsRepository;
import com.ashokit.utils.EmailUtils;
import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

@Service
public class CoTrigServiceImp implements CoTrgService {

	private static final String PDF_PATH="D:\\JRTP\\Major Project\\pdfs\\";
	@Autowired
	private CoTriggersRepository corepo;

	@Autowired
	private EligibilityDtlsRepository eligrepo;

	@Autowired
	private CitizenAppsRepository cityapprepo;

	@Autowired
	private EmailUtils emailutils;

	// get pending trigger all
	@Override
	public Map<String, Integer> generatenotice() {

		Map<String, Integer> statusmap = new HashMap<>();
		int success = 0;
		int failure = 0;
		List<CoTriggersEntity> pendingtrgs = corepo.findByTrgStatus("Pending");

		ExecutorService exservice = Executors.newFixedThreadPool(10);
		ExecutorCompletionService<Object> pool = new ExecutorCompletionService<>(exservice);
		for (CoTriggersEntity coTrigger : pendingtrgs) {
			pool.submit(new Callable<Object>() {

				@Override
				public Object call() throws Exception {
					try {
						processtrigger(coTrigger);

					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					return null;
				}

			});
		}
		statusmap.put("TOTAL_TRIGGER", pendingtrgs.size());
		statusmap.put("SUCCESS", success);
		statusmap.put("FAILURE", failure);
		return statusmap;
	}

	private void processtrigger(CoTriggersEntity cotrgs) throws Exception {
		// get eligdata into elig detls
		EligibilityDtlsEntity eligdata = eligrepo.findByCaseNum(cotrgs.getCaseNum());
		Optional<CitizenAppsEntity> findbyid = cityapprepo.findById(cotrgs.getCaseNum());

		// geneartepdf
		generatepdf(eligdata, findbyid.get());

		// send pdf file to citizen email
		sendpdf(findbyid.get());

		// update trigger status
		byte[] filedata = new byte[1024];
		FileInputStream fis = new FileInputStream(new File(PDF_PATH+cotrgs.getCaseNum() + ".pdf"));
		fis.read(filedata);
		fis.close();

		cotrgs.setNotice(filedata);
		cotrgs.setTrgStatus("Completed");
		corepo.save(cotrgs);
	}

	private void sendpdf(CitizenAppsEntity app) {
		String subject = "Eligibility Details Info-IHIS";
		String body = "body";
		emailutils.sendmail(app.getEmail(), subject, body, new File(app.getCaseNum() + ".pdf"));
	}

	private void generatepdf(EligibilityDtlsEntity eligdata, CitizenAppsEntity cityapp) throws Exception {
		Document document = new Document();
		FileOutputStream fos = new FileOutputStream(new File(eligdata.getCaseNum() + ".pdf"));
		PdfWriter pdfwriter = PdfWriter.getInstance(document, fos);
		document.open();
		Font font = new Font(Font.HELVETICA, 16, Font.BOLDITALIC, Color.RED);
		Paragraph paragraph = new Paragraph("Eligibility Details", font);
		document.add(paragraph);
		PdfPTable table = new PdfPTable(2);

		table.addCell("HolderName");
		table.addCell(cityapp.getFullName());
		table.addCell("HolderSSN");
		table.addCell(String.valueOf(cityapp.getSsn()));
		table.addCell("PlanName");
		table.addCell(eligdata.getPlanName());
		table.addCell("PlanStatus");
		table.addCell(eligdata.getPlanStatus());
		table.addCell("StartDate");
		table.addCell(String.valueOf(eligdata.getStartDate()));

		table.addCell("EndDate");
		table.addCell(String.valueOf(eligdata.getEndDate()));
		table.addCell("BenefitAmount");
		table.addCell(String.valueOf(eligdata.getBenifitAmt()));
		table.addCell("DenialReason");
		table.addCell(eligdata.getDenialReason());

		document.add(table);
		document.close();
		pdfwriter.close();

	}

}
 