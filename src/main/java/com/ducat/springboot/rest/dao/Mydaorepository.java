package com.ducat.springboot.rest.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ducat.springboot.rest.model.Employee;

@Repository
public interface Mydaorepository extends JpaRepository<Employee, Integer> {
	
	@Query("select e from Employee e where e.name like %?1")
    List<Employee> findByNameEndsWith(String chars);
	
}
		