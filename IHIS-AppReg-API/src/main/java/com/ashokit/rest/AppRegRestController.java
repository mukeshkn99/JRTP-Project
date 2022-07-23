package com.ashokit.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ashokit.binding.CitizenApp;
import com.ashokit.service.AppRegService;

@RestController
public class AppRegRestController {

	@Autowired
	private AppRegService service;
	
	@PostMapping("/apps")
	public ResponseEntity<String> createapp(@RequestBody CitizenApp citiapp){
		String msg = service.createapp(citiapp);
		return new ResponseEntity<>(msg,HttpStatus.OK);
	}
	
	@GetMapping("/getapp")
	public List<CitizenApp> viewapp() {
		return service.viewapp();
	}
}
