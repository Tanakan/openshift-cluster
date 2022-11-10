package com.example.demo.consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.example.demo.dto.PaymentConsumerDTO;
import com.example.demo.service.TransactionService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class PaymentConsumer {

	@Autowired
	private TransactionService transactionService;

	@KafkaListener(topics = "${app.topic.payment}")
	public void consumer(String message) throws JsonMappingException, JsonProcessingException {
		PaymentConsumerDTO paymentConsumerDTO = new ObjectMapper().readValue(message, PaymentConsumerDTO.class);
		transactionService.createTransaction(paymentConsumerDTO);
	}
}
