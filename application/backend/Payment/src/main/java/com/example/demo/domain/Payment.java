package com.example.demo.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
@RequiredArgsConstructor
public class Payment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@CreationTimestamp
	@Column(nullable = false)
	private Date createdAt;

	@NonNull
	@Column(nullable = false)
	private long menuId;

	@NonNull
	@Column(nullable = false)
	private String employeeId;

	@NonNull
	@Column(nullable = false)
	private int price;
}
