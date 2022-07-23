package com.ashokit.service;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ashokit.binding.EligDetls;
import com.ashokit.entity.AppPlanEntity;
import com.ashokit.entity.CitizenAppsEntity;
import com.ashokit.entity.CitizenChildDtlsEntity;
import com.ashokit.entity.CitizenGraduationDtlsEntity;
import com.ashokit.entity.CitizenIncomeDtlsEntity;
import com.ashokit.entity.CitizenPlansEntity;
import com.ashokit.entity.CoTriggersEntity;
import com.ashokit.entity.EligibilityDtlsEntity;
import com.ashokit.repository.AppPlanRepository;
import com.ashokit.repository.CitizenAppsRepository;
import com.ashokit.repository.CitizenChildDtlsRepository;
import com.ashokit.repository.CitizenGraduationDtlsRepository;
import com.ashokit.repository.CitizenIncomeDtlsRepository;
import com.ashokit.repository.CitizenPlansRepository;
import com.ashokit.repository.CoTriggersRepository;
import com.ashokit.repository.EligibilityDtlsRepository;
import com.ashokit.utils.EligUtils;

@Service
public class EligDetlsImplemt implements EligDetlsService {

	@Autowired
	private AppPlanRepository planrepo;
	
	@Autowired
	private EligibilityDtlsRepository eligrepo;
	
	@Autowired
	private CitizenIncomeDtlsRepository incomerepo;
	
	@Autowired
	private CitizenPlansRepository cityplanrepo;
	
	@Autowired
	private CitizenAppsRepository cityapprepo;
	
	@Autowired
	private CitizenGraduationDtlsRepository citygradrepo;
	
	@Autowired
	private CitizenChildDtlsRepository citychildrepo; 
	
	@Autowired
	private CoTriggersRepository triggerrepo;
	
	@Autowired
	private EligUtils eligutils;
	@Override
	public EligDetls getcasenum(Integer caseNum) {
		String planstatus=null;
		LocalDate today=LocalDate.now();
		
		//get planid into casenum in CityzenPlans
		CitizenPlansEntity plansEntity = cityplanrepo.findByCaseNum(caseNum);
		Integer planId = plansEntity.getPlanId();
		
		//get planname into AppPlans
		Optional<AppPlanEntity> optional = planrepo.findById(planId);
		AppPlanEntity appPlanEntity = optional.get();
		String planName = appPlanEntity.getPlanName();
		
		//getIncome 
		CitizenIncomeDtlsEntity incomeDtlsEntity = incomerepo.findByCaseNum(caseNum);
		Double salaryIncome = incomeDtlsEntity.getSalaryIncome();
		Double propertyIncome = incomeDtlsEntity.getPropertyIncome();
		
		//count child
		List<CitizenChildDtlsEntity> listChild = citychildrepo.findByCaseNum(caseNum);
		int childcount = listChild.size();
		
		//citizen acc details
		 Optional<CitizenAppsEntity> cityapp = cityapprepo.findByCaseNum(caseNum);
		 CitizenAppsEntity citizenAppsEntity = cityapp.get();
		 int cityage = Period.between(citizenAppsEntity.getDob(),today).getYears();
		 
		 //citi education detls
		 CitizenGraduationDtlsEntity graduationDtlsEntity = citygradrepo.findByCaseNum(caseNum);
		 Integer gradyear = graduationDtlsEntity.getGraduationYearId();
		 
		 //get kids age
		 List<Integer> kidsagelist=new ArrayList<>();
		 listChild.forEach(child->{
			 int age = Period.between(child.getDob(),today).getYears();
             kidsagelist.add(age); 		 
		 });
		 
		 //condition check age
		 boolean kidsageelig = eligutils.kidsageelig(kidsagelist);
		 
		 EligDetls elig=new EligDetls();
		 elig.setCaseNum(caseNum);
		 elig.setBenifitAmt(400);
		 elig.setPlanName(planName);
		 
		 if("SNAP".equals(planName)&& salaryIncome>300) {
			 planstatus="Denied";
             elig.setDenialReason("snap Income is greater than 300");
		 }
		 if("CCAP".equals(planName)&&salaryIncome>300) {
		if(childcount>0 && kidsageelig) {
			planstatus="Denied";
			elig.setDenialReason("ccap Income is graeter than 300");
		}
		 }
		 if("MEDICAID".equals(planName)&&salaryIncome>300) {
			 if(propertyIncome!=0) {
				 planstatus="Denied";
				 elig.setDenialReason("Medicaid not applicable property income");
			 }
		 }
		 if("MEDICARE".equals(planName)&& cityage>65) {
			 planstatus="Denied";
			 elig.setDenialReason("Medicare not applicable for age is bleow 65");
		 }
		 if("NJW".equals(planName) &&gradyear==null||gradyear==0 ) {
			 planstatus="Denied";
			 elig.setDenialReason("NJW is not applicable for ungraduate");
		 }
		 planstatus="Approved";
		 elig.setDenialReason("NA");
		 if("Approved".equals(planstatus)) {
			elig.setStartDate(today);
			elig.setEndDate(today.plusMonths(3));
		 }
		 else {
			 elig.setStartDate(null);
			 elig.setEndDate(null);
		 }
		 //save elig record
		 saveeligdetls(elig);
		 
		 //save cotrigger
		 savecorrespondance(elig);
		 return elig;
		}
	private String saveeligdetls(EligDetls elig) {
	EligibilityDtlsEntity entity=new EligibilityDtlsEntity();
	BeanUtils.copyProperties(elig,entity);
	eligrepo.save(entity);
	return "EligDetls stored into database";
	}
	private String savecorrespondance(EligDetls elig) {
		CoTriggersEntity entity=new CoTriggersEntity();
		entity.setCaseNum(elig.getCaseNum());
		entity.setTrgStatus("pending");
		entity.setNotice(null);
		triggerrepo.save(entity);
		return "Trigers detls stored into database";
	}
}