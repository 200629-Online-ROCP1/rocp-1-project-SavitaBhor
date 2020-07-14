package com.revature.models;

import com.revature.repos.AccountDAO;
import com.revature.repos.AccountDAOImpl;

public class Account {
	 
	  private int accountId; // primary key
	  private double balance;  // not null
	  private AccountStatus status;
	  private AccountType type;
	  
	public Account() {
		super();
			// TODO Auto-generated constructor stub
	} 
	public Account(int accountId, double balance) {
		super();
		this.accountId = accountId;
		this.balance = balance;
	}
	public Account(int accountId, double balance, AccountStatus status, AccountType type) {
		super();
		this.accountId = accountId;
		this.balance = balance;
		this.status = status;
		this.type = type;
	}
	
	public Account(int accountId, double balance, int status, int type) {
		super();
		this.accountId = accountId;
		this.balance = balance;
		AccountDAO accountDao = AccountDAOImpl.getInstance();
		
		this.status = accountDao.getAccountByStatus(status);
//		System.out.println(this.status );
		this.type = accountDao.getAccountByType(type);
	}
	public int getAccountId() {
		return accountId;
	}
	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	public AccountStatus getStatus() {
		return status;
	}
	public void setStatus(AccountStatus status) {
		this.status = status;
	}
	public AccountType getType() {
		return type;
	}
	public void setType(AccountType type) {
		this.type = type;
	}
	
	

}
