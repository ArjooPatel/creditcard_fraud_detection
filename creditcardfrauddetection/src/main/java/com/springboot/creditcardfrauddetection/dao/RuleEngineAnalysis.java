package com.springboot.creditcardfrauddetection.dao;


import java.util.List;

import com.springboot.creditcardfrauddetection.model.CreditCardTransaction;
import com.springboot.creditcardfrauddetection.model.DetectedFraudsReport;



public interface RuleEngineAnalysis {

	List<DetectedFraudsReport> processTransaction(CreditCardTransaction ccTransaction)  ;

}