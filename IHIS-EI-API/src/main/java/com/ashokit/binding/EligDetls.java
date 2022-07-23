package com.ashokit.binding;

import java.time.LocalDate;
import java.util.Date;



import lombok.Data;

@Data
public class EligDetls {

	private Integer eligId;
	
	private Integer caseNum;

	private String planName;

	private String planStatus;

	private LocalDate startDate;

	private LocalDate endDate;

	private Integer benifitAmt;

	private String DenialReason;

}
