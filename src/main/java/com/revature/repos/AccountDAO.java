package com.revature.repos;

import java.util.List;
import java.util.Set;

import com.revature.models.Account;
import com.revature.models.AccountStatus;
import com.revature.models.AccountType;

public interface AccountDAO {
	
	boolean submitAccount(Account account);
	
	//boolean deleteAccount(Account account);
	
    boolean saveAccountBalance(Account account);
	
	Account getAccountById(Integer AccountId);
	
	Account getAccountByUserid(Integer UserId);
	
	Account getAccountByStatusId(Integer StatusId);
	
	boolean updateAccount(Account account);
	
	AccountStatus getAccountByStatus(Integer StatusId);
	
	AccountType getAccountByType(Integer TypeId);
	
	//Account findAccounts(Account account);
	
	public List <Account> showAllAccounts();
	
	public Account showSubmittedAccount(Integer UserId);
	
	

}
