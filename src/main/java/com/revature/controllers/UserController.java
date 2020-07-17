package com.revature.controllers;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.models.User;
import com.revature.services.UserService;


public class UserController {
	
	private final UserService us = new UserService();
	public static final ObjectMapper om = new ObjectMapper();
	
	public void handlePost(HttpServletRequest req, HttpServletResponse res, String[] portions) throws IOException {
		if (portions.length == 2) {

			
		} else {
			
				BufferedReader reader = req.getReader();

				StringBuilder s = new StringBuilder();

				String line = reader.readLine();

				while (line != null) {
					s.append(line);
					line = reader.readLine();
				}

				String body = new String(s);

				System.out.println(body);

				User u = om.readValue(body, User.class);

				System.out.println(u);

				if (us.registerUser(u)) {
					
					res.setStatus(201);
					res.getWriter().println("User was created");
				}

			
		}
		
	}
	
	public void handGet(HttpServletRequest req, HttpServletResponse res, String[] portions) throws IOException {
		
		if (portions.length == 2) {
			int uid = Integer.parseInt(portions[1]);
			User u = us.getUserById(uid);
			
			res.setStatus(200);
			
			String json = om.writeValueAsString(u);
			res.getWriter().println(json);
		} else {
			List<User> all = us.getAllUsers();
			res.setStatus(200);
			res.getWriter().println(om.writeValueAsString(all));
		}
	}
//	public List<User> getAllUsers(){
//		return us.getAllUsers();
//	}
//
//	
//	public User getUserById(Integer user_id) {
//		
//		return us.getUserById(user_id);
//	}
//	
//	public boolean registerUser(User user) {
//		return us.registerUser(user);
//	}
	
	
	
}
