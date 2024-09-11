package com.lojapet.petfi.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lojapet.petfi.entities.Login;
import com.lojapet.petfi.services.LoginService;

@RestController
@RequestMapping(path = "/login")
@CrossOrigin(origins = "https://pet-fi.vercel.app/**")
public class LoginController {

	@Autowired
	private LoginService loginService;

	@PostMapping
  public ResponseEntity<String> login(@RequestBody Login login){
	  try {
		  return ResponseEntity.ok(loginService.login(login));
	  }catch(AuthenticationException e) {
		  return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
	  }catch(Exception ex) {
		  return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
	  }
  }

}
