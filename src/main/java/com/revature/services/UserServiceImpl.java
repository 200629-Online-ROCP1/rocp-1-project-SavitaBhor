package com.revature.services;

import com.revature.models.User;
import com.revature.repos.UserDAO;
import com.revature.repos.UserDAOImpl;


public class UserServiceImpl implements UserService{
	
	UserDAO userDao = UserDAOImpl.getInstance();


	@Override
	public boolean isLoginValid(String userName, String password) {
		
		User user = userDao.getUserByUserName(userName);
		System.out.println(user.getUsername());
		if(password.equals(user.getPassword())) {
			System.out.println("correct");
			return true;
		}
		return false;
	}

	@Override
	public boolean isUserAdmin(Integer user_id) {
	
		return false;
	}

	@Override
	public boolean isUserEmployee(Integer user_id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isUserStandard(Integer user_id) {
		// TODO Auto-generated method stub
		return false;
	}

}
