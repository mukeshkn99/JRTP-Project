package com.ashokit.rest;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ashokit.binding.PlanSelection;
import com.ashokit.service.DataCollectService;

@RestController
public class PlanSelectRestController {

	@Autowired
	private DataCollectService service;

	@GetMapping("/getplan")
	public Map<Integer, String> getcityplan() {
		 return service.getplan();
	}
	@PostMapping("/planselect")
	public ResponseEntity<String> createplan(@RequestBody PlanSelection planselect) {
		String msg = service.planselect(planselect);
		return new ResponseEntity<>(msg, HttpStatus.OK);
	}
}
