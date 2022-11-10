package com.example.demo.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.PaymentConsumerDTO;
import com.example.demo.entity.Transaction;
import com.example.demo.repository.TransactionRepository;

@Service
public class TransactionService {
	@Autowired
	private TransactionRepository transactionRepository;

	public Transaction createTransaction(PaymentConsumerDTO paymentConsumerDTO) {
		Transaction transaction = new Transaction(paymentConsumerDTO.getCreatedAt(), paymentConsumerDTO.getEmployee(),
				paymentConsumerDTO.getPrice());
		return transactionRepository.save(transaction);
	}

	public List<Transaction> listTransaction(String employee, Date from, Date to) {
		if (from == null || to == null) {
			return transactionRepository.findAllByEmployee(employee);
		}
		return transactionRepository.findAllByCreatedAtBetween(employee, from, to);
	}
}
