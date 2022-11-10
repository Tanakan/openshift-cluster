package com.example.demo.presentation;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.application.EmployeeService;
import com.example.demo.domain.Employee;

@RestController
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	@Autowired
	private ModelMapper modelMapper;

	@GetMapping
	public List<EmployeeDTO> listEmployees() {
		List<Employee> employees = employeeService.listEmployees();
		List<EmployeeDTO> response = modelMapper.map(employees, new TypeToken<List<EmployeeDTO>>() {
		}.getType());
		return response;
	}

}
