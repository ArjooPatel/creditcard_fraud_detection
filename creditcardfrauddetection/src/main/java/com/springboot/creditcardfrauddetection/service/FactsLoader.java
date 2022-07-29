package com.springboot.creditcardfrauddetection.service;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.springboot.creditcardfrauddetection.model.CreditCardTransaction;


public class FactsLoader {



	public static Collection<CreditCardTransaction> loadTransactions() {

		List<CreditCardTransaction> ccts = new ArrayList<>();
		CreditCardTransaction transaction= new CreditCardTransaction();
		transaction.setId(1L);
		transaction.setAmount(new BigDecimal(8000.00));
		transaction.setCountry("INDIA");
		transaction.setCreditCardHolder("ARJOO PATEL");
		transaction.setCreditCardNumber(1234);
		transaction.setCvv(544);
		transaction.setExpiryMonth("Jan");
		transaction.setExpiryYear(2025);
		transaction.setTimestamp(1635453187933L);
		ccts.add(transaction);
		
		CreditCardTransaction transaction2= new CreditCardTransaction();
		transaction2.setId(2L);
		transaction2.setAmount(new BigDecimal(10000.00));
		transaction2.setCountry("INDIA");
		transaction2.setCreditCardHolder("ARJOO PATEL");
		transaction2.setCreditCardNumber(1234);
		transaction2.setCvv(544);
		transaction2.setExpiryMonth("Jan");
		transaction2.setExpiryYear(2025);
		transaction2.setTimestamp(1635453201193L);
		ccts.add(transaction2);
		
		CreditCardTransaction transaction3= new CreditCardTransaction();
		transaction3.setId(3L);
		transaction3.setAmount(new BigDecimal(12000.00));
		transaction3.setCountry("INDIA");
		transaction3.setCreditCardHolder("ARJOO PATEL");
		transaction3.setCreditCardNumber(1234);
		transaction3.setCvv(544);
		transaction3.setExpiryMonth("Jan");
		transaction3.setExpiryYear(2025);
		transaction3.setTimestamp(1635453213423L);
		ccts.add(transaction3);
		
		CreditCardTransaction transaction4= new CreditCardTransaction();
		transaction4.setId(4L);
		transaction4.setAmount(new BigDecimal(14000.00));
		transaction4.setCountry("INDIA");
		transaction4.setCreditCardHolder("ARJOO PATEL");
		transaction4.setCreditCardNumber(1234);
		transaction4.setCvv(544);
		transaction4.setExpiryMonth("Jan");
		transaction4.setExpiryYear(2025);
		transaction4.setTimestamp(1635453228262L);
		ccts.add(transaction4);
		
		CreditCardTransaction transaction5= new CreditCardTransaction();
		transaction5.setId(5L);
		transaction5.setAmount(new BigDecimal(16000.00));
		transaction5.setCountry("INDIA");
		transaction5.setCreditCardHolder("ARJOO PATEL");
		transaction5.setCreditCardNumber(1234);
		transaction5.setCvv(544);
		transaction5.setExpiryMonth("Jan");
		transaction5.setExpiryYear(2025);
		transaction5.setTimestamp(1635453239341L);
		ccts.add(transaction5);
		
		return ccts;
		
	}

}
