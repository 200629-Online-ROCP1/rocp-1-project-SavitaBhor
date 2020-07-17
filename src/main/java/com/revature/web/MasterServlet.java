package com.revature.web;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.controllers.AccountController;
import com.revature.controllers.LoginController;
import com.revature.controllers.UserController;
import com.revature.models.User;

public class MasterServlet extends HttpServlet {

	private static final ObjectMapper om = new ObjectMapper();
	private static final UserController uc = new UserController();
	private static final LoginController lc = new LoginController();
	private static final AccountController ac = new AccountController();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		res.setContentType("application/json");
		// this will set the default response to not found; we will change it later if
		// the request was successful
		res.setStatus(404);

		final String URI = req.getRequestURI().replace("/rocp-project/", "");

		String[] portions = URI.split("/");

//		System.out.println(Arrays.toString(portions));
//		System.out.println(portions.length);

		try {
			switch (portions[0]) {
			case "user":
				HttpSession ses = req.getSession(false);
				if (ses != null && ((Boolean) ses.getAttribute("loggedin"))) {
					
					
					
//					if (portions.length == 2) {
//
//						int uid = Integer.parseInt(portions[1]);
//						User u = uc.getUserById(uid);
//						System.out.println(uid);
//						res.setStatus(200);
//						// The ObjectMapper (om) here will take the object (a) and convert it to a JSON
//						// object String.
//						String json = om.writeValueAsString(u);
//						res.getWriter().println(json);
//					} else {
//						if (req.getMethod().equals("POST")) {
//							BufferedReader reader = req.getReader();
//
//							StringBuilder s = new StringBuilder();
//
//							String line = reader.readLine();
//
//							while (line != null) {
//								s.append(line);
//								line = reader.readLine();
//							}
//
//							String body = new String(s);
//
//							System.out.println(body);
//
//							User u = om.readValue(body, User.class);
//
//							System.out.println(u);
//
//							if (uc.registerUser(u)) {
//								System.out.println("in add User if statement");
//								res.setStatus(201);
//								res.getWriter().println("User was created");
//							}
//
//						} else {
//
//							List<User> all = uc.getAllUsers();
//							res.setStatus(200);
//							res.getWriter().println(om.writeValueAsString(all));
//
//						}
//					}
					
				} else {
					res.setStatus(401);
					res.getWriter().println("You must be logged in to do that!");
				}
				break;
			case "account":
				ses = req.getSession(false);
				if (ses != null && ((Boolean) ses.getAttribute("loggedin"))) {
					if (req.getMethod().equals("POST")) {
						ac.handlePost(req, res, portions);
					} else {
						ac.handGet(req, res, portions);
					}
				} else {
					res.setStatus(401);
					res.getWriter().println("You must be logged in to do that!");
				}

				break;
			case "login":
				lc.login(req, res);
				break;
			case "logout":
				lc.logout(req, res);

			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
			res.getWriter().println("The id you provided is not an integer");
			res.setStatus(400);
		}

	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doGet(req, res);
	}

}
