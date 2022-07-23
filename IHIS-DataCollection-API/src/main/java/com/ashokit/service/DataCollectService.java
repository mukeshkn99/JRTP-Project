package com.ashokit.service;

import java.util.List;
import java.util.Map;

import com.ashokit.binding.EducationDetails;
import com.ashokit.binding.IncomeDetails;
import com.ashokit.binding.PlanSelection;
import com.ashokit.binding.childs;
import com.ashokit.binding.summary;
import com.ashokit.entity.GraduationYearsEntity;

public interface DataCollectService {
	
public Map<Integer,String> getplan();
public String planselect(PlanSelection planselect);

public String insertincome(IncomeDetails income);

public List<GraduationYearsEntity> getyear();
public String inserteducation(EducationDetails education);

public String insertkidsdata(childs child);
public summary getsummary(Integer caseNum);
}
