package com.luv2code.springboot.thymeleafdemo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.luv2code.springboot.thymeleafdemo.dao.EmployeeRepository;
import com.luv2code.springboot.thymeleafdemo.entity.Employee;

@Service
public class EmployeeServiceImpl implements EmployeeService{

	//JPA Repository doesnt need to use @Transactional
	private EmployeeRepository employeeRepository;
	
	//link const for field
	public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
		this.employeeRepository = employeeRepository;
	}

	/*Create a DAO object 
	 * 
	 * Link all methods from DAO to service 
	 *@transactional not needed with JPA Repository
	 * 
	 * Delegate calls from service to dao
	
	
	*/
	
	@Override
	@Transactional
	public List<Employee> findAll() {
		return employeeRepository.findAll();
	}

	
	@Override
	public Employee findById(int theId) {
		Optional<Employee> result = employeeRepository.findById(theId);

		Employee theEmployee=null;
		
		if(result.isPresent()) {
			theEmployee = result.get();
		} else
		{
			throw new RuntimeException("Did not find employee id - " + theId);
		}
		
		return theEmployee;
	}

	@Override
	public void save(Employee theEmployee) {
		employeeRepository.save(theEmployee);
	}

	@Override
	public void deleteById(int theId) {
		employeeRepository.deleteById(theId);
	}

}
