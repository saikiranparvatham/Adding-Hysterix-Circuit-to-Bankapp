package com.mmbank.transaction.Service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.mmbank.transaction.pojo.Transaction;
import com.mmbank.transaction.pojo.TransactionType;
import com.mmbank.transaction.repo.TransactionRepository;

@Service
public class TransactionServiceImpl implements TransactionService {
	
	@Autowired
	private TransactionRepository repository;

@Override
public double withdraw(int accountNumber, double amount, double currentBalance, String transactionDetails) {

	Transaction transaction = new Transaction();
	transaction.setAccountNumber(accountNumber);
	transaction.setAmount(amount);
	currentBalance = currentBalance - amount;
	transaction.setCurrentBalance(currentBalance);
	transaction.setTransactionDetails(transactionDetails);
	transaction.setTransactionType(TransactionType.WITHDRAW);
	transaction.setTransactionDate(LocalDateTime.now());
	repository.save(transaction);
	return currentBalance;
}

@Override
public double deposit(Integer accountNumber, Double amount, double currentBalance, String transactionDetails) {

	Transaction transaction = new Transaction();
	transaction.setAccountNumber(accountNumber);
	transaction.setAmount(amount);
	currentBalance = currentBalance + amount;
	transaction.setCurrentBalance(currentBalance);
	transaction.setTransactionDetails(transactionDetails);
	transaction.setTransactionType(TransactionType.DEPOSIT);
	transaction.setTransactionDate(LocalDateTime.now());
	repository.save(transaction);
	return currentBalance;
}

@Override
public List<Transaction> getAll() {
	return repository.findAll();
}
}
