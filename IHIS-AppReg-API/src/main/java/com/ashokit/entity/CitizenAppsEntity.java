package com.ashokit.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.jpa.repository.JpaRepository;

import lombok.Data;

@Data
@Entity
@Table(name = "CITIZEN_APPS")
public class CitizenAppsEntity {

	@Id
	@Column(name="CASE_NUM")
	private Integer caseNum;
	
	@Column(name="FULLNAME")
	private String fullName;
	
	@Column(name="EMAIL")
	private String email;
	
	@Column(name="PWD")
	private String pwd;
	
	@Column(name="MOBILE_NUM")
	private Integer mobileNum;
	
	@Column(name="GENDER")
	private String gender;
	
	@Column(name="DOB")
	private Date dob;
	
	@Column(unique=true,name="SSN")
	private Integer ssn;
	
	@Column(name="STATE_NAME")
	private Integer stateName;
	
	@Column(name="ACTIVE_SW")
	private Character activeSw;
	
	@Column(name="CREATED_DATE")
	private Date createdDate;
	
	@Column(name="UPDATED_DATE")
	private Date updatedDate;
	
	@Column(name="CREATED_BY")
	private String createdBy;
	
	@Column(name="UPDATED_BY")
	private String updatedBy;
}
