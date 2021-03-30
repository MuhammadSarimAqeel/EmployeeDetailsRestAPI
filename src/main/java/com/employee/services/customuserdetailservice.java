package com.employee.services;


import java.util.ArrayList;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class customuserdetailservice implements UserDetailsService {

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		
		if(username.equals("SarimAqeel")) {
			
			return new User("SarimAqeel", "SarimSarim123", new ArrayList<>());
		}else {
			
			throw new UsernameNotFoundException("User not found!!");
		}
		
	}

}
