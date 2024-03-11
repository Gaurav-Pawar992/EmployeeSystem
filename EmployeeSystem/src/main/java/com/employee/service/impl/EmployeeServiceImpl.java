package com.employee.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.employee.exception.ResourceNotFoundException;
import com.employee.model.Employee;
import com.employee.repository.EmployeeRepository;
import com.employee.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {
	
	private EmployeeRepository employeeRepository ;
	
	public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
		super();
		this.employeeRepository = employeeRepository;
	}



	@Override
	public Employee saveEmployee(Employee employee) {
		// TODO Auto-generated method stub
		return employeeRepository.save(employee);
	}



	@Override
	public List<Employee> getAllEmployees() {
		// TODO Auto-generated method stub
		return employeeRepository.findAll();
	}



	@Override
	public Employee getEmployeeById(long id) {
		// TODO Auto-generated method stub
		
		/*
		 * Optional<Employee> employee = employeeRepository.findById(id);
		 * 
		 * if(employee.isPresent()) { return employee.get(); } else { throw new
		 * ResourceNotFoundException("Employee", "Id", id); }
		 */
		
		// Use of Lambda Expression
		
		return employeeRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Employee", "Id", id));
		
	}



	@Override
	public Employee updateEmployee(Employee employee, long id) {
		// TODO Auto-generated method stub
		
		// We will check whether employee with given id exist or not
		
		Employee existEmp = employeeRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Employee", "Id", id)); 
		
		existEmp.setFirstName(employee.getFirstName());
		existEmp.setLastName(employee.getLastName());
		existEmp.setEmail(employee.getEmail());
		
		employeeRepository.save(existEmp);
		
		return existEmp;
	}



	@Override
	public void deleteEmployee(long id) {
		// TODO Auto-generated method stub
		
		// We will check whether employee with given id exist or not
		
		employeeRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Employee", "Id", id)); 
		
		employeeRepository.deleteById(id);
		
	}




}
