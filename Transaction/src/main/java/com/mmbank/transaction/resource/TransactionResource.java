package com.mmbank.transaction.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.mmbank.transaction.Service.TransactionService;
import com.mmbank.transaction.pojo.Transaction;


@EnableDiscoveryClient
@RestController
@RequestMapping("/transactions")
public class TransactionResource {
	@Autowired
	public TransactionService service;
	
	@Autowired
	public RestTemplate restTemplate;
	@Autowired
	public Transaction transaction;
	
	@PostMapping("/withdraw")
	public ResponseEntity<Transaction> withdraw(@RequestBody Transaction transaction)
	{
		ResponseEntity<Double> entity = restTemplate.getForEntity("http://MMBank/accounts/"+transaction.getAccountNumber()+"/balance",Double.class);
		double currentBalance = entity.getBody();
		double updatedBalance = service.withdraw(
				transaction.getAccountNumber(),transaction.getAmount(),currentBalance, transaction.getTransactionDetails());
		restTemplate.put("http://MMBank/accounts/"+transaction.getAccountNumber()+"?currentBalance="+updatedBalance, null);
		return new ResponseEntity<Transaction>(HttpStatus.OK);
	}
	
	@PostMapping("/deposit")
	public ResponseEntity<Transaction> deposit(@RequestBody Transaction transaction)
	{
		ResponseEntity<Double> entity = restTemplate.getForEntity("http://MMBank/accounts/"+transaction.getAccountNumber()+"/balance",Double.class);
		double currentBalance = entity.getBody();
		double updatedBalance = service.deposit(
				transaction.getAccountNumber(),transaction.getAmount(),currentBalance, transaction.getTransactionDetails());
		restTemplate.put("http://MMBank/accounts/"+transaction.getAccountNumber()+"?currentBalance="+updatedBalance, null);
		return new ResponseEntity<Transaction>(HttpStatus.OK);
	}
	
	@GetMapping("/getall")
	public CurrentDataSet getAll()
	{
		
		List<Transaction> transactions =  service.getAll();
		CurrentDataSet dataset = new CurrentDataSet();
		dataset.setTransaction(transactions);
		return dataset;
	}
}
