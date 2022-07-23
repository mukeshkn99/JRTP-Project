package com.ashokit.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ashokit.binding.EducationDetails;
import com.ashokit.entity.GraduationYearsEntity;
import com.ashokit.service.DataCollectService;


@RestController
public class EducationController {
	@Autowired
	private DataCollectService service;
	
	@GetMapping("/getyear")
	public List<GraduationYearsEntity> findgradyear(){
		return service.getyear();
	}
	@PostMapping("/education")
	public ResponseEntity<String> savedincome(@RequestBody EducationDetails education){
		String edu = service.inserteducation(education);
		return new ResponseEntity<>(edu,HttpStatus.OK);
	}
}
