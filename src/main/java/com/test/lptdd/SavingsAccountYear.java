package com.test.lptdd;

public class SavingsAccountYear {

	private int startingBalance;
	private int interestRate;
	private int totalWithdrawals;
	private int startingPrincipal;

	public SavingsAccountYear(int startingBalance, int startingPrincipal, int interestRate) {
		this.startingBalance = startingBalance;
		this.startingPrincipal = startingPrincipal;
		this.interestRate = interestRate;
	}

	public int startingBalance() {
		return startingBalance;
	}

	public int startingPrincipal() {
		return startingPrincipal;
	}

	public int startingCapitalGains() {
		return startingBalance - startingPrincipal;
	}
	
	public int interestRate() {
		return interestRate;
	}

	public void withdraw(int amount) {
		totalWithdrawals += amount;
	}

	public int capitalGainsWithdrawn() {
		int result = -1 * (startingPrincipal() - totalWithdrawals);
		return Math.max(0, result);
	}

	public int capitalGainsTaxIncurred(int taxRate) {
		double doubleTaxRate = taxRate / 100.0;
		double doubleCapitalGains = capitalGainsWithdrawn();
		return (int)((doubleCapitalGains / (1 - doubleTaxRate)) - doubleCapitalGains);
	}
	
	public int totalWithdrawn(int capitalGainsTax) {
		return totalWithdrawals + capitalGainsTaxIncurred(capitalGainsTax);
	}

	public int interestEarned(int capitalGainsTaxRate) {
		return (startingBalance() - totalWithdrawn(capitalGainsTaxRate)) * interestRate() / 100;
	}
	
	public int endingPrincipal() {
		int result = startingPrincipal() - totalWithdrawals;
		return Math.max(0, result);
	}

	public int endingCapitalGains(int capitalGainsTaxRate) {
		return startingCapitalGains() - capitalGainsWithdrawn() + interestEarned(capitalGainsTaxRate);
	}
	
	public int endingBalance(int capitalGainsTaxRate) {
		int modifiedStart = startingBalance - totalWithdrawn(capitalGainsTaxRate);
		return modifiedStart + interestEarned(capitalGainsTaxRate);
	}

	public SavingsAccountYear nextYear(int capitalGainsTaxRate) {
		return new SavingsAccountYear(this.endingBalance(capitalGainsTaxRate), 0, interestRate);
	}
}
