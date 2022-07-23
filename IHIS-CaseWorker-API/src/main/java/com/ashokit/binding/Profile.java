package com.ashokit.binding;

import java.time.LocalDate;

import lombok.Data;

@Data
public class Profile {
	private Integer acctId;
private String fullName;
private String email;
private String gender;
private Long mobilenum;
private LocalDate dob;
private Long zzn;

}
