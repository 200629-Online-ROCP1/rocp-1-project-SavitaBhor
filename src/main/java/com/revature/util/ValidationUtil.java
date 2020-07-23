package com.revature.util;

import java.util.List;

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

	public boolean isValidRole(String username, String role){
		
	    User user = userDao.getUserByUserName(username);
	    if(user != null) {

	    	if(role.equalsIgnoreCase(user.getRole().getRole())) {
	    		
	    		return true;
	    	}else
	    		return false;
	    }else
	    return false;
	}
	
	public boolean isIdMatchesCurrentUser(String username,Integer uid) {
		
		User user = userDao.getUserByUserName(username);
		if(user != null) {
	    	if(uid == user.getUserId()) {
	    		return true;
	    	}else
	    		return false;
	    }else
	    return false;
				
	}

	public boolean isOwnerOfAccount(String username,int accntId){
		
		User user = userDao.getUserByUserName(username);
		Account account = accDao.getAccountByUserid(user.getUserId());
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
	public boolean usernameEmailAlreadyExist(User user) {
		List<User> list = userDao.getAllUsers();
		for (User u : list) {

			if ((u.getUsername().equals(user.getUsername()) 
					|| u.getEmail().equals(user.getEmail())) 
					&& (u.getUserId() != user.getUserId())) {
				return true;
			}
		}
		return false;
	}
	
	public boolean isUserIdExists(int uid) {
		List<User> list = userDao.getAllUsers();
		for (User u : list) {

			if (u.getUserId() == uid){
				return true;
			}
		}
		return false;
		
	}
	
	

}
