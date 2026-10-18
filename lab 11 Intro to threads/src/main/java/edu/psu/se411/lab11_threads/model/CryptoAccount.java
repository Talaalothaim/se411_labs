package edu.psu.se411.lab11_threads.model;

import edu.psu.se411.lab11_threads.exceptions.InsufficientBalanceException;

public class CryptoAccount {
	private String id;
	private Double balance;
	
	public CryptoAccount(String id, Double balance) {
		this.id = id;
		this.balance = balance;
	}
	
	public void addToBalance(Double amount) {
		if (amount == null || amount < 0) throw new IllegalArgumentException("Invalid amount");
		Double current = balance;          // 1- read
		Thread.yield();                    // let other threads interleave (makes the race visible)
		balance = current + amount;        // 2- write
	}
	
	public void retrieveFromBalance(Double amount) throws InsufficientBalanceException {
		if (amount == null || amount < 0) throw new IllegalArgumentException("Invalid amount");
		Double current = balance;          // 1- read
		if (current < amount) {            // 2- check
			throw new InsufficientBalanceException(
					"Account " + id + " has insufficient balance (" + current + ") to send " + amount);
		}
		Thread.yield();                    // let other threads interleave (makes the race visible)
		balance = current - amount;        // 3- write
	}
	
	public String getId() {
		return id;
	}
	public Double getBalance() {
		return balance;
	}
	
}
