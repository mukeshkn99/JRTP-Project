package com.ashokit.rest;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.ashokit.service.CoTrgService;

@RestController
public class CoRestController {

	@Autowired
	private CoTrgService coservice;
	
	public Map<String,Integer> getnotice(){
		return coservice.generatenotice();
	}
}
