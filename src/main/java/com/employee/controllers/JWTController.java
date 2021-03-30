package com.employee.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.employee.helper.JWTutil;
import com.employee.model.JWTrequest;
import com.employee.model.JWTresponse;
import com.employee.services.customuserdetailservice;

@RestController
@RequestMapping("/api/")
@CrossOrigin(originPatterns = "*")
public class JWTController {
	
	@Autowired
	private customuserdetailservice userservice;
	
	@Autowired
	private JWTutil jwtutil;
	
	@Autowired
	private AuthenticationManager authmanager;
	
	
	@PostMapping("/login")
	public ResponseEntity<?> generatetoken(@RequestBody JWTrequest Jwtrequest) throws Exception{
		
		try {
			
			this.authmanager.authenticate(new UsernamePasswordAuthenticationToken(Jwtrequest.getUsername(), Jwtrequest.getPassword()));
			
			
		}catch(UsernameNotFoundException e) {
			
			e.printStackTrace();
			
		}catch(BadCredentialsException  e) {
			
			throw new Exception("Bad Credentials");
		}
		
		UserDetails userDetails = this.userservice.loadUserByUsername(Jwtrequest.getUsername());
		
		String token =this.jwtutil.generateToken(userDetails);
		System.out.println(token);
		
		return ResponseEntity.ok(new JWTresponse(token));
	}

	
	
}


