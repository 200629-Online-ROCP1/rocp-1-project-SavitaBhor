package com.revature.repos;
import java.util.List;
import java.util.Set;

import com.revature.models.Role;
import com.revature.models.User;

public interface UserDAO {
	
	
	
	boolean updateUser(User user);
	
	boolean insertUser(User user);
	
	User getUserByUserName(String username);
	
	User getUserById(Integer user_id);
	
	Role getUserByRoleId(Integer role_id);
	
	Role getUserByRoleName(String role_name);
	
	
	
	Set<User> getAllUsers();

	
	
	
	
	//boolean deleteUser(String userName);
	
	
	

}
