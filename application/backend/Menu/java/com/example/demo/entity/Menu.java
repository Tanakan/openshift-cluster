package com.example.demo.entity;


import java.sql.Timestamp;
import java.time.Instant;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@Entity
@RequiredArgsConstructor
@NoArgsConstructor
public class Menu {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String id;
	
	@CreationTimestamp
	@Column(nullable = false)
	private Timestamp createdAt;
	
	@Column(nullable = false)
	@NonNull
	private String name;
	
	@OneToOne(cascade = CascadeType.ALL, mappedBy = "menu")
	@NonNull
	private Tuition tuition;
}
