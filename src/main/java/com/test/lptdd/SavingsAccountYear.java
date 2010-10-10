package com.test.lptdd;

public class SavingsAccountYear {

	private int startingBalance;
	private int interestRate;
	private int totalWithdrawn;
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

	public int interestEarned(int capitalGainsTaxRate) {
		return (startingBalance() - totalWithdrawnExceptCapitalGainsTax() - capitalGainsTaxIncurred(capitalGainsTaxRate)) * interestRate() / 100;
	}
	
	public int totalWithdrawnExceptCapitalGainsTax() {
		return totalWithdrawn;
	}

	public int totalWithdrawn(int capitalGainsTax) {
		return totalWithdrawnExceptCapitalGainsTax() + capitalGainsTaxIncurred(capitalGainsTax);
	}
	
	public int endingPrincipal() {
		int result = startingPrincipal() - totalWithdrawnExceptCapitalGainsTax();
		return Math.max(0, result);
	}

	public int endingCapitalGains() {
		throw new UnsupportedOperationException();
	}
	
	public int endingBalance(int capitalGainsTaxRate) {
		int modifiedStart = startingBalance - totalWithdrawnExceptCapitalGainsTax() - capitalGainsTaxIncurred(capitalGainsTaxRate);
		return modifiedStart + interestEarned(capitalGainsTaxRate);
	}

	public SavingsAccountYear nextYear(int capitalGainsTaxRate) {
		return new SavingsAccountYear(this.endingBalance(capitalGainsTaxRate), 0, interestRate);
	}

	public void withdraw(int amount) {
		totalWithdrawn += amount;
	}

	public int capitalGainsWithdrawn() {
		int result = -1 * (startingPrincipal() - totalWithdrawnExceptCapitalGainsTax());
		return Math.max(0, result);
	}

	public int capitalGainsTaxIncurred(int taxRate) {
		double doubleTaxRate = taxRate / 100.0;
		double doubleCapitalGains = capitalGainsWithdrawn();
		
		
		return (int)((doubleCapitalGains / (1 - doubleTaxRate)) - doubleCapitalGains);
	}
}
