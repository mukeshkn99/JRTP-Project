package com.ashokit.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.type.BlobType;

import lombok.Data;

@Data
@Entity
@Table(name = "CO_TRIGGERS")
public class CoTriggersEntity {

	@Id
	@Column(name="TRG_ID")
	private Integer trgId;
	
	@Column(name="CASE_NUM")
	private String caseNum;
	
	@Column(name="TRG_STATUS")
	private String trgStatus;
	
	@Column(name="NOTICE")
	private BlobType notice;
}
