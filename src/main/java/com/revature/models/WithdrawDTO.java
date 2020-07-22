package com.revature.models;

public class WithdrawDTO {
	
	public int accountId;
	public double amount;
	
	public WithdrawDTO() {
		super();
	}

	public WithdrawDTO(int accountId, double amount) {
		super();
		this.accountId = accountId;
		this.amount = amount;
	}
	

}
