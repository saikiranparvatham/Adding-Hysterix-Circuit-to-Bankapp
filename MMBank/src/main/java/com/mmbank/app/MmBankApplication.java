package com.mmbank.app;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.mmbank.app.accounts.CurrentAccount;
import com.mmbank.app.accounts.SavingsAccount;
import com.mmbank.app.repo.AccountRepository;

@SpringBootApplication
public class MmBankApplication {

	public static void main(String[] args) {
		SpringApplication.run(MmBankApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner populateData(AccountRepository repository)
	{
		
		return (arg) -> {
			repository.save(new SavingsAccount(101,"Ramesh",true));
			repository.save(new SavingsAccount(102,"Suresh",15625.00,true));			
			repository.save(new CurrentAccount(103,"Kumar",12000.00,1500.00));
			repository.save(new CurrentAccount(104,"Shankar",50000.00,2500.00));
		};
	}
}

