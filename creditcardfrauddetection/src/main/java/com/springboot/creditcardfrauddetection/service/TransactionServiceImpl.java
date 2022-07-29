package com.springboot.creditcardfrauddetection.service;

import java.util.Collection;
import java.util.List;

import javax.transaction.Transactional;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.springboot.creditcardfrauddetection.dao.RuleEngineAnalysis;
import com.springboot.creditcardfrauddetection.dao.TransactionDAO;
import com.springboot.creditcardfrauddetection.model.CreditCardTransaction;
import com.springboot.creditcardfrauddetection.model.DetectedFraudsReport;
import com.springboot.creditcardfrauddetection.repository.TransactionRepository;



@Transactional
@Service
public class TransactionServiceImpl implements TransactionService {

	private static final Logger LOGGER = LoggerFactory.getLogger(TransactionServiceImpl.class);
 
	@Autowired
	TransactionRepository transactionRepository;
	
	@Autowired
	TransactionDAO transactionDAO;
	
	@Autowired
	RuleEngineAnalysis ruleEngineAnalysis;
	
	@Override
	public CreditCardTransaction createTransaction(CreditCardTransaction transaction) {
		return this.transactionRepository.save(transaction);
	}

	@Override
	public Collection<CreditCardTransaction> getCreditCardTransactionsForCC(Long creditCardNumber,Long ID) {
		// TODO Auto-generated method stub
		return transactionDAO.getCreditCardTransactionsForCC(creditCardNumber,ID);
	}

	@Override
	public Long totalcreditCardTransactionsByCreditCardNumber(Long creditCardNumber) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<CreditCardTransaction> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {
		Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
			Sort.by(sortField).descending();
		
		Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
		return this.transactionRepository.findAll(pageable);
	
	}

	@Override
	public List<DetectedFraudsReport> getDetectedFraudReport(Long id) {
		CreditCardTransaction transaction= transactionRepository.getById(id);
		List<DetectedFraudsReport>  reportlist=ruleEngineAnalysis.processTransaction(transaction);
		return reportlist;
	}
	

}
