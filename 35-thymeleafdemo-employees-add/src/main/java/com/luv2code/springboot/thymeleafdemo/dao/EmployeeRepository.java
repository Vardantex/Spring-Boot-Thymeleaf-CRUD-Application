package com.luv2code.springboot.thymeleafdemo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.luv2code.springboot.thymeleafdemo.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

	//This automatically gets the CRUD methods
	
	//Add a method to sort by last name (METHOD NAME WILL BE PARSED
	//LLOKS FOR A SPECIFIC FORMAT AND PATTERN
	//CREATES APPROPRIATE QUERY AUTOMATICALLY
	public List<Employee> findAllByOrderByLastNameAsc();
	
}
