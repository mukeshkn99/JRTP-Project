package com.ashokit.rest;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ashokit.binding.IncomeDetails;
import com.ashokit.service.DataCollectService;



@RestController
public class IncomeRestController {
	@Autowired
	private DataCollectService service;
	
	@PostMapping("/income")
	public ResponseEntity<String> savedincome(@RequestBody IncomeDetails income){
		String amount = service.insertincome(income);
		return new ResponseEntity<>(amount,HttpStatus.OK);
	}
}
