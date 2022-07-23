package com.ashokit.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ashokit.binding.Card;
import com.ashokit.binding.Loginform;
import com.ashokit.binding.Profile;
import com.ashokit.service.CWService;

@RestController
public class CwRestController {
@Autowired
private CWService cwservice;

@PostMapping("/login")
public ResponseEntity<String> login(@RequestBody Loginform login){
	String status = cwservice.cwlogin(login);
	return new ResponseEntity<>(status,HttpStatus.OK);
}

@GetMapping("/forgot/{email}")
public ResponseEntity<String> forgot(@PathVariable("email") String email){
	String forgotpwd = cwservice.forgotpwd(email);
	return new ResponseEntity<>(forgotpwd,HttpStatus.OK);
}
@GetMapping("/dashboard")
public ResponseEntity<Card> login(){
	Card status = cwservice.fetchDashboards();
	return new ResponseEntity<>(status,HttpStatus.OK);
}
@GetMapping("/fetchprofile/{email}")
public ResponseEntity<Profile> fetchcard(@PathVariable("email")String email){
Profile profileInfo = cwservice.fetchProfileInfo(email);
return new ResponseEntity<>(profileInfo,HttpStatus.OK);
}
@PostMapping("/update")
public ResponseEntity<String> edit(@RequestBody Profile profile){
String profileInfo = cwservice.editprofile(profile);
return new ResponseEntity<>(profileInfo,HttpStatus.OK);
}
}
