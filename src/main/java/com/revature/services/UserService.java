package com.revature.services;

import com.revature.models.User;

public interface UserService {
	
	//User findUserEmpAdmin(User user);
	
	boolean isLoginValid(String userName,String password);
	
	boolean isUserAdmin(Integer user_id);
	boolean isUserEmployee(Integer user_id);
	boolean isUserStandard(Integer user_id);
	

}
