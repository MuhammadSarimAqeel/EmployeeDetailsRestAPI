package com.employee.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class employee {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id ;
	private String firstename;
	private String lastname;
	private String email;
	
	
	
	public employee(int id, String firstename, String lastname, String email) {
		super();
		this.id = id;
		this.firstename = firstename;
		this.lastname = lastname;
		this.email = email;
	}



	public employee(String firstename, String lastname, String email) {
		super();
		this.firstename = firstename;
		this.lastname = lastname;
		this.email = email;
	}



	public employee() {
		super();
		// TODO Auto-generated constructor stub
	}



	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public String getFirstename() {
		return firstename;
	}



	public void setFirstename(String firstename) {
		this.firstename = firstename;
	}



	public String getLastname() {
		return lastname;
	}



	public void setLastname(String lastname) {
		this.lastname = lastname;
	}



	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}
	
	
	

}
