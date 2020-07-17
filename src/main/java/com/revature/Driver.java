package com.revature;

import java.util.List;

import java.util.Set;

import com.revature.models.Account;
import com.revature.models.Role;
import com.revature.models.User;
import com.revature.repos.AccountDAO;
import com.revature.repos.AccountDAOImpl;
import com.revature.repos.UserDAO;
import com.revature.repos.UserDAOImpl;
import com.revature.services.AccountService;

//import com.revature.services.UserServices;
import com.revature.util.ValidationUtil;

public class Driver {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//UserDAOImpl repo = new UserDAOImpl();
//		repo.getUserById(1);
//		System.out.println(repo.getUserById(4).getUsername());
//		Role role = new Role();
		UserDAO userDao = UserDAOImpl.getInstance();
		System.out.println(userDao.getUserById(1).toString());
//		List<User> list = userDao.getAllUsers();
//		for(User c: list) {
//			System.out.println(c);
//		}
//		User u = new User(3,"anuj","anuj123","Anuj","Daundkar","an@gm.com");
//		userDao.updateUser(u);
		
//		
//		User u = new User("savita","savita","sav","bh","sav@gm.com",role);
//		repo.insertUser(u);
//		AccountDAO accountDao = AccountDAOImpl.getInstance();
//		
//		User u = new User("avani","avani123","Avani","Daundkar","avani@gmail.com",3);
//		System.out.println(u.getRole());
//		userDao.insertUser(u);
//		userDao.getUserByUserName("savita");
		
		//accountDao.getAccountByUserid(2);
		//accountDao.getAccountByStatus(1);
		//System.out.println(accountDao.getAccountById(4000));
//		
//		Set<Account> set1 = accountDao.showAllAccounts(); 
//		//set.forEach(System.out::println);
//		
//		for(Account c: set1) {
//			System.out.println(c);
//		}
//		
//		UserServiceImpl user = new UserServiceImpl();
//		user.isLoginValid("savita", "savb123");
//		AccountServiceImpl aser = new AccountServiceImpl();
//		aser.transferBetweenAccounts(4000, 4001, 500.00);
		
//		ValidationUtil validator = new ValidationUtil();
//		System.out.println(validator.isOwner(4000, 1));

	}

}
