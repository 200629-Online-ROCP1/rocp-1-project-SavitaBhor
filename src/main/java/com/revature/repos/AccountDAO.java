package com.revature.repos;

import java.util.Set;

import com.revature.models.Account;
import com.revature.models.AccountStatus;
import com.revature.models.AccountType;

public interface AccountDAO {
	
	boolean submitAccount(Account account);
	
	//boolean deleteAccount(Account account);
	
    //boolean saveAccount(Account account);
	
	Account getAccountById(Integer AccountId);
	
	Account getAccountByUserid(Integer UserId);
	
	boolean updateAccount(Account account);
	
	AccountStatus getAccountByStatus(Integer StatusId);
	
	AccountType getAccountByType(Integer TypeId);
	
	Account findAccounts(Account account);
	
	public Set <Account> showAllAccounts();
	
	

}
