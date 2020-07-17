package com.revature.util;

import com.revature.models.Account;
import com.revature.models.User;
import com.revature.repos.AccountDAOImpl;
import com.revature.repos.UserDAOImpl;

public class ValidationUtil {
	
	 UserDAOImpl userDao = new UserDAOImpl();
	 AccountDAOImpl accDao = new AccountDAOImpl();
	 
	
	public ValidationUtil() {
		super();
		// TODO Auto-generated constructor stub
	}

	public boolean isValidRole(int userId, String role){
		   
	   
	    User user = userDao.getUserById(userId);
	    return role == user.getRole().getRole();
	}

	public boolean isOwner(int accntId, int UserId){
		
	    //for this yUserId u get XuserId
		
		Account account = accDao.getAccountByUserid(UserId);
		if(account != null) {
			if(account.getAccountId() == accntId ){
	    	
				return true ;
			}
			else{
				return false;
			}
		}
		else
			return false;
	}

}
