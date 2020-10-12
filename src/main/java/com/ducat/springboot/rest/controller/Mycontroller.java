package com.ducat.springboot.rest.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ducat.springboot.rest.model.Employee;
import com.ducat.springboot.rest.service.Myservice;

@RestController
@Transactional( propagation = Propagation.REQUIRED)
public class Mycontroller {

	@Autowired
	Myservice service;

	@GetMapping(value= "/employeeall/all")
	List<Employee> getAllEmployees() {
		System.out.println(this.getClass().getSimpleName() + " - Get all employees service is invoked.");		
		return service.getAllEmployees();
	}
	
	@GetMapping(value= "/employee/all")
	public List<Employee> getEmployees() {
		System.out.println(this.getClass().getSimpleName() + " - Get all employees service is invoked.");
		return service.getEmployees();
	
	}
	@Transactional( propagation = Propagation.NEVER)
	@GetMapping(value= "/employee/{id}")
	public Employee getEmployeeById(@PathVariable int id) throws Exception {
		System.out.println(this.getClass().getSimpleName() + " - Get employee details by id is invoked.");

		Optional<Employee> emp =  service.getEmployeeById(id);
		if(!emp.isPresent())
			throw new Exception("Could not find employee with id- " + id);

		return emp.get();
	}

	@PostMapping(value= "/employee/add")
	public Employee createEmployee(@RequestBody Employee newemp) {
		System.out.println(this.getClass().getSimpleName() + " - Create new employee method is invoked.");
		return service.addNewEmployee(newemp);
	}

	@PutMapping(value= "/employee/update/{id}")
	public Employee updateEmployee(@RequestBody Employee updemp, @PathVariable int id) throws Exception {
		System.out.println(this.getClass().getSimpleName() + " - Update employee details by id is invoked.");

		Optional<Employee> emp =  service.getEmployeeById(id);
		if (!emp.isPresent())
			throw new Exception("Could not find employee with id- " + id);

		/* IMPORTANT - To prevent the overiding of the existing value of the variables in the database, 
		 * if that variable is not coming in the @RequestBody annotation object. */		
		if(updemp.getName() == null || updemp.getName().isEmpty())
			updemp.setName(emp.get().getName());
		if(updemp.getDepartment() == null || updemp.getDepartment().isEmpty())
			updemp.setDepartment(emp.get().getDepartment());
		if(updemp.getSalary() == 0)
			updemp.setSalary(emp.get().getSalary());

		// Required for the "where" clause in the sql query template.
		updemp.setId(id);
		return service.updateEmployee(updemp);
	}

	@DeleteMapping(value= "/employee/delete/{id}")
	public void deleteEmployeeById(@PathVariable int id) throws Exception {
		System.out.println(this.getClass().getSimpleName() + " - Delete employee by id is invoked.");

		Optional<Employee> emp =  service.getEmployeeById(id);
		if(!emp.isPresent())
			throw new Exception("Could not find employee with id- " + id);

		service.deleteEmployeeById(id);
	}

	@DeleteMapping(value= "/employee/deleteall")
	public void deleteAll() {
		System.out.println(this.getClass().getSimpleName() + " - Delete all employees is invoked.");
		service.deleteAllEmployees();
	}
}