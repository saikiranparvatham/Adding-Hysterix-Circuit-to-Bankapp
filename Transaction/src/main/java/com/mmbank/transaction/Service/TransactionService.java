package com.mmbank.transaction.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.mmbank.transaction.pojo.Transaction;


public interface TransactionService {

	double withdraw(int accountNumber, double amount, double currentBalance, String transactionDetails);

	double deposit(Integer accountNumber, Double amount, double currentBalance, String transactionDetails);

	List<Transaction> getAll();
	
}
