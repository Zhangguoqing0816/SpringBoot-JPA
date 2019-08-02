package com.me.testjpa.jpa.repository;

import com.me.testjpa.jpa.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmpRepository extends JpaRepository<Employee, String>      {

}
