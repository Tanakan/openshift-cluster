package com.example.demo.infrastructure.external;

import java.util.Date;

import lombok.Data;

@Data
public class Message {
	private long id;

	private Date createdAt;

	private long menuId;

	private String employee;

	private int price;
}
