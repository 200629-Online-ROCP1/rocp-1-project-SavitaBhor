package com.revature.models;

public class TransferDTO {
	
	public int sourceAccountId;
	public int targetAccountId;
	public double amount;
	
	public TransferDTO() {
		super();
	}

	public TransferDTO(int sourceAccountId, int targetAccountId, double amount) {
		super();
		this.sourceAccountId = sourceAccountId;
		this.targetAccountId = targetAccountId;
		this.amount = amount;
	}
	
	
}
