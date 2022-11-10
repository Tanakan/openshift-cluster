package com.example.demo.presentation;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
class UserProfileDTO {
	@JsonProperty("employee_id")
	private String employeeId;

	@JsonProperty("last_name")
	private String lastName;

	@JsonProperty("first_name")
	private String firstName;

	@JsonProperty("mail_address")
	private String mailAddress;
}
