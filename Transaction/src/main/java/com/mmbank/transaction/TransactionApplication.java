package com.mmbank.transaction;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import com.mmbank.transaction.Service.TransactionService;
import com.mmbank.transaction.repo.TransactionRepository;




@SpringBootApplication
public class TransactionApplication {

	public static void main(String[] args) {
		SpringApplication.run(TransactionApplication.class, args);
	}
	@Autowired
	TransactionService service;
	
	
	@Bean
	@LoadBalanced
	   public RestTemplate getRestTemplate() {
	      return new RestTemplate();
	   }
	@Bean
	public CommandLineRunner getCommand(TransactionRepository repository)
	{
		return (evt) ->{
		
		service.deposit(101, 1000.0, 58, "ATM");
		service.deposit(102, 1500.0, 53, "Website");
		service.deposit(103, 1800.0, 54, "Other Account");
		service.deposit(104, 2000.0, 50, "Bank");
		};
	
}
}

