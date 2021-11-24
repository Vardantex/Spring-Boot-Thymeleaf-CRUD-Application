package com.luv2code.springboot.thymeleafdemo.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
	
		return "employees/list-employees";
	}

	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model theModel) {
		
		//TODO: create model attr tp bind form data 
		
		//Create a blank employee object 
		Employee theEmployee = new Employee();
		
		//Bind the employee object to the html form
		theModel.addAttribute("employee", theEmployee);
		
		return "employees/employee-form";
	}
	
	//Create a mapping for Update form (Constructor param: Request for employeeId 
	@GetMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("employeeId") int theId, Model theModel) {
		
		//get the employee from the service: Find by const id
		Employee theEmployee = employeeService.findById(theId);
		
		//set the employee as a model attr to prepopulate the form 
		theModel.addAttribute("employee", theEmployee);
		
		//send over to our form 
		return "employees/employee-form";
	}
	
	
	
	
	//Create a post mapping to save the users 
	
	@PostMapping("/save")
	public String saveEmployee(@ModelAttribute("employee") Employee theEmployee) {
		
		//save the employee
		employeeService.save(theEmployee);
		
		//Return with a redirect to prevent duplicate exploit
		return "redirect:/employees/list";
	}
	
	//Add mapping for deleting a user 
	@GetMapping("/delete")
	public String delete(@RequestParam("employeeId") int theId) {
		
		//delete employee 
		employeeService.deleteById(theId);
		
		return "redirect:/employees/list";
	}
	
	
}
