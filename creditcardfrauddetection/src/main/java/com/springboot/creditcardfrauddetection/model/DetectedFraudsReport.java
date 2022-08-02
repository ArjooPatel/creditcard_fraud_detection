package com.springboot.creditcardfrauddetection.model;

import java.util.Date;

public class DetectedFraudsReport {
	
	private long creditCardNumber;
	private String message;
	private long transaction_id;
	private Date transaction_time;
	private int disputeRiskRating;
	private int cardHolderRiskRating;
	
	
	public DetectedFraudsReport(long creditCardNumber, String message, long transaction_id, Date transaction_time) {
		super();
		this.creditCardNumber = creditCardNumber;
		this.message = message;
		this.transaction_id = transaction_id;
		this.transaction_time = transaction_time;
	}

	public DetectedFraudsReport(long creditCardNumber, String message, long transaction_id, Date transaction_time,
			int disputeRiskRating) {
		super();
		this.creditCardNumber = creditCardNumber;
		this.message = message;
		this.transaction_id = transaction_id;
		this.transaction_time = transaction_time;
		this.disputeRiskRating = disputeRiskRating;
	}

	public DetectedFraudsReport() {
		super();
	}

	public long getCreditCardNumber() {
		return creditCardNumber;
	}

	public void setCreditCardNumber(long creditCardNumber) {
		this.creditCardNumber = creditCardNumber;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public long getTransaction_id() {
		return transaction_id;
	}

	public void setTransaction_id(long transaction_id) {
		this.transaction_id = transaction_id;
	}

	public Date getTransaction_time() {
		return transaction_time;
	}

	public void setTransaction_time(Date transaction_time) {
		this.transaction_time = transaction_time;
	}

	public int getDisputeRiskRating() {
		return disputeRiskRating;
	}

	public void setDisputeRiskRating(int disputeRiskRating) {
		this.disputeRiskRating = disputeRiskRating;
	}

	public int getCardHolderRiskRating() {
		return cardHolderRiskRating;
	}

	public void setCardHolderRiskRating(int cardHolderRiskRating) {
		this.cardHolderRiskRating = cardHolderRiskRating;
	}

	@Override
	public String toString() {
		return "DetectedFraudsReport [creditCardNumber=" + creditCardNumber + ", message=" + message
				+ ", transaction_id=" + transaction_id + ", transaction_time=" + transaction_time + "]";
	}
	
	
	
	
	
/*	private final Map<String, Collection<CreditCardTransaction>> detectedFrauds = new HashMap<>();
	
	
	public DetectedFraudsReport(long creditCardNumber) {
		this.creditCardNumber = creditCardNumber;
	}
	
	public long getCreditCardNumber() {
		return creditCardNumber;
	}

	public Map<String, Collection<CreditCardTransaction>> getDetectedFrauds() {
		return detectedFrauds;
	}

	public void addDetectedFraud(String message, Collection<CreditCardTransaction> ccTransactions) {
		detectedFrauds.put(message, ccTransactions);
	}
	
	*/
}

