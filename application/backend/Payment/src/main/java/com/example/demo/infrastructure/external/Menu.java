package com.example.demo.infrastructure.external;

import java.util.Date;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class Menu {
	private long id;

	private String name;

	private int price;

	private Date createdAt;

	private Tuition tuition;

	@Data
	@NoArgsConstructor
	public class Tuition {
		private double cal;

		private double protein;

		private double fat;

		private double carbo;

		private double salt;
	}
}
