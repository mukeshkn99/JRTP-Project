package com.ashokit.binding;

import java.time.LocalDate;

import lombok.Data;

@Data
public class CitizenApp {
	private Integer caseNum;
	private String fullName;
	private String email;
	private Long moblienum;
	private String gender;
	private LocalDate dob;
	private Integer zzn;
	private String statename;
}
