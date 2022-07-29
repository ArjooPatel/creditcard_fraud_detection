package com.springboot.creditcardfrauddetection.dao;
import java.util.Collection;

import com.springboot.creditcardfrauddetection.model.CreditCardTransaction;


public interface TransactionDAO {
	
	public Collection<CreditCardTransaction> getCreditCardTransactionsForCC(Long creditCardNumber,Long transactionId);
	
	Long totalcreditCardTransactionsByCreditCardNumber(Long creditCardNumber);
	
	public CreditCardTransaction getCreditCardTransactionById(Long id);

}
