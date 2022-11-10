package com.example.demo.service;

import java.util.Calendar;
import java.util.Optional;

import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.PaymentConsumerDTO;
import com.example.demo.entity.Bill;
import com.example.demo.entity.BillId;
import com.example.demo.repository.BillRepository;

@Service
public class BillService {

	@Autowired
	private BillRepository billRepository;

	public void createBill(PaymentConsumerDTO paymentConsumerDTO) {
		BillId billId = new BillId(paymentConsumerDTO.getEmployee(),
				DateUtils.truncate(paymentConsumerDTO.getCreatedAt(), Calendar.MONTH));
		Optional<Bill> bill = billRepository.findById(billId);
		if (bill.isEmpty()) {
			billRepository.save(new Bill(billId, paymentConsumerDTO.getPrice()));
		} else {
			billRepository.save(new Bill(billId, bill.get().getAmount() + paymentConsumerDTO.getPrice()));
		}
	}

}
