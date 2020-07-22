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

import com.revature.models.Account;
import com.revature.models.AccountStatus;
import com.revature.models.AccountType;
import com.revature.util.ConnectionUtil;


public class AccountDAOImpl implements AccountDAO{
	

	private static AccountDAOImpl repo = new AccountDAOImpl();
	
	//private AccountDAOImpl() {}
	
	public static AccountDAOImpl getInstance() {
		return repo;
	}



	@Override
	public boolean submitAccount(Account account) {
		try(Connection conn = ConnectionUtil.getConnection()){
			int index =0;
			String sql = "INSERT INTO account(acc_balance,acc_status_id,acc_type_id,acc_user_id) "
					+ "VALUES(?,?,?,?)";
			
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setDouble(++index, account.getBalance());
			statement.setInt(++index, account.getStatus().getStatusId());
			statement.setInt(++index, account.getType().getTypeId());
			statement.setInt(++index, account.getUserId());
			statement.execute();
			
			if(statement.getUpdateCount() != 0) {
				return true;
			}
			//return true;
		
		}catch (SQLException e) {
			System.out.println(e);
		}
		return false;
	}

	@Override
	public Account getAccountById(Integer AccountId) {
		
		try(Connection conn =ConnectionUtil.getConnection()){
			String sql = "SELECT * FROM account WHERE acc_id =?";
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, AccountId);
			
			ResultSet result = statement.executeQuery();
			
			if(result.next()) {

				//return new Account(result.getInt("acc_id"),result.getDouble("acc_balance"),result.getInt("acc_status_id"),result.getInt("acc_type_id"));
				return new Account(result.getInt("acc_id"),result.getDouble("acc_balance"),result.getInt("acc_status_id"),result.getInt("acc_type_id"),result.getInt("acc_user_id"));
			}
		}catch(SQLException e) {
			System.out.println(e);
		}
		return null;
	}
	
	

	@Override
	public Account getAccountByUserid(Integer UserId) {
		
		try(Connection conn =ConnectionUtil.getConnection()){
			String sql = "SELECT * FROM account WHERE acc_user_id =?";
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, UserId);
			
			ResultSet result = statement.executeQuery();

			if(result.next()) {
				
				//return new Account(result.getInt("acc_id"),result.getDouble("acc_balance"),result.getInt("acc_status_id"),result.getInt("acc_type_id"));
				return new Account(result.getInt("acc_id"),result.getDouble("acc_balance"),result.getInt("acc_status_id"),result.getInt("acc_type_id"),result.getInt("acc_user_id"));
			}
			
		}catch(SQLException e) {
			System.out.println(e);
		}
		
		return null;
	}
	
	@Override
	public Account showSubmittedAccount(Integer UserId) {
		try(Connection conn =ConnectionUtil.getConnection()){
			String sql = "SELECT * FROM account WHERE acc_user_id =? AND acc_id = (SELECT max(acc_id) FROM account)";
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, UserId);
			
			ResultSet result = statement.executeQuery();

			if(result.next()) {
				
				//return new Account(result.getInt("acc_id"),result.getDouble("acc_balance"),result.getInt("acc_status_id"),result.getInt("acc_type_id"));
				return new Account(result.getInt("acc_id"),result.getDouble("acc_balance"),result.getInt("acc_status_id"),result.getInt("acc_type_id"),result.getInt("acc_user_id"));
			}
			
		}catch(SQLException e) {
			System.out.println(e);
		}
		return null;
	}


	@Override
	public boolean updateAccount(Account account) {
		
		try(Connection conn = ConnectionUtil.getConnection()){
			int index =0;

			String sql = "UPDATE account "
						+ "SET acc_balance = ?," 
						+ "acc_status_id = ?,"
						+ "acc_type_id = ?,"
						+ "acc_user_id = ?"
						+ "WHERE acc_id=?";
			
			PreparedStatement statement = conn.prepareStatement(sql);
		
			statement.setDouble(++index, account.getBalance());
			statement.setInt(++index, account.getStatus().getStatusId());
			statement.setInt(++index, account.getType().getTypeId());
			statement.setInt(++index, account.getUserId());
			statement.setInt(++index, account.getAccountId());
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
	public boolean saveAccountBalance(Account account) {
		
		try(Connection conn = ConnectionUtil.getConnection()){
			int index =0;
			String sql = "UPDATE account "+ "SET acc_balance = ?" + "WHERE acc_id=?";
			
			PreparedStatement statement = conn.prepareStatement(sql);
			
			statement.setDouble(++index, account.getBalance());
			statement.setInt(++index, account.getAccountId());
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
	public AccountStatus getAccountByStatus(Integer StatusId) {
		
		try(Connection conn =ConnectionUtil.getConnection()){
			String sql = "SELECT * FROM account_status WHERE status_id =?";
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, StatusId);
			
			ResultSet result = statement.executeQuery();

			if(result.next()) {
				
				return new AccountStatus(result.getInt("status_id"),result.getString("status"));
			}
		}catch(SQLException e) {
			System.out.println(e);
		}
		return null;
	}

	
	@Override
	public AccountType getAccountByType(Integer TypeId) {
		try(Connection conn =ConnectionUtil.getConnection()){
			String sql = "SELECT * FROM account_type WHERE type_id =?";
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, TypeId);
			
			ResultSet result = statement.executeQuery();

			if(result.next()) {
				
				return new AccountType(result.getInt("type_id"),result.getString("acc_type"));
			}
		}catch(SQLException e) {
			System.out.println(e);
		}
		return null;
	}



	@Override
	public List<Account> showAllAccounts() {
		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "SELECT * FROM account;";
			
			Statement statement = conn.createStatement();
			
			List<Account> list = new ArrayList<>();
			
			ResultSet result = statement.executeQuery(sql);
			
			while(result.next()) {
				
				//list.add(new Account(result.getInt("acc_id"),result.getDouble("acc_balance"),result.getInt("acc_status_id"),result.getInt("acc_type_id")));
				list.add(new Account(result.getInt("acc_id"),result.getDouble("acc_balance"),result.getInt("acc_status_id"),result.getInt("acc_type_id"),result.getInt("acc_user_id")));
			}
			
			return list;
			
		}catch(SQLException e) {
			System.out.println(e);
		}
		return null;
	}



	@Override
	public Account getAccountByStatusId(Integer StatusId) {
		
		try(Connection conn =ConnectionUtil.getConnection()){
			String sql = "SELECT * FROM account WHERE acc_status_id =?";
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, StatusId);
			
			ResultSet result = statement.executeQuery();

			if(result.next()) {
				
				//return new Account(result.getInt("acc_id"),result.getDouble("acc_balance"),result.getInt("acc_status_id"),result.getInt("acc_type_id"));
				return new Account(result.getInt("acc_id"),result.getDouble("acc_balance"),result.getInt("acc_status_id"),result.getInt("acc_type_id"),result.getInt("acc_user_id"));
			}
			
		}catch(SQLException e) {
			System.out.println(e);
		}
		return null;
	}



	@Override
	public boolean deleteAccount(Integer AccountId) {
		try(Connection conn =ConnectionUtil.getConnection()){
			String sql = "DELETE FROM account WHERE acc_id =?";
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, AccountId);
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
