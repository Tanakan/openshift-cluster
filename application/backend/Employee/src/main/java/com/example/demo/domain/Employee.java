package com.example.demo.domain;

import javax.persistence.Column;
import javax.persistence.Id;

import lombok.Data;

@Data
public class Employee {

	@Id
	private String employeeId;

	@Column(nullable = false)
	private String lastName;

	@Column(nullable = false)
	private String firstName;

	@Column(nullable = false)
	private String mailAddress;

}
