package com.example.demo.entity;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Bill {
	@EmbeddedId
	private BillId billId;

	@NonNull
	@Column(nullable = false)
	private int amount;
}
