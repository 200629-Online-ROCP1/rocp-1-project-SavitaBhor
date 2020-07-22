package com.revature.controllers;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.models.Account;
import com.revature.models.DepositDTO;
import com.revature.models.LoginDTO;
import com.revature.models.TransferDTO;
import com.revature.models.User;
import com.revature.models.WithdrawDTO;
import com.revature.services.AccountService;
import com.revature.util.ValidationUtil;

public class AccountController {

	public static final AccountService as = new AccountService();
	public static final ObjectMapper om = new ObjectMapper();
	private static final ValidationUtil vu = new ValidationUtil();

	public void handleGet(HttpServletRequest req, HttpServletResponse res, String[] portions, String logedInUser)
			throws IOException {

		if (portions.length == 1) {
			if (vu.isValidRole(logedInUser, "Admin") || vu.isValidRole(logedInUser, "Employee")) {
				List<Account> all = as.showAllAccounts();
				if (all != null) {
					res.setStatus(200);
					res.getWriter().println(om.writeValueAsString(all));
				} else {
					res.setStatus(404);
					res.getWriter().println("No Accounts to show");
				}
			} else {
				res.setStatus(401);
				res.getWriter().println("The requested action is not permitted");
			}

		} else if (portions.length == 2) {
			int accid = Integer.parseInt(portions[1]);
			Account a = as.getAccountById(accid);
			if (a != null) {
				if (vu.isValidRole(logedInUser, "Admin") || vu.isValidRole(logedInUser, "Employee")
						|| vu.isOwnerOfAccount(logedInUser, a.getAccountId())) {

					res.setStatus(200);
					String json = om.writeValueAsString(a);
					res.getWriter().println(json);

				} else {
					res.setStatus(401);
					res.getWriter().println("The requested action is not permitted");
				}
			} else {
				res.setStatus(404);
				res.getWriter().println("Account Number does not exist!");
			}

		} else if (portions.length == 3) {

			if (portions[1].matches("status")) {

				if (vu.isValidRole(logedInUser, "Admin") || vu.isValidRole(logedInUser, "Employee")) {
					int statusid = Integer.parseInt(portions[2]);
					Account a = as.getAccountByStatusId(statusid);
					if (a != null) {
						res.setStatus(200);
						String json = om.writeValueAsString(a);
						res.getWriter().println(json);
					} else {
						res.setStatus(404);
						res.getWriter().println("Status Id is not linked to Account!");
					}
				} else {
					res.setStatus(401);
					res.getWriter().println("The requested action is not permitted");
				}

			} else if (portions[1].matches("owner")) {

				int userid = Integer.parseInt(portions[2]);
				Account a = as.getAccountByUserid(userid);
				if (a != null) {
					if (vu.isValidRole(logedInUser, "Admin") || vu.isValidRole(logedInUser, "Employee")
							|| vu.isOwnerOfAccount(logedInUser, a.getAccountId())) {

						res.setStatus(200);
						String json = om.writeValueAsString(a);
						res.getWriter().println(json);

					} else {
						res.setStatus(401);
						res.getWriter().println("The requested action is not permitted");
					}
				} else {
					res.setStatus(404);
					res.getWriter().println("User Id is not linked to Account!");
				}

			} else {
				res.setStatus(404);
				res.getWriter().println("Check URL");
			}

		} else {
			res.setStatus(404);
			res.getWriter().println("Check URL");
		}

	}

	public void handlePost(HttpServletRequest req, HttpServletResponse res, String[] portions, String logedInUser)
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

			Account a = om.readValue(body, Account.class);

