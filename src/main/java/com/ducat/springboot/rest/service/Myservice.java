package com.ducat.springboot.rest.service;

import java.util.List;
import java.util.Optional;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ducat.springboot.rest.model.Employee;
@Transactional( propagation = Propagation.REQUIRED)
public interface Myservice {

	public List<Employee> getEmployees();
	//@Transactional( propagation = Propagation.NEVER)
	public Optional<Employee> getEmployeeById(int empid);
	public Employee addNewEmployee(Employee emp);
	public Employee updateEmployee(Employee emp);
	public void deleteEmployeeById(int empid);
	public void deleteAllEmployees();
	List<Employee> getAllEmployees();

}