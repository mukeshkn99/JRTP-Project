package com.ashokit.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.ashokit.binding.summary;
import com.ashokit.service.DataCollectService;


@RestController
public class SummaryController {
	@Autowired
	private DataCollectService service;
	
	@GetMapping("summary/{casenum}")
	public summary getdata(@PathVariable("casenum")Integer caseNum) {
		summary summ = service.getsummary(caseNum);
		return summ;
	}
	
}
