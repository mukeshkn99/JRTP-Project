package com.ashokit.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.ashokit.binding.EligDetls;
import com.ashokit.service.EligDetlsService;

@RestController
public class EligRestController {
@Autowired
private EligDetlsService eligservice;

@GetMapping("/getelig/{casenum}")
public ResponseEntity<EligDetls> checkelig(@PathVariable("casenum")Integer caseNum){
	EligDetls eligDetls = eligservice.getcasenum(caseNum);
	return new ResponseEntity<>(eligDetls,HttpStatus.OK);
}
}
