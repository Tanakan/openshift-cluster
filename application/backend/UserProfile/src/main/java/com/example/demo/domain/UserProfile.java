package com.example.demo.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class UserProfile {
	@Id
	@Column(nullable = false)
	private String employeeId;

	@Column(nullable = false)
	private String lastName;

	@Column(nullable = false)
	private String firstName;

	@Column(nullable = false)
	private String mailAddress;
}
