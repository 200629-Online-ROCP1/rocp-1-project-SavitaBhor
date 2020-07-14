package com.revature.services;

import com.revature.models.Account;

public interface AccountService {
	
	boolean withdrawFromAccount(Integer accountId, Double amount);
	
	boolean depositAccount(Integer accountId, Double amount);
	
	boolean transferBetweenAccounts(Integer fromAccountId, Integer toAccountId, Double amount);

}
