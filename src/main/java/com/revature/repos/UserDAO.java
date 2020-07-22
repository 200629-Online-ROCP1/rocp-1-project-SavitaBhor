package com.revature.repos;
import java.util.List;
import java.util.Set;

import com.revature.models.Role;
import com.revature.models.User;

public interface UserDAO {
	
	
	
	public boolean updateUser(User user);
	
	//public boolean checkUsernameEmailExist(User user);
	
	public boolean insertUser(User user);
	
	public User getUserByUserName(String username);
	
	public User getUserById(Integer user_id);
	
	public Role getUserRoleByRoleId(Integer role_id);
	
	public Role getUserRoleByRoleName(String role_name);
	
	
	
	public List<User> getAllUsers();

	boolean deleteUser(Integer user_id);
	
}
