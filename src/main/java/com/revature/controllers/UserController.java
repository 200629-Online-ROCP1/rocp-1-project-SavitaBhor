package com.revature.controllers;

import java.io.BufferedReader;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.revature.models.User;
import com.revature.services.UserService;
import com.revature.util.ValidationUtil;

public class UserController {

	private final UserService us = new UserService();
	public static final ObjectMapper om = new ObjectMapper();
	private static final ValidationUtil vu = new ValidationUtil();

	public void handlePut(HttpServletRequest req, HttpServletResponse res, String[] portions, String logedInUser)
			throws IOException {

		if (portions.length == 1) {

			BufferedReader reader = req.getReader();

			StringBuilder s = new StringBuilder();

			String line = reader.readLine();

			while (line != null) {
				s.append(line);
				line = reader.readLine();
			}
			String body = new String(s);

			User u = om.readValue(body, User.class);
			
			if (vu.isValidRole(logedInUser, "Admin") || vu.isIdMatchesCurrentUser(logedInUser, u.getUserId())) {
				if(!vu.usernameEmailAlreadyExist(u)) {
					if (us.updateUser(u)) {
	
						res.setStatus(201);
						String json = om.writeValueAsString(u);
						res.getWriter().println(json);
						//res.getWriter().println("User is Updated");
					}else {
						res.setStatus(400);
						res.getWriter().println("User Doesn't Exist. ");
					}
					
				}else {
					res.setStatus(400);
					res.getWriter().println("Invalid fields - username or email is already used ");
				}
			} else {
				res.setStatus(401);
				res.getWriter().println("The requested action is not permitted");
			}

		} else {
			res.setStatus(404);
			res.getWriter().println("Check URL");
		}

	}

	public void handleGet(HttpServletRequest req, HttpServletResponse res, String[] portions, String logedInUser)
			throws IOException {

		if (portions.length == 2) {

			int uid = Integer.parseInt(portions[1]);
			if (vu.isValidRole(logedInUser, "Admin") || vu.isValidRole(logedInUser, "Employee")|| vu.isIdMatchesCurrentUser(logedInUser, uid)) {
				User u = us.getUserById(uid);
				if (u != null) {
					res.setStatus(200);

					String json = om.writeValueAsString(u);
					res.getWriter().println(json);
				} else {
					res.setStatus(404);
					res.getWriter().println("UserId does not exist!");
				}
			} else {
				res.setStatus(401);
				res.getWriter().println("The requested action is not permitted");
			}

		} else if (portions.length == 1) {

			if (vu.isValidRole(logedInUser, "Admin") || vu.isValidRole(logedInUser, "Employee")) {

				List<User> all = us.getAllUsers();
				res.setStatus(200);
				res.getWriter().println(om.writeValueAsString(all));
			} else {
				res.setStatus(401);
				res.getWriter().println("The requested action is not permitted");
			}
		} else {
			res.setStatus(404);
			res.getWriter().println("Check URL");
		}
	}

	public void handlePost(HttpServletRequest req, HttpServletResponse res, String[] portions, String logedInUser) throws IOException {
		
		if(portions.length == 1){
			
			BufferedReader reader = req.getReader();

			StringBuilder s = new StringBuilder();

			String line = reader.readLine();

			while (line != null) {
				s.append(line);
				line = reader.readLine();
			}
			String body = new String(s);

			User u = om.readValue(body, User.class);
			if (vu.isValidRole(logedInUser, "Admin")) {
				
				if(!vu.usernameEmailAlreadyExist(u)) {
						if (us.registerUser(u)) {
							User u1 = us.getUserByUserName(u.getUsername());
							res.setStatus(201);
							String json = om.writeValueAsString(u1);
							res.getWriter().println(json);
							//res.getWriter().println("User is Registered");
						}
				}else {
					res.setStatus(400);
					res.getWriter().println("Invalid fields - username or email is already used");
				}
			}else {
				res.setStatus(401);
				res.getWriter().println("The requested action is not permitted");
			}
		
	}else {
		res.setStatus(404);
		res.getWriter().println("Check URL");
	}
		
	}
		
	
}
