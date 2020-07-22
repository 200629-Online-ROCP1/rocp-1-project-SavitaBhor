package com.revature.services;

import java.util.List;

import com.revature.models.Account;
import com.revature.repos.AccountDAO;
import com.revature.repos.AccountDAOImpl;


public class AccountService {
	

	AccountDAO accountDao = AccountDAOImpl.getInstance();
	
	public List<Account> showAllAccounts(){
		return accountDao.showAllAccounts();
	}
	
	public Account getAccountById(Integer AccountId) {
		return accountDao.getAccountById(AccountId);
	}
		
	public Account getAccountByStatusId(Integer StatusId) {
		return accountDao.getAccountByStatusId(StatusId);
	}
	public Account getAccountByUserid(Integer UserId) {
		return accountDao.getAccountByUserid(UserId);
	}
	
	public boolean submitAccount(Account account) {
		return accountDao.submitAccount(account);
	}
	
	public boolean updateAccount(Account account) {
		return accountDao.updateAccount(account);
	}
	
	public boolean saveAccountBalance(Account account) {
		return accountDao.updateAccount(account);
	}
	
	public Account showSubmittedAccount(Integer UserId) {
		return accountDao.showSubmittedAccount(UserId);
	}
	
	public boolean withdrawFromAccount(Integer accountId, Double amount) {
		
		Account account = accountDao.getAccountById(accountId);
		
		Double oldBalance = account.getBalance();
		Double newBalance = oldBalance - amount;
		
		if (newBalance < 0) {
			//throw error that balance cannot go negative
			return false;
		}
		account.setBalance(newBalance);
		//need to update account with new balance
		
		accountDao.saveAccountBalance(account);
		return true;
	}


	public boolean depositAccount(Integer accountId, Double amount) {
		
		Account account = accountDao.getAccountById(accountId);
		
		Double oldBalance = account.getBalance();
		Double newBalance = oldBalance + amount;
		account.setBalance(newBalance);
		//need to update account with new balance
		accountDao.saveAccountBalance(account);
		
		return true;
	}


	public boolean transferBetweenAccounts(Integer fromAccountId, Integer toAccountId, Double amount) {
		
		if(this.withdrawFromAccount(fromAccountId, amount)) {
			this.depositAccount(toAccountId, amount);
			return true;
		}else
		return false;

	}
	
	

}

