package com.revature.services;

import java.util.List;

import com.revature.models.User;
import com.revature.repos.UserDAO;
import com.revature.repos.UserDAOImpl;
import com.revature.util.ValidationUtil;


public class UserService{
	
	UserDAO userDao = UserDAOImpl.getInstance();
	


	public List<User> getAllUsers() {
		return userDao.getAllUsers();
	}


	public User getUserById(Integer user_id) {
		return userDao.getUserById(user_id);
	}


	public boolean registerUser(User user) {
//	
//		List<User> list =  getAllUsers();
//		for(User u: list) {
//			
//			if(u.getUsername().equals(user.getUsername()) || u.getEmail().equals(user.getEmail())) {
//				return false;
//			}
//		}
//		boolean b= userDao.insertUser(user);
//		return b;
		return userDao.insertUser(user);
	}



}