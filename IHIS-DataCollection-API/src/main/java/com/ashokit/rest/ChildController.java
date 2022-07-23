package com.ashokit.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ashokit.binding.childs;
import com.ashokit.service.DataCollectService;


@RestController
public class ChildController {
	@Autowired
	private DataCollectService service;
	
	@PostMapping("/child")
	public ResponseEntity<String> savedincome(@RequestBody childs child){
		String amount = service.insertkidsdata(child);
		return new ResponseEntity<>(amount,HttpStatus.OK);
	}
	}
