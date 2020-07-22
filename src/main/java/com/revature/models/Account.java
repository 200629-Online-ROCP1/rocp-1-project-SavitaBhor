package com.revature.models;

import com.revature.repos.AccountDAO;
import com.revature.repos.AccountDAOImpl;

public class Account {
	 
	  private int accountId; // primary key
	  private double balance;  // not null
	  private AccountStatus status;
	  private AccountType type;
	  private int userId;
	  
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
	
	public Account(int accountId, double balance, int status, int type, int userId) {
		super();
		this.accountId = accountId;
		this.balance = balance;
		AccountDAO accountDao = AccountDAOImpl.getInstance();
		this.status = accountDao.getAccountByStatus(status);
		this.type = accountDao.getAccountByType(type);
		this.userId = userId;
	}
	public Account(int accountId, double balance, int status, int type) {
		super();
		this.accountId = accountId;
		this.balance = balance;
		AccountDAO accountDao = AccountDAOImpl.getInstance();
		this.status = accountDao.getAccountByStatus(status);
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
	
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + accountId;
		long temp;
		temp = Double.doubleToLongBits(balance);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Account other = (Account) obj;
		if (accountId != other.accountId)
			return false;
		if (Double.doubleToLongBits(balance) != Double.doubleToLongBits(other.balance))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Account [accountId=" + accountId + ", balance=" + balance + ", status=" + status.getStatus() + ", type=" + type.getType()
				+ "]";
	}
	
	

}
