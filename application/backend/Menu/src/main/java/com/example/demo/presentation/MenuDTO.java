package com.example.demo.presentation;

import java.util.Date;

import lombok.Data;

@Data
public class MenuDTO {
	private long id;

	private String name;

	private long price;

	private Date createdAt;

	private Tuition tuition;

	@Data
	private static class Tuition {
		private double cal;

		private double protein;

		private double fat;

		private double carbo;

		private double salt;
	}
}
