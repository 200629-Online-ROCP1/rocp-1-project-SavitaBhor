package com.revature.services;

import java.util.List;

import com.revature.models.User;
import com.revature.repos.UserDAO;
import com.revature.repos.UserDAOImpl;


public class UserService {

	UserDAO userDao = UserDAOImpl.getInstance();

	public List<User> getAllUsers() {
		return userDao.getAllUsers();
	}

	public User getUserById(Integer user_id) {
		return userDao.getUserById(user_id);
	}

	public boolean registerUser(User user) {

		return userDao.insertUser(user);
	}

//	public boolean usernameEmailAlreadyExist(User user) {
//		List<User> list = getAllUsers();
//		for (User u : list) {
//
//			if (u.getUsername().equals(user.getUsername()) || u.getEmail().equals(user.getEmail())) {
//				return true;
//			}
//		}
//		return false;
//	}

	public boolean updateUser(User user) {
		return userDao.updateUser(user);
	}
	
	public User getUserByUserName(String username) {
		return userDao.getUserByUserName(username);
	}

}