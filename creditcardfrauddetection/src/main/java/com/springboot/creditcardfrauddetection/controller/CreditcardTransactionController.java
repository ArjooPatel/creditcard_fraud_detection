package com.springboot.creditcardfrauddetection.controller;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.springboot.creditcardfrauddetection.model.CreditCardTransaction;
import com.springboot.creditcardfrauddetection.model.DetectedFraudsReport;
import com.springboot.creditcardfrauddetection.service.TransactionService;





@Controller
public class CreditcardTransactionController {
	
	@Autowired 
	TransactionService transactionService;
	
	
	@GetMapping("/payment")
	public String payment(Model model) {
		// create model attribute to bind form data
		CreditCardTransaction transaction = new CreditCardTransaction();
		model.addAttribute("transaction", transaction);
		List<String> listCountries = Arrays.asList("CANADA","INDIA", "US", "UK","NL","ES","FR","DE");
        model.addAttribute("listCountries", listCountries);
        List<Long> listYears = Arrays.asList(2021L,2022L,2023L,2024L,2025L,2026L,2027L,2028L,2029L,2030L);
        model.addAttribute("listYears", listYears);
        List<String> listMonths = Arrays.asList("Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec");
        model.addAttribute("listMonths", listMonths);
		return "payment";
	}


	@PostMapping("/transactions")
	public String createTransaction(@ModelAttribute("transaction") CreditCardTransaction transaction){
		transactionService.createTransaction(transaction);
		return "redirect:/";
	}
	
	@GetMapping("/")
	public String viewHomePage(Model model) {
		return findPaginated(1, "id", "asc", model);		
	}
	
	
	
	@GetMapping("/page/{pageNo}")
	public String findPaginated(@PathVariable (value = "pageNo") int pageNo, 
			@RequestParam("sortField") String sortField,
			@RequestParam("sortDir") String sortDir,
			Model model) {
		int pageSize = 5;
		
		Page<CreditCardTransaction> page = transactionService.findPaginated(pageNo, pageSize, sortField, sortDir);
		List<CreditCardTransaction> listTransaction = page.getContent();
		for(CreditCardTransaction transaction:listTransaction){
			long timestamp= transaction.getTimestamp();
			transaction.setCreated_Date(new Date(timestamp));
		}
		model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems", page.getTotalElements());
		
		model.addAttribute("sortField", sortField);
		model.addAttribute("sortDir", sortDir);
		model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
		
		model.addAttribute("listTransaction", listTransaction);
		return "index";
	}
	
	
	
	
	
	@CrossOrigin
	@RequestMapping(value="/detectedfraudreport/{id}", method=RequestMethod.GET)
	public String getDetectedFraudReport(@PathVariable Long id,Model model){
		List<DetectedFraudsReport> listReport = transactionService.getDetectedFraudReport(id);
		model.addAttribute("listReport", listReport);
		return "fraud-report";
		}
		
		//return ResponseEntity.ok().body(transactionService.getCreditCardTransactionsForCC(creditCardNumber));

}
