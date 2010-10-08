package com.test.lptdd;

public class SavingsAccountYear {

	private final int startingBalance;
	private final int interestRate;
	private final int capitalGainsAmount;
	private int totalWithdrawn;

	public SavingsAccountYear(int startingBalance, int interestRate) {
		this(startingBalance, 0, interestRate);
	}

	public SavingsAccountYear(int startingBalance, int capitalGainsAmount, int interestRate) {
		this.startingBalance = startingBalance;
		this.capitalGainsAmount = capitalGainsAmount;
		this.interestRate = interestRate;
	}

	public int startingBalance() {
		return startingBalance;
	}

	public int startingPrincipal() {
		return startingBalance - capitalGainsAmount;
	}

	public int interestRate() {
		return interestRate;
	}

	public int endingPrincipal() {
		int result = startingPrincipal() - totalWithdrawn;
		return (result < 0) ? 0 : result;
	}

	public int endingBalance() {
		int modifiedStart = startingBalance - totalWithdrawn;
		return (modifiedStart + (modifiedStart * interestRate / 100));
	}

	public SavingsAccountYear nextYear() {
		return new SavingsAccountYear(this.endingBalance(), interestRate);
	}

	public void withdraw(int amount) {
		totalWithdrawn += amount;
	}
}
