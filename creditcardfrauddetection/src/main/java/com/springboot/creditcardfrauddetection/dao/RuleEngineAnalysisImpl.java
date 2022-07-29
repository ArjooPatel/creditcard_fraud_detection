package com.springboot.creditcardfrauddetection.dao;



import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.TimeUnit;


import org.kie.api.KieServices;

import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import org.kie.api.runtime.rule.EntryPoint;
import org.kie.api.runtime.rule.FactHandle;
import org.kie.api.time.SessionClock;
import org.kie.api.time.SessionPseudoClock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.springboot.creditcardfrauddetection.model.CreditCardTransaction;
import com.springboot.creditcardfrauddetection.model.DetectedFraudsReport;



@Repository("ruleEngineAnalysis")
public class RuleEngineAnalysisImpl implements RuleEngineAnalysis {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(RuleEngineAnalysisImpl.class);
 

	@Autowired
	TransactionDAO transactionDAO;

	@Override
	public  List<DetectedFraudsReport> processTransaction(CreditCardTransaction ccTransaction)  {

	// Retrieve all transactions for this account
		Collection<CreditCardTransaction> ccTransactions = transactionDAO
				.getCreditCardTransactionsForCC(ccTransaction.getCreditCardNumber(),ccTransaction.getId());

		
		
		System.out.println("process Transaction");
		//Collection<CreditCardTransaction> ccTransactions = FactsLoader.loadTransactions();

		LOGGER.debug("Found '" + ccTransactions.size() + "' transactions for creditcard: '" + ccTransaction.getCreditCardNumber() + "'.");
		KieServices kieServices = KieServices.Factory.get();
		KieContainer kContainer = kieServices.newKieClasspathContainer();
		KieSession kieSession = kContainer.newKieSession();
		// Insert transaction history/context.
		LOGGER.debug("Inserting credit-card transaction context into session.");
		List<DetectedFraudsReport> reportList = new ArrayList<>();
		kieSession.setGlobal( "reportList", reportList );
		for (CreditCardTransaction nextTransaction : ccTransactions) {
			insert(kieSession, "Transactions", nextTransaction);
		}
		// Insert the new transaction event
		System.out.println("Inserting credit-card transaction event into session.");
		insert(kieSession, "Transactions", ccTransaction);
		// And fire the rules.
		int firedRule=kieSession.fireAllRules();
		System.out.println(firedRule);
		System.out.println("Generated Report: "+reportList);

		// Dispose the session to free up the resources.
		kieSession.dispose();
		return reportList;
	}
	
	private static FactHandle insert(KieSession kieSession, String stream, CreditCardTransaction cct) {
		SessionClock clock = kieSession.getSessionClock();
		if (!(clock instanceof SessionPseudoClock)) {
			String errorMessage = "This fact inserter can only be used with KieSessions that use a SessionPseudoClock";
			LOGGER.error(errorMessage);
			throw new IllegalStateException(errorMessage);
		}
		SessionPseudoClock pseudoClock = (SessionPseudoClock) clock;
		EntryPoint ep = kieSession.getEntryPoint(stream);

		// First insert the event
		FactHandle factHandle = ep.insert(cct);
		// And then advance the clock.

		long advanceTime = cct.getTimestamp() - pseudoClock.getCurrentTime();
		if (advanceTime > 0) {
			LOGGER.debug("Advancing the PseudoClock with " + advanceTime + " milliseconds.");
			pseudoClock.advanceTime(advanceTime, TimeUnit.MILLISECONDS);
		} else {
			// Print a warning when we don't need to advance the clock. This usually means that the events are entering the system in the
			// incorrect order.
			LOGGER.warn("Not advancing time. CreditCardTransaction timestamp is '" + cct.getTimestamp() + "', PseudoClock timestamp is '"
					+ pseudoClock.getCurrentTime() + "'.");
		}
		return factHandle;
	}

}

