package com.revature.util;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtil {
	
	public static Connection getConnection() throws SQLException
	{
		
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		String url = "jdbc:postgresql://localhost:5432/banking";
		String username ="postgres";
		String password= "postgres";
		
		return DriverManager.getConnection(url,username,password);
	}
	
	//test if the connection is working
//	public static void main(String[] args) {
//		
//		try(Connection conn = ConnectionUtil.getConnection()){
//			System.out.println("success");
//			System.out.println(conn);
//			
//		}catch(SQLException e) {
//			System.out.println(e);
//		}
//	}

}
