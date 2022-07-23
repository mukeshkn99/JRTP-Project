package com.ashokit.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "CITIZEN_INCOME_DTLS")
public class CitizenIncomeDtlsEntity {

	@Id
	@Column(name="INCOME_ID")
	private Integer incomeId;
	
	@Column(name="CASE_NUM")
	private Integer caseNum;
	
	@Column(name="SALARY_INCOME")
	private Integer salaryIncome;
	
	@Column(name="RENT_INCOME")
	private Integer rentIncome;
	
	@Column(name="PROPERTY_INCOME")
	private Integer propertyIncome;
	
	@Column(name="CREATED_DATE")
	private Date createdDate;
	
	@Column(name="UPDATED_DATE")
	private Date updatedDate;
	
	@Column(name="CREATED_BY")
	private String createdBy;
	
	@Column(name="UPDATED_BY")
	private String updatedBy;
	
	
	
}
