package com.example.demo.application;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.domain.Payment;
import com.example.demo.infrastructure.PaymentRepository;
import com.example.demo.infrastructure.external.Menu;
import com.example.demo.infrastructure.external.MenuRestClientService;
import com.example.demo.infrastructure.external.PaymentPublishService;
import com.example.demo.presentation.RequestDTO;

@Service
@Transactional
public class PaymentService {
	@Autowired
	private PaymentRepository paymentRepository;

	@Autowired
	private MenuRestClientService menuRestClientService;

	@Autowired
	private PaymentPublishService paymentPublishService;

	public Payment addPayment(RequestDTO paymentRequestDTO) throws Exception {
		Menu menuDTO = menuRestClientService.getMenu(paymentRequestDTO.getMenuId());
		Payment payment = new Payment(menuDTO.getId(), paymentRequestDTO.getEmployee(), menuDTO.getPrice());
		payment = paymentRepository.save(payment);
		paymentPublishService.publish(payment);
		return payment;
	}

	public Optional<Payment> getPayment(long paymentId) throws Exception {
		return paymentRepository.findById(paymentId);
	}

	public List<Payment> listPayments() {
		return paymentRepository.findAll();
	}

}
