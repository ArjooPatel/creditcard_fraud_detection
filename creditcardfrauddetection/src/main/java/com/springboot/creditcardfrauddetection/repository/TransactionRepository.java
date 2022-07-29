package com.springboot.creditcardfrauddetection.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springboot.creditcardfrauddetection.model.CreditCardTransaction;


@Repository
public interface TransactionRepository extends JpaRepository<CreditCardTransaction, Long> {
	
	

}
