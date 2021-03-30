package com.employee.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.employee.model.employee;

@Repository
public interface employeerepository extends JpaRepository<employee, Integer> {
	@Query("select e from employee e  where e.firstename like %:n%  or e.lastname like %:n%")
	public List<employee> getuserByName(@Param("n")String name);
	
}
