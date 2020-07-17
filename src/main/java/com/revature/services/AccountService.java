package com.revature.services;

import com.revature.models.Account;
import com.revature.repos.AccountDAO;
import com.revature.repos.AccountDAOImpl;


public class AccountService {
	

	AccountDAO accountDao = AccountDAOImpl.getInstance();
		

	public boolean withdrawFromAccount(Integer accountId, Double amount) {
		//TODO: Get account from DB

		Account account = accountDao.getAccountById(accountId);
		
		Double oldBalance = account.getBalance();
		Double newBalance = oldBalance - amount;
		
		if (newBalance < 0) {
			//throw error that balance cannot go negative
			return false;
		}
		account.setBalance(newBalance);
		//need to update account with new balance
		accountDao.updateAccount(account);
		return true;
	}


	public boolean depositAccount(Integer accountId, Double amount) {
		
		Account account = accountDao.getAccountById(accountId);
		
		Double oldBalance = account.getBalance();
		Double newBalance = oldBalance + amount;
		account.setBalance(newBalance);
		//need to update account with new balance
		accountDao.updateAccount(account);
		
		return true;
	}


	public boolean transferBetweenAccounts(Integer fromAccountId, Integer toAccountId, Double amount) {
		
		this.withdrawFromAccount(fromAccountId, amount);
		this.depositAccount(toAccountId, amount);
		
		return true;

	}
	
	

}

