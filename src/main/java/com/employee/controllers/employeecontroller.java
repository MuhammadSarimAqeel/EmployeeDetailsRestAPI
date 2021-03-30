package com.employee.controllers;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


import com.employee.Auth.SecureAuth;
import com.employee.exception.resourcenotfound;
import com.employee.model.employee;
import com.employee.repository.employeerepository;


@RestController
@RequestMapping("/api/")
@CrossOrigin(origins = "*",allowedHeaders = "*")
public class employeecontroller {
	
	
	
	@Autowired
	private employeerepository emprepository;
	

	
	
	@RequestMapping("/employees")
	public List<employee> getallemployes(){
		
		
		
		
		
		return emprepository.findAll();
	}
	
	// get sorted employees by firstname
	@RequestMapping("/sortedemployees")
	public List<employee> getsortedemployes(){
		
		
		
		return emprepository.findAll(Sort.by(Sort.Direction.ASC, "firstename"));
	}
	
	//creating add employee
	@PostMapping("/employees")
	public ResponseEntity<employee> createemployee(@RequestBody employee emp) {
		
		employee employee = this.emprepository.save(emp);
		
		return ResponseEntity.ok(employee);
	}
// get employee by id
	@GetMapping("employees/{id}")
	public ResponseEntity<employee> getemployeebyid(@PathVariable("id") int id) {
		
		employee employee = emprepository.findById(id)
				.orElseThrow(()-> new resourcenotfound("Employee doesn`t exists with id :"+ id));
		return ResponseEntity.ok(employee);
	}
	//update employee
	@PutMapping("/employees/{id}")
	public ResponseEntity<employee> updateEmployee(@PathVariable("id") int id, @RequestBody employee employeeDetails){
		employee employee = emprepository.findById(id)
				.orElseThrow(()-> new resourcenotfound("Employee doesn`t exists with id :"+ id));
		employee.setFirstename(employeeDetails.getFirstename());
		employee.setLastname(employeeDetails.getLastname());
		employee.setEmail(employeeDetails.getEmail());
		
		
	employee updatedemployee=	emprepository.save(employee);
	
	return ResponseEntity.ok(updatedemployee);
	}
	
	//delete employee
	@DeleteMapping("/employees/{id}")
	public ResponseEntity<Map<String, Boolean>> deleteemployee(@PathVariable("id") int id){
		
		employee employee = emprepository.findById(id)
				.orElseThrow(()-> new resourcenotfound("Employee doesn`t exists with id :"+ id));
		
		emprepository.delete(employee);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		
		return ResponseEntity.ok(response);
		
	}
	// get employee by name
		@GetMapping("/names/{firstename}")
		public ResponseEntity<List<employee>> getemployeebyname(@PathVariable("firstename") String name) {
			
			List<employee> employees = emprepository.getuserByName(name);

			return ResponseEntity.ok(employees);
		}
		
		/*
		@GetMapping("/login")
	    public ResponseEntity<String> login( HttpServletRequest request, HttpServletResponse response) {
			  response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
			getallemployes();
				return ResponseEntity.ok("Authenticated Successfully");
		
	        
	          
		}
		*/
		
}

