package com.example.demo.infrastructure.external;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.example.demo.domain.Payment;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class PaymentPublishService {
	@Autowired
	KafkaTemplate<String, String> kafkaTemplate;

	@Autowired
	ModelMapper modelMapper;

	@Value("${app.topic}")
	private String topic;

	public void publish(Payment payment) throws JsonProcessingException {
		Message message = modelMapper.map(payment, Message.class);
		kafkaTemplate.send(topic, new ObjectMapper().writeValueAsString(message));
	}
}
