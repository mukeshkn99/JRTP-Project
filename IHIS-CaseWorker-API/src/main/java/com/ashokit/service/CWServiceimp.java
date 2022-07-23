package com.ashokit.service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ashokit.binding.Card;
import com.ashokit.binding.Loginform;
import com.ashokit.binding.Profile;
import com.ashokit.entity.CaseWorkersAcctEntity;
import com.ashokit.entity.EligibilityDtlsEntity;
import com.ashokit.repository.AppPlanRepository;
import com.ashokit.repository.CaseWorkersAcctRepository;
import com.ashokit.repository.CitizenAppsRepository;
import com.ashokit.repository.EligibilityDtlsRepository;
import com.ashokit.utils.EmailUtils;


@Service
public class CWServiceimp implements CWService {
	private Logger logger=LoggerFactory.getLogger(CWServiceimp.class);

	@Autowired
	private CaseWorkersAcctRepository CwAcctRepo;

	@Autowired 
	private AppPlanRepository AppPlanRepo;
	
	@Autowired
	private CitizenAppsRepository CitizenRepo;
	
	@Autowired
	private EligibilityDtlsRepository eligirepo;
	
	@Autowired
	private EmailUtils emailutils;
	
	@Override
	public String cwlogin(Loginform login) {
		CaseWorkersAcctEntity entity = CwAcctRepo.findByEmailAndPazzword(login.getEmail(), login.getPwd());
		if(entity==null) {
			return "Invalid Credentials"; 
		}
		return "success";
	}

	@Override
	public String forgotpwd(String email) {
		CaseWorkersAcctEntity entity = CwAcctRepo.findByEmail(email);
		if(entity==null) {
			return "Invalid email";
		}
		else {
			String filename="Recover-Password.txt";
			String body = readmailbody(filename,entity);
			String subject="password recovered";
			emailutils.sendmail(email, subject, body);
			return "success";
		}
	
	}

	@Override
	public Card fetchDashboards() {
		long planscount = AppPlanRepo.count();
		
		Card card=new Card();
		card.setAppPlans((int)planscount);
		
		long countPlans = CitizenRepo.count();
		card.setCountPlans((int)countPlans);
		
		List<EligibilityDtlsEntity> findAll = eligirepo.findAll();
		int approvedsize = findAll.stream().filter(ed->ed.getPlanStatus().equals("Approved")).collect(Collectors.toList()).size();
		int deniedsize = findAll.stream().filter(ed->ed.getPlanStatus().equals("Denied")).collect(Collectors.toList()).size();
		return card;
	}

	@Override
	public Profile fetchProfileInfo(String email) {
		CaseWorkersAcctEntity entity = CwAcctRepo.findByEmail(email);
		Profile profile=new Profile();
		BeanUtils.copyProperties(entity, profile);
		return profile;
	}

	@Override
	public String editprofile(Profile edit) {
		CaseWorkersAcctEntity entity = CwAcctRepo.findByEmail(edit.getEmail());
		Profile profile=new Profile();
		BeanUtils.copyProperties(entity, profile);
		CwAcctRepo.save(entity);
		return "Success";
	}
	
	 private String readmailbody(String filename, CaseWorkersAcctEntity entity)  {
			String mailbody=null;
			try {
				StringBuilder sb=new StringBuilder();
				FileReader fr=new FileReader(filename);
				BufferedReader br=new BufferedReader(fr);
				String line=br.readLine();
				while(line!=null) {
					sb.append(line);
					line=br.readLine();
				}
				mailbody=sb.toString();
				mailbody=mailbody.replace("{NAME}", entity.getFullName());
				mailbody=mailbody.replace("{PWD}", entity.getPazzword());
				br.close();
				fr.close();
				
			}
			catch(Exception e) {
				logger.error(e.getMessage(),e);
			}
			return mailbody;
		}

	
}
