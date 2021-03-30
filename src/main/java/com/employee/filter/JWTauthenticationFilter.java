package com.employee.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.employee.helper.JWTutil;
import com.employee.services.customuserdetailservice;

@Component
public class JWTauthenticationFilter extends OncePerRequestFilter {
	
	@Autowired
	private JWTutil jwtutil;
	
	@Autowired
	private customuserdetailservice userservice;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	String requesttokenheader =	request.getHeader("Authorization");
	String username = null;
	String JWTtoken = null;
	
	if(requesttokenheader!= null && requesttokenheader.startsWith("Bearer ")) {
		
		JWTtoken = requesttokenheader.substring(7);
		try {
			System.out.println(JWTtoken);
			
		username =	this.jwtutil.extractUsername(JWTtoken);
		System.out.println(username);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	UserDetails userDetails =	this.userservice.loadUserByUsername(username);
		if(username!= null && SecurityContextHolder.getContext().getAuthentication()==null) {
			
		UsernamePasswordAuthenticationToken	  UsernamePasswordAuthenticationToken =new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
		UsernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
		
			
		SecurityContextHolder.getContext().setAuthentication(UsernamePasswordAuthenticationToken);
		
		}else {
			
			System.out.println("User is not validated");
		}
	}
	filterChain.doFilter(request, response);
		
	}
	
	

}
