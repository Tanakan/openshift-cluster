package com.example.demo.consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.example.demo.dto.PaymentConsumerDTO;
import com.example.demo.service.BillService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class PaymentConsumer {

	@Autowired
	private BillService billService;

	@KafkaListener(topics = "payment")
	public void consumer(String payment) throws JsonMappingException, JsonProcessingException {
		PaymentConsumerDTO paymentConsumerDTO = new ObjectMapper().readValue(payment, PaymentConsumerDTO.class);
		billService.createBill(paymentConsumerDTO);
	}
}
