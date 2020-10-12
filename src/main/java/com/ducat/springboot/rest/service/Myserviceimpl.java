package com.ducat.springboot.rest.service;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ducat.springboot.rest.dao.Mydaorepository;
import com.ducat.springboot.rest.model.Employee;

@Service
public class Myserviceimpl implements Myservice {

	@Autowired
	Mydaorepository dao;
	
	@PersistenceContext
    private EntityManager entityManager;

	@Override
	public List<Employee> getAllEmployees() {
		return entityManager.createQuery("from Employee e where e.id > 1001").getResultList();
	}
	
	@Override
	public List<Employee> getEmployees() {
		return dao.findAll();
	}
	@Override
	//@Transactional( propagation = Propagation.MANDATORY)
	public Optional<Employee> getEmployeeById(int empid) {
		return dao.findById(empid);
	}
	@Override
	public Employee addNewEmployee(Employee emp) {
		return dao.save(emp);
	}
	@Override
	public Employee updateEmployee(Employee emp) {
		return dao.save(emp);
	}
	@Override
	public void deleteEmployeeById(int empid) {
		dao.deleteById(empid);
	}
	@Override
	public void deleteAllEmployees() {
		dao.deleteAll();
	}
}