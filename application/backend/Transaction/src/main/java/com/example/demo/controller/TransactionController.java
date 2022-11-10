package com.example.demo.controller;

import java.lang.reflect.Type;
import java.util.Date;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Transaction;
import com.example.demo.service.TransactionService;

@RestController
@RequestMapping(path = "/transactions")
public class TransactionController {

	@Autowired
	private TransactionService transactionService;

	@Autowired
	private ModelMapper modelMapper;

	@RequestMapping(method = RequestMethod.GET)
	public List<Transaction> list(@RequestParam(required = false) @DateTimeFormat(pattern = "yyyyMMdd") Date from,
			@RequestParam(required = false) @DateTimeFormat(pattern = "yyyyMMdd") Date to) {
		Type targetListType = new TypeToken<List<Transaction>>() {
		}.getType();
		List<Transaction> transactions = transactionService.listTransaction("test", from, to);
		return modelMapper.map(transactions, targetListType);
	}
}
