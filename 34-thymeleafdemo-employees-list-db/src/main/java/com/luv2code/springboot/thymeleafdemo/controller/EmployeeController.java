package com.luv2code.springboot.thymeleafdemo.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.luv2code.springboot.thymeleafdemo.entity.Employee;
import com.luv2code.springboot.thymeleafdemo.service.EmployeeService;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

	private EmployeeService employeeService; 
	
	//Const for const injection (service
	@Autowired
	public EmployeeController(EmployeeService employeeService) {
		
		this.employeeService = employeeService;
	}
	
	
	//add mapping for "/list"
	@GetMapping("/list")
	public String listEmployees(Model theModel) {
		
		//get employees from db
		List<Employee> theEmployees = employeeService.findAll();
		
		
		//add the spring model 
		theModel.addAttribute("employees", theEmployees);
	
		return "list-employees";
	}

	
	
}
