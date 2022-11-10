package com.example.demo.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Transaction;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, String> {

	@Query("select t from Transaction t where t.employee = :employee and :from <= t.createdAt and  t.createdAt <= :to")
	List<Transaction> findAllByCreatedAtBetween(String employee, Date from, Date to);

	@Query("select t from Transaction t where t.employee = :employee order by t.createdAt desc")
	List<Transaction> findAllByEmployee(String employee);
}
