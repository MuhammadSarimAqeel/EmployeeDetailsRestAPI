package com.employee.Auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.userdetails.DaoAuthenticationConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.employee.filter.JWTauthenticationFilter;
import com.employee.services.customuserdetailservice;

@Configuration
@EnableWebSecurity
public class SecureAuth extends WebSecurityConfigurerAdapter {
	
	
	@Autowired
	private customuserdetailservice customuserdetailservice;

	@Autowired
	private JWTauthenticationFilter authenticationfilter;
	
	@Override
	public void configure(HttpSecurity http)throws Exception {
		
	 http.cors().and().authorizeRequests().antMatchers("/api/login").permitAll().anyRequest().authenticated().and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	 http.addFilterBefore(authenticationfilter, UsernamePasswordAuthenticationFilter.class);
	 http .csrf().disable() ;
		
	}
	
	
	
	
	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		 auth.userDetailsService(customuserdetailservice);
		
	}
	
	
	@Bean
	public PasswordEncoder passwordencoder() {
		return NoOpPasswordEncoder.getInstance();
	}
	
	@Bean
	public AuthenticationManager authenticationmanagerbean() throws Exception {
		
		return super.authenticationManagerBean();
	}

	
	/*
	
	@Override
	public void configure(AuthenticationManagerBuilder auth)throws Exception {
		
	auth.inMemoryAuthentication().passwordEncoder(org.springframework.security.crypto.password
			.NoOpPasswordEncoder.getInstance()).withUser("SarimAqeel").password("SarimSarim123").roles("USER");
		
	}

	
	@Override
	public void configure(HttpSecurity http)throws Exception {
		
	 http.cors().and().authorizeRequests().antMatchers("/api/login").hasAnyRole("USER","ADMIN").antMatchers("/api/employees", "/api/names").permitAll().and().httpBasic();
	 http .csrf().disable() .authorizeRequests() .anyRequest().permitAll(); 
		
	}
	*/

}
