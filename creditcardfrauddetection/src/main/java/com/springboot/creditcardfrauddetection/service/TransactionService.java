package com.springboot.creditcardfrauddetection.service;

import java.util.Collection;
import java.util.List;

import org.springframework.data.domain.Page;

import com.springboot.creditcardfrauddetection.model.CreditCardTransaction;
import com.springboot.creditcardfrauddetection.model.DetectedFraudsReport;




public interface TransactionService {
	
	
	
	CreditCardTransaction createTransaction(CreditCardTransaction transaction);
	
	/**
	 * Retrieves all credit-card transactions of the card with the given number.
	 * 
	 * @param creditCardNumber
	 * @return
	 */
	Collection<CreditCardTransaction> getCreditCardTransactionsForCC(Long creditCardNumber,Long ID);
	
	Long totalcreditCardTransactionsByCreditCardNumber(Long creditCardNumber);
	
	Page<CreditCardTransaction> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);
	
	List<DetectedFraudsReport> getDetectedFraudReport(Long id) ;
	



}
