package com.example.demo.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, String> {

}
