package com.example.demo.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.entity.Bill;
import com.example.demo.entity.BillId;

public interface BillRepository extends JpaRepository<Bill, BillId> {

	@Query("select b from Bill b where b.id = :id and :from <= b.billId.month and  b.billId.month <= :to")
	List<Bill> findAllByCreatedAtBetween(String id, Date from, Date to);
}
