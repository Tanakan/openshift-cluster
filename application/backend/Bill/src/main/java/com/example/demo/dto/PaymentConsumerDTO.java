package com.example.demo.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * AMQ StreamsのConsumerDTOクラス
 * 
 * @author mastanak
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentConsumerDTO {
	private String id;

	private Date createdAt;

	private String menuId;

	private String employee;

	private int price;
}
