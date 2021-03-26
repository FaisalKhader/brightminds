package com.brightminds.assignment.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.brightminds.assignment.util.DateUtils;

	
@Entity
@Table(name ="STATEMENT")
public class Statement {

	@Id
	@GeneratedValue
	@Column(name = "ID")
	private Long id;
	
	@Column(name = "ACCOUNT_ID")
	private Long accountId;
	
	@Column(name = "DATEFIELD")
	private String statmentDate;
	
	@Column(name = "AMOUNT")
	private String amount;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getAccountId() {
		
		return accountId	;
	}

	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}

	public LocalDate getStatmentDate() {
		return DateUtils.getStringAsDate(statmentDate);
	}

	public void setStatmentDate(String statmentDate) {
		this.statmentDate = statmentDate;
	}

	public Double getAmount() {
		return Double.parseDouble(amount);
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}
	
	
	
}
