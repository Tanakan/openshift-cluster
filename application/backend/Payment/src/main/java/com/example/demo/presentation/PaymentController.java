package com.example.demo.presentation;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.application.PaymentService;
import com.example.demo.domain.Payment;

@RestController
public class PaymentController {

	@Autowired
	private PaymentService paymentService;

	@Autowired
	private ModelMapper modelMapper;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public PaymentDTO addPayment(@RequestBody RequestDTO paymentRequestDTO) throws Exception {
		Payment payment = paymentService.addPayment(paymentRequestDTO);
		PaymentDTO respsonse = modelMapper.map(payment, PaymentDTO.class);
		return respsonse;
	}

	@GetMapping
	public List<PaymentDTO> listPayments() {
		List<Payment> payments = paymentService.listPayments();
		List<PaymentDTO> response = modelMapper.map(payments, new TypeToken<List<PaymentDTO>>() {
		}.getType());
		return response;
	}

	@GetMapping(path = "/{paymentId}")
	public PaymentDTO get(@PathVariable long paymentId) throws Exception {
		Optional<Payment> payment = paymentService.getPayment(paymentId);
		if (payment.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
		PaymentDTO response = modelMapper.map(payment.get(), PaymentDTO.class);
		return response;
	}
}