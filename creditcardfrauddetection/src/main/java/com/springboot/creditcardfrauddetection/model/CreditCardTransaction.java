package com.springboot.creditcardfrauddetection.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@Entity
@Table(name = "credit_card_transaction")
public class CreditCardTransaction{
	/**
	 * 
	 */


	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private long id;

	@Column(name = "creditCardNumber")
	private long creditCardNumber;

	@Column(name = "ammount")
	@JsonProperty("amountOfMoney")
	@JsonSerialize(using = MoneySerializer.class)
	private BigDecimal amount;

	//@Temporal(TemporalType.DATE)
	//@CreationTimestamp
	@Column(name = "created_time")
	private long timestamp = System.currentTimeMillis(); 

	@Column(name = "creditCardHolder")
	private String creditCardHolder;

	@Column(name = "cvv")
	private long cvv;

	@Column(name = "expiryMonth")
	private String expiryMonth;

	@Column(name = "expiryYear")
	private long expiryYear;

	@Column(name = "country")
	private String country;
	
	private Date created_Date;

	/*public CreditCardTransaction() {
		super();
		// TODO Auto-generated constructor stub
	}*/


	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getCreditCardNumber() {
		return creditCardNumber;
	}

	public void setCreditCardNumber(long creditCardNumber) {
		this.creditCardNumber = creditCardNumber;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

	public String getCreditCardHolder() {
		return creditCardHolder;
	}

	public void setCreditCardHolder(String creditCardHolder) {
		this.creditCardHolder = creditCardHolder;
	}

	public long getCvv() {
		return cvv;
	}

	public void setCvv(long cvv) {
		this.cvv = cvv;
	}

	public String getExpiryMonth() {
		return expiryMonth;
	}

	public void setExpiryMonth(String expiryMonth) {
		this.expiryMonth = expiryMonth;
	}

	public long getExpiryYear() {
		return expiryYear;
	}

	public void setExpiryYear(long expiryYear) {
		this.expiryYear = expiryYear;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public Date getCreated_Date() {
		return created_Date;
	}

	public void setCreated_Date(Date created_Date) {
		this.created_Date = created_Date;
	}

	@Override
	public String toString() {
		return "CreditCardTransaction [id=" + id + ", creditCardNumber=" + creditCardNumber + ", amount=" + amount
				+ ", timestamp=" + timestamp + ", creditCardHolder=" + creditCardHolder + ", cvv=" + cvv
				+ ", expiryMonth=" + expiryMonth + ", expiryYear=" + expiryYear + ", country=" + country + "]";
	}

}

