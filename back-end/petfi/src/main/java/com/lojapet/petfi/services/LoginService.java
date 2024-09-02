package com.lojapet.petfi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import com.lojapet.petfi.config.JwtServiceGenerator;
import com.lojapet.petfi.entities.Login;
import com.lojapet.petfi.entities.User;
import com.lojapet.petfi.repositories.LoginRepository;

@Service
public class LoginService {

  @Autowired
  private LoginRepository loginRepository;
  
  @Autowired
  private JwtServiceGenerator jwtService;
  
  @Autowired
  private AuthenticationManager authenticationManager;
  
  public String login(Login login){
	  authenticationManager.authenticate(
			  new UsernamePasswordAuthenticationToken(
					  login.getUsername(),
					  login.getPassword()));
	  User user = loginRepository.findByUsername(login.getUsername()).get();
	  String jwtToken = jwtService.generateToken(user);
	  
	  return jwtToken;
  }

}
