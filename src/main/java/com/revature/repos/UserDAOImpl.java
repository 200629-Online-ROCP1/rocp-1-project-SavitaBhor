package com.revature.repos;


import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import com.revature.models.Role;
import com.revature.models.User;
import com.revature.util.ConnectionUtil;

public class UserDAOImpl implements UserDAO{

	private static UserDAOImpl repo = new UserDAOImpl();
	
	public static UserDAOImpl getInstance() {
		return repo;
	}
	
	
	@Override
	public boolean insertUser(User user) {
		
		try(Connection conn = ConnectionUtil.getConnection()){
			int index =0;
			String sql = "INSERT INTO bank_user(username,pwd,first_name, last_name,email,role_id) "
					+ "VALUES(?,?,?,?,?,?)";
			
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(++index, user.getUsername());
			statement.setString(++index, user.getPassword());
			statement.setString(++index, user.getFirstName());
			statement.setString(++index, user.getLastName());
			statement.setString(++index, user.getEmail());
			statement.setInt(++index, user.getRole().getRoleId());
			statement.execute();
			if(statement.getUpdateCount() != 0) {
				return true;
			}
			//return true;
	
		
		}catch (SQLException e) {
			System.out.println(e);
		}
//		}
		return false;
	}

	@Override
	public User getUserByUserName(String username) {
		
		try(Connection conn =ConnectionUtil.getConnection()){
			String sql = "SELECT * FROM bank_user WHERE username =?";
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, username);
			
			ResultSet result = statement.executeQuery();

			if(result.next()) {
				
				return new User(result.getInt("user_id"),result.getString("username"),result.getString("pwd"),result.getString("first_name"),result.getString("last_name"),result.getString("email"),result.getInt("role_id"));
			}
		}catch(SQLException e) {
			System.out.println(e);
		}
		return null;
	}

	@Override
	public List<User> getAllUsers() {
		
		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "SELECT user_id,username,pwd,first_name,last_name,email,role_name FROM bank_user bu inner join user_role ur ON bu.role_id = ur.role_id;";
			
			Statement statement = conn.createStatement();
			
			List<User> list = new ArrayList<>();
			
			ResultSet result = statement.executeQuery(sql);
			
			while(result.next()) {
				list.add(new User(result.getInt("user_id"), result.getString("username"),
						result.getString("pwd"), result.getString("first_name"), result.getString("last_name"),result.getString("email"),result.getString("role_name")));
			}
			
			return list;
			
		}catch(SQLException e) {
			System.out.println(e);
		}
		
		return null;
	}


	@Override
	public boolean updateUser(User user) {
		
		try(Connection conn = ConnectionUtil.getConnection()){
			int index =0;
			String sql = "UPDATE bank_user SET "
							+"username=?,"
							+"pwd=?,"
							+"first_name=?," 
							+"last_name=?,"
							+"email=? ,"
							+"role_id=? "
							+"WHERE user_id=?";
		
			PreparedStatement statement = conn.prepareStatement(sql);
		
			statement.setString(++index, user.getUsername());
			statement.setString(++index, user.getPassword());
			statement.setString(++index, user.getFirstName());
			statement.setString(++index, user.getLastName());
			statement.setString(++index, user.getEmail());
			statement.setInt(++index, user.getRole().getRoleId());
			statement.setInt(++index, user.getUserId());
			statement.execute();
			
			if(statement.getUpdateCount() != 0) {
				return true;
			}
									
		}catch (SQLException e) {
			System.out.println(e);
		}
		return false;
	}

	@Override
	public User getUserById(Integer user_id) {
		
		try(Connection conn = ConnectionUtil.getConnection()){
			
			String sql = "SELECT * FROM bank_user WHERE user_id =?";
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, user_id);
			
			ResultSet result = statement.executeQuery();
			
			if(result.next()) {
				
				return new User(result.getInt("user_id"),result.getString("username"),result.getString("pwd"),result.getString("first_name"),result.getString("last_name"),result.getString("email"),result.getInt("role_id"));
				
			}
		}catch(SQLException e) {
			System.out.println(e);
		}
		return null;
	}


	@Override
	public Role getUserRoleByRoleId(Integer role_id) {
		
		try(Connection conn =ConnectionUtil.getConnection()){
			
			String sql = "SELECT * FROM user_role WHERE  role_id =?";
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, role_id);
			
			ResultSet result = statement.executeQuery();

			if(result.next()) {
				
				return new Role(result.getInt("role_id"),result.getString("role_name"));
			}
		}catch(SQLException e) {
			System.out.println(e);
		}
		
		return null;
	}


	@Override
	public Role getUserRoleByRoleName(String role_name) {
		
		try(Connection conn =ConnectionUtil.getConnection()){
			
			String sql = "SELECT * FROM user_role WHERE  role_name =?";
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, role_name);
			
			ResultSet result = statement.executeQuery();

			if(result.next()) {
				
				return new Role(result.getInt("role_id"),result.getString("role_name"));
			}
		}catch(SQLException e) {
			System.out.println(e);
		}
		
		return null;
	}

	
	@Override
	public boolean deleteUser(Integer user_id) {
		
		try(Connection conn =ConnectionUtil.getConnection()){
			String sql = "DELETE FROM bank_user WHERE user_id =?";
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, user_id);
			statement.execute();	
			
			if(statement.getUpdateCount() != 0) {
				return true;
			}
			
		}catch(SQLException e) {
			System.out.println(e);
		}
		return false;
	}



	}

	
	


