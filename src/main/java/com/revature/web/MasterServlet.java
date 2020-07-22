package com.revature.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.controllers.AccountController;
import com.revature.controllers.LoginController;
import com.revature.controllers.UserController;
import com.revature.models.LoginDTO;


public class MasterServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
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

		HttpSession ses = req.getSession(false);
		String logedInUser = null;
		if (ses != null && ((Boolean) ses.getAttribute("loggedin"))) {

			LoginDTO user = (LoginDTO) ses.getAttribute("user");
			logedInUser = user.getUsername();

		}

		try {
			switch (portions[0]) {
			/*------------------------------------------------------------------------------*/
			case "users":
				
				if (logedInUser != null) {
					
					if (req.getMethod().equals("PUT")) {
						uc.handlePut(req, res, portions,logedInUser);
					} else if (req.getMethod().equals("GET")) {
						uc.handleGet(req, res, portions,logedInUser);
					}else if (req.getMethod().equals("DELETE")) {
						uc.handleDelete(req, res, portions,logedInUser);
					}
				} else {
					res.setStatus(401);
					res.getWriter().println("You must be logged in to do that!");
				}
				break;
			/*-------------------------------------------------------------------------------*/
			case "accounts":
				
				if (logedInUser != null) {
					
					if (req.getMethod().equals("POST")) {
						ac.handlePost(req, res, portions,logedInUser);
					} else if (req.getMethod().equals("GET")){
						ac.handleGet(req, res, portions,logedInUser);
					}else if (req.getMethod().equals("PUT")) {
						ac.handlePut(req, res, portions,logedInUser);
					}else if (req.getMethod().equals("DELETE")) {
						ac.handleDelete(req, res, portions,logedInUser);
					}
				} else {
					res.setStatus(401);
					res.getWriter().println("You must be logged in to do that!");
				}
				break;
			/*-------------------------------------------------------------------------------------*/
			case "register":
				//if (logedInUser != null) {
					if (req.getMethod().equals("POST")) {
						uc.handlePost(req, res, portions,logedInUser);
					}
//				}else {
//					res.setStatus(401);
//					res.getWriter().println("You must be logged in to do that!");
//				}
				break;
				
			/*--------------------------------------------------------------------------------------*/
			case "login":
				lc.login(req, res);
				break;
			/*-----------------------------------------------------------------------------------------*/
			case "logout":
				lc.logout(req, res);
				break;
			/*-----------------------------------------------------------------------------------------*/

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
	
	protected void doPut(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doGet(req, res);
	}
	
	protected void doDelete(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doGet(req, res);
	}

}
