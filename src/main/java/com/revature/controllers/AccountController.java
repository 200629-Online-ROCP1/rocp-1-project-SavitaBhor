package com.revature.controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.models.Account;
import com.revature.services.AccountService;

public class AccountController {
	
	public static final AccountService as = new AccountService();
	public static final ObjectMapper om = new ObjectMapper();


	public void handlePost(HttpServletRequest req, HttpServletResponse res, String[] portions) throws IOException {
		// TODO Auto-generated method stub
		
	}

	public void handGet(HttpServletRequest req, HttpServletResponse res, String[] portions) throws IOException{
		
//		if (portions.length == 2) {
//			String name = portions[1];
//			Account a = as.(name);
//			res.setStatus(200);
//			String json = om.writeValueAsString(h);
//			res.getWriter().println(json);
//		} else {
//			List<Account> all = as.findAll();
//			res.setStatus(200);
//			res.getWriter().println(om.writeValueAsString(all));
//		}
		
	}

}
