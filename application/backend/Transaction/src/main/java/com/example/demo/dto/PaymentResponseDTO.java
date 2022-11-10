package com.example.demo.dto;

import java.util.Date;

import lombok.Data;

@Data
public class PaymentResponseDTO {
	private int id;

	private Date createdAt;

	private String employee;

	private int price;
}
