package com.springboot.creditcardfrauddetection.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import javax.sql.DataSource;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.springboot.creditcardfrauddetection.model.CreditCardTransaction;



@Transactional
@Repository("transactionDAO")
public class TransactionDAOImpl implements TransactionDAO {
	
	@Autowired
	private DataSource dataSource;

	@Override
	public Collection<CreditCardTransaction> getCreditCardTransactionsForCC(Long creditCardNumber, Long transactionId) {

		Collection<CreditCardTransaction> transactionList = new ArrayList<CreditCardTransaction>();

		String query;
		query = "SELECT * from CREDIT_CARD_TRANSACTION where CREDIT_CARD_TRANSACTION.CREDIT_CARD_NUMBER = ? and CREDIT_CARD_TRANSACTION.ID != ?";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setLong(1, creditCardNumber);
			ps.setLong(2, transactionId);

			CreditCardTransaction transaction = null;
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				transaction = new CreditCardTransaction();
				transaction.setId(rs.getLong("id"));
				transaction.setAmount(rs.getBigDecimal("ammount"));
				transaction.setCreditCardNumber(rs.getLong("CREDIT_CARD_NUMBER"));
				transaction.setTimestamp(rs.getLong("CREATED_TIME"));
				transaction.setCountry(rs.getString("COUNTRY"));
				transaction.setCreditCardHolder(rs.getString("CREDIT_CARD_HOLDER"));
				transaction.setCvv(rs.getLong("CVV"));
				transaction.setExpiryMonth(rs.getString("EXPIRY_MONTH"));
				transaction.setExpiryYear(rs.getLong("EXPIRY_YEAR"));
				transactionList.add(transaction);
			}
			rs.close();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
				}
			}
		}
		return transactionList;
	}

	@Override
	public CreditCardTransaction getCreditCardTransactionById(Long id) {
		CreditCardTransaction transaction = null;
		String query;
		query = "SELECT * from CREDIT_CARD_TRANSACTION where CREDIT_CARD_TRANSACTION.ID = ?";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setLong(1, id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				transaction = new CreditCardTransaction();
				transaction.setId(id);
				transaction.setAmount(rs.getBigDecimal("ammount"));
				transaction.setCreditCardNumber(rs.getLong("CREDIT_CARD_NUMBER"));
				transaction.setTimestamp(rs.getLong("CREATED_TIME"));
				transaction.setCountry(rs.getString("COUNTRY"));
				transaction.setCreditCardHolder(rs.getString("CREDIT_CARD_HOLDER"));
				transaction.setCvv(rs.getLong("CVV"));
				transaction.setExpiryMonth(rs.getString("EXPIRY_MONTH"));
				transaction.setExpiryYear(rs.getLong("EXPIRY_YEAR"));
			}
			rs.close();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
				}
			}
		}
		return transaction;
	}
	
	@Override
	public Long totalcreditCardTransactionsByCreditCardNumber(Long creditCardNumber) {
		// TODO Auto-generated method stub
		return null;
	}

}
