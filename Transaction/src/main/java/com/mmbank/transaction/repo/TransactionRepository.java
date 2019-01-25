package com.mmbank.transaction.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mmbank.transaction.pojo.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Integer>{

}
