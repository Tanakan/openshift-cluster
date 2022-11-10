package com.example.demo.domain;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Getter
@Entity
@RequiredArgsConstructor
@NoArgsConstructor
public class Menu {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(nullable = false)
	@NonNull
	private String name;

	@Column(nullable = false)
	@NonNull
	private long price;

	@CreationTimestamp
	@Column(nullable = false)
	private Date createdAt;

	@OneToOne(cascade = CascadeType.ALL, mappedBy = "menu")
	@NonNull
	private Tuition tuition;
}
