package com.example.demo.presentation;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentDTO {
	private long id;

	private Date createdAt;

	private int menuId;

	private String employeeId;

	private int price;
}