			if (vu.isValidRole(logedInUser, "Admin") || vu.isValidRole(logedInUser, "Employee")
					|| vu.isIdMatchesCurrentUser(logedInUser, a.getUserId())) {

				if (as.submitAccount(a)) {

					Account a1 = as.showSubmittedAccount(a.getUserId());
					String json = om.writeValueAsString(a1);
					res.getWriter().println(json);
					res.setStatus(201);
					//res.getWriter().println("Account is Created");
					
				}else {
					res.setStatus(400);
					res.getWriter().println("UserId does not exist,to assign to the account.");
				}
			} else {
				res.setStatus(401);
				res.getWriter().println("The requested action is not permitted");
			}

		} else if (portions.length == 2) {

			BufferedReader reader = req.getReader();
			StringBuilder s = new StringBuilder();
			String line = reader.readLine();
			while (line != null) {
				s.append(line);
				line = reader.readLine();
			}
			String body = new String(s);
			/*--------------------------------------------------------------------------------------------*/
			if (portions[1].matches("withdraw")) {
				WithdrawDTO wdto = om.readValue(body, WithdrawDTO.class);
				if (vu.isValidRole(logedInUser, "Admin") || vu.isOwnerOfAccount(logedInUser, wdto.accountId)) {
					Account a = as.getAccountById(wdto.accountId);
					if (a != null) {
						if (as.withdrawFromAccount(wdto.accountId, wdto.amount)) {
							res.setStatus(200);
							res.getWriter()
									.println("$" + wdto.amount + " has been withdrawn from Account# " + wdto.accountId);
						} else {
							res.setStatus(400);
							res.getWriter().println("Insuffiecient balance.");
						}

					} else {
						res.setStatus(404);
						res.getWriter().println("The requested Account does not exist.");
					}
				} else {
					res.setStatus(401);
					res.getWriter().println("The requested action is not permitted");
				}

				/*------------------------------------------------------------------------------------------------*/
			} else if (portions[1].matches("deposit")) {
				DepositDTO ddto = om.readValue(body, DepositDTO.class);
				if (vu.isValidRole(logedInUser, "Admin") || vu.isOwnerOfAccount(logedInUser, ddto.accountId)) {
					Account a = as.getAccountById(ddto.accountId);
					if (a != null) {
						if (as.depositAccount(ddto.accountId, ddto.amount)) {
							res.setStatus(200);
							res.getWriter()
									.println("$" + ddto.amount + " has been deposited to Account# " + ddto.accountId);
						}
					} else {
						res.setStatus(404);
						res.getWriter().println("The requested Account does not exist.");
					}
				} else {
					res.setStatus(401);
					res.getWriter().println("The requested action is not permitted");
				}

				/*--------------------------------------------------------------------------------------------------*/
			} else if (portions[1].matches("transfer")) {
				TransferDTO tdto = om.readValue(body, TransferDTO.class);
				if (vu.isValidRole(logedInUser, "Admin") || vu.isOwnerOfAccount(logedInUser, tdto.sourceAccountId)) {
					Account a1 = as.getAccountById(tdto.sourceAccountId);
					Account a2 = as.getAccountById(tdto.targetAccountId);
					if (a1 != null && a2 != null) {
						
						if (as.transferBetweenAccounts(tdto.sourceAccountId, tdto.targetAccountId, tdto.amount)) {
							res.setStatus(200);
							res.getWriter().println("$" + tdto.amount + " has been transferred from Account# "
									+ tdto.sourceAccountId + " to Account# " + tdto.targetAccountId);
						} else {
							res.setStatus(400);
							res.getWriter().println("Insuffiecient balance to transfer.");
						}
					} else {
						res.setStatus(404);
						res.getWriter().println("The requested AccountId's not found.");
					}
				} else {
					res.setStatus(401);
					res.getWriter().println("The requested action is not permitted");
				}

				/*--------------------------------------------------------------------------------------------------*/
			} else {
				res.setStatus(404);
				res.getWriter().println("Check URL");
			}

		} else {
			res.setStatus(404);
			res.getWriter().println("Check URL");
		}

	}

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

			Account a = om.readValue(body, Account.class);

			if (vu.isValidRole(logedInUser, "Admin")) {

				if (as.updateAccount(a)) {
					Account a1 = as.getAccountById(a.getAccountId());
					String json = om.writeValueAsString(a1);
					res.getWriter().println(json);
					res.setStatus(200);
					//res.getWriter().println("Account is Updated");
				}else {
					res.setStatus(400);
					res.getWriter().println("Account does not exist.");
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

}
