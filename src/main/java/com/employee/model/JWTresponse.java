package com.employee.model;

public class JWTresponse {
	String token;

	public JWTresponse(String token) {
		super();
		this.token = token;
	}

	public JWTresponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	@Override
	public String toString() {
		return "JWTresponse [token=" + token + "]";
	}
	
	
	
	

}
