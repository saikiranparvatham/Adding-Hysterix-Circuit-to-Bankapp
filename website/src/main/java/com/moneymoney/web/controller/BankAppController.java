package com.moneymoney.web.controller;

import java.util.ArrayList;
import java.util.List;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.hateoas.Link;


import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.moneymoney.web.entity.Transaction;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;


@EnableDiscoveryClient
@Controller
@Service
public class BankAppController {
	
	@Autowired
	private RestTemplate restTemplate;
	
	@RequestMapping("/")
	public String homeForm() {
		return "Home";
	}
	
	
	@RequestMapping("/withdrawForm")
	public String depositForm() {
		return "WithdrawForm";
	}
	
	@HystrixCommand(fallbackMethod = "reliable")
	@RequestMapping("/withdraw")
	public String deposit(@ModelAttribute Transaction transaction,
			Model model) {
		restTemplate.postForEntity("http://Transaction/transactions/withdraw", 
				transaction, null);
		model.addAttribute("message","Success!");
		return "WithdrawForm";
	}
	
	@RequestMapping("/depositForm")
	public String withdrawForm() {
		return "DepositForm";
	}
	
	@HystrixCommand(fallbackMethod = "reliable")
	@RequestMapping("/deposit")
	public String withdraw(@ModelAttribute Transaction transaction,
			Model model) {
		restTemplate.postForEntity("http://Transaction/transactions/deposit", 
				transaction, null);
		model.addAttribute("message","Success!");
		return "DepositForm";
	}
	
	@RequestMapping("/fundtransferForm")
	public String fundtransferForm() {
		return "FundTransferForm";
	}
	
	@HystrixCommand(fallbackMethod = "reliable")
	@RequestMapping("/fundtransfer")
	public String fundtransfer(@RequestParam("senderaccountNumber")int senderaccountNumber,@RequestParam("receiveraccountNumber")int receiveraccountNumber,@ModelAttribute Transaction transaction,
			Model model) {
		transaction.setAccountNumber(senderaccountNumber);
		restTemplate.postForEntity("http://Transaction/transactions/withdraw", 
				transaction, null);
		transaction.setAccountNumber(receiveraccountNumber);
		restTemplate.postForEntity("http://Transaction/transactions/deposit", 
				transaction, null);
		model.addAttribute("message","Success!");
		return "FundTransferForm";
						}
	@HystrixCommand(fallbackMethod = "reliable")
	@RequestMapping("/gettransactions")
	public ModelAndView gettransactions(@RequestParam("offset")int offset,@RequestParam("size")int size)
	{
		CurrentDataSet dataset = restTemplate.getForObject("http://Transaction/transactions/getall", CurrentDataSet.class); 
		int currentsize=size==0?3:size;
		int currentoffset = offset==0?1:offset;
		Link next= linkTo(methodOn(BankAppController.class).gettransactions(currentoffset+currentsize, currentsize)).withRel("next");
		Link prev= linkTo(methodOn(BankAppController.class).gettransactions(currentoffset-currentsize, currentsize)).withRel("prev");
		List<Transaction> currentDataSet=new ArrayList<Transaction>();
		for(int i=currentoffset-1;i<currentsize+currentoffset-1;i++)
		{
			List<Transaction> transactions = dataset.getTransaction();
			Transaction transaction=transactions.get(i);
			currentDataSet.add(transaction);
		}
		CurrentDataSet datasetList=new CurrentDataSet(currentDataSet,next,prev);
		return new ModelAndView("DepositForm","currentDataSet",datasetList);				
	}
	
	  public String reliable(@ModelAttribute Transaction transaction,
				Model model) {
		    return "ErrorPage";
		  }
}
