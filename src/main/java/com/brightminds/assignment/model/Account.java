package com.brightminds.assignment.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.codec.digest.DigestUtils;

@Entity
@Table(name = "Account")
public class Account {
	

	@Id	
	@GeneratedValue
	@Column(name = "ID")
	private int id;
	
	@Column(name = "ACCOUNT_TYPE")
	private String accountType;
	
	@Column(name = "ACCOUNT_NUMBER")
	private String accountNumber;

	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public String getAccountNumber() {
		return DigestUtils.sha256Hex(accountNumber);
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	
	

}
