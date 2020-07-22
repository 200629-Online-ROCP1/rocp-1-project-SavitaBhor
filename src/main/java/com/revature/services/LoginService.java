package com.revature.services;

import com.revature.models.LoginDTO;
import com.revature.models.User;
import com.revature.repos.UserDAO;
import com.revature.repos.UserDAOImpl;

public class LoginService {
	
	private static final UserDAO dao = new UserDAOImpl();
	
	public boolean login(LoginDTO l) {
		
		User u = dao.getUserByUserName(l.username);
		if(u!=null) {
			if(l.username.equals(u.getUsername()) && l.password.equals(u.getPassword())) {
				return true; 
			}
		}
		return false; 
	}
	
	public User getUserByUserName(String username) {
		return dao.getUserByUserName(username);
	}

}
