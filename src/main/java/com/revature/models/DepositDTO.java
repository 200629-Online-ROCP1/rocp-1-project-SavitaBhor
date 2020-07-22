package com.revature.models;

public class DepositDTO {
	
	public int accountId;
	public double amount;
	
	public DepositDTO() {
		super();
	}
	
	public DepositDTO(int accountId, double amount) {
		super();
		this.accountId = accountId;
		this.amount = amount;
	}
	

}
