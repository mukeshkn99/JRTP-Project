package com.ashokit.service;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ashokit.binding.EducationDetails;
import com.ashokit.binding.IncomeDetails;
import com.ashokit.binding.PlanSelection;
import com.ashokit.binding.child;
import com.ashokit.binding.childs;
import com.ashokit.binding.summary;
import com.ashokit.entity.AppPlanEntity;
import com.ashokit.entity.CitizenChildDtlsEntity;
import com.ashokit.entity.CitizenGraduationDtlsEntity;
import com.ashokit.entity.CitizenIncomeDtlsEntity;
import com.ashokit.entity.CitizenPlansEntity;
import com.ashokit.entity.GraduationYearsEntity;
import com.ashokit.repository.AppPlanRepository;
import com.ashokit.repository.CitizenChildDtlsRepository;
import com.ashokit.repository.CitizenGraduationDtlsRepository;
import com.ashokit.repository.CitizenIncomeDtlsRepository;
import com.ashokit.repository.CitizenPlansRepository;
import com.ashokit.repository.GraduationYearsRepository;

@Service
public class DataCollectServiceImpl implements DataCollectService {

	@Autowired
	private AppPlanRepository planrepo;
	
	@Autowired
	private CitizenPlansRepository citizenrepo;
	
	@Autowired 
	private CitizenIncomeDtlsRepository cityincomerepo;
	
	@Autowired
	private CitizenGraduationDtlsRepository cityedurepo;
	
	@Autowired
	private GraduationYearsRepository gradyearrepo;
	
	@Autowired
	private CitizenChildDtlsRepository citychildrepo;
	
	@Override
	public Map<Integer, String> getplan() {
		List<AppPlanEntity> list = planrepo.findAll();
		Map<Integer,String> map=new HashMap<>();
		list.forEach(plan->{
			map.put(plan.getPlanId(),plan.getPlanName());
		});
		return map;
	}

	@Override
	public String planselect(PlanSelection planselect) {
	     CitizenPlansEntity entity=new CitizenPlansEntity();
	     BeanUtils.copyProperties(planselect,entity);
	     citizenrepo.save(entity);
		return "plan select success";
	}

	@Override
	public String insertincome(IncomeDetails income) {
		CitizenIncomeDtlsEntity entity=new CitizenIncomeDtlsEntity();
		BeanUtils.copyProperties(income, entity);
		cityincomerepo.save(entity);
		return "insert income details";
	}


	@Override
	public List<GraduationYearsEntity> getyear() {
		return gradyearrepo.findyear();
	}

	@Override
	public String inserteducation(EducationDetails education) {
		CitizenGraduationDtlsEntity entity=new CitizenGraduationDtlsEntity();
		BeanUtils.copyProperties(education, entity);
		cityedurepo.save(entity);
		return "education insert success";
	}


	@Override
	public String insertkidsdata(childs child) {
		List<child> ch = child.getChild();
		ch.forEach(chil->{
			CitizenChildDtlsEntity entity=new CitizenChildDtlsEntity();
			BeanUtils.copyProperties(chil,entity);
			citychildrepo.save(entity);
		});
		return "kids data saved success";
	}

	
	@Override
	public summary getsummary(Integer caseNum) {
		
		summary sum=new summary();
		List<CitizenIncomeDtlsEntity> incomentity = cityincomerepo.findByCaseNum(caseNum);
		IncomeDetails incomebinding=new IncomeDetails();
		BeanUtils.copyProperties(incomentity, incomebinding);
		sum.setIncome(incomebinding);
	
		List<CitizenGraduationDtlsEntity> gradentity = cityedurepo.findByCaseNum(caseNum);
		EducationDetails educationbinding=new EducationDetails();
		BeanUtils.copyProperties(gradentity, educationbinding);
		sum.setEducation(educationbinding);
		
		List<CitizenChildDtlsEntity> childentity = citychildrepo.findByCaseNum(caseNum);
		List<child> childlist=new ArrayList<>();
		childentity.forEach(kids->{
			child ch1=new child();
			BeanUtils.copyProperties(kids, ch1);
			childlist.add(ch1);
		});
	   childs ch3=new childs();
	   ch3.setCaseNum(caseNum);
	   ch3.setChild(childlist);
	   sum.setKids(ch3);
		return sum;
	}

}
