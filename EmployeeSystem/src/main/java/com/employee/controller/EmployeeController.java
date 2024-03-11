package com.employee.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.employee.model.Employee;
import com.employee.service.EmployeeService;

@RestController
public class EmployeeController {
	
	private EmployeeService employeeService;

	public EmployeeController(EmployeeService employeeService) {
		super();
		this.employeeService = employeeService;
	}
	
	// Create Employee Rest API
	
	@PostMapping("api/add")
	public ResponseEntity<Employee> saveEmployee(@RequestBody Employee employee){
		return new ResponseEntity<Employee>(employeeService.saveEmployee(employee), HttpStatus.CREATED);
	}
	
	
	// Get All Employees Rest API
	
	@GetMapping("api/showall")
	public List<Employee> showAllEmployees(){
		return employeeService.getAllEmployees();
	}
	
	// Get Employee By ID Rest API
	
	@GetMapping("api/showid/{id}")
	public ResponseEntity<Employee> showEmployeeById(@PathVariable("id") long empId){
		return new ResponseEntity<Employee>(employeeService.getEmployeeById(empId), HttpStatus.OK);
	}
	
	// Update Employee Rest API
	
	@PutMapping("api/update/{id}")
	public ResponseEntity<Employee> updateEmp(@RequestBody Employee emp, @PathVariable("id") long empId){
		return new ResponseEntity<Employee>(employeeService.updateEmployee(emp, empId),HttpStatus.OK);
 
	}
	
	// Delete Employee Rest API
	
	@DeleteMapping("api/delete/{id}")
	public ResponseEntity<String> deleteEmp(@PathVariable("id") long empId){
		employeeService.deleteEmployee(empId);
		return new ResponseEntity<String>("Employee Deleted Successfully", HttpStatus.OK);
 
	}
}
