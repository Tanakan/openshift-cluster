package com.example.demo.dto;




import lombok.Data;

@Data
public class MenuDTO {
	private String name;
	
	private Tuition tuition;
	
	@Data
	class Tuition {
		private double cal;
		
		private double protein;
		
		private double fat;
		
		private double carbo;
		
		private double salt;
	}
}
