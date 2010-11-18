package com.test.lptdd;

import static com.test.lptdd.ValueObjectsStaticFactory.dollars;

public class StockMarketYear {

	private int startingBalance;
	private InterestRate interestRate;
	private int totalWithdrawals;
	private Dollars startingPrincipal;
	private TaxRate capitalGainsTaxRate;

	public StockMarketYear(int startingBalance, final Dollars startingPrincipal, final InterestRate interestRate, final TaxRate capitalGainsTaxRate) {
		this.startingBalance = startingBalance;
		this.startingPrincipal = startingPrincipal;
		this.interestRate = interestRate;
		this.capitalGainsTaxRate = capitalGainsTaxRate;
		this.totalWithdrawals = 0;
	}

	public int startingBalance() {
		return startingBalance;
	}

	public Dollars startingPrincipal() {
		return startingPrincipal;
	}

	public InterestRate interestRate() {
		return interestRate;
	}

	public TaxRate capitalGainsTaxRate() {
		return capitalGainsTaxRate;
	}
	
	public void withdraw(int amount) {
		totalWithdrawals += amount;
	}

	private int capitalGainsWithdrawn() {
		int result = -1 * (startingPrincipal().amount() - totalWithdrawals);
		return Math.max(0, result);
	}

	public int capitalGainsTaxIncurred() {
		return capitalGainsTaxRate.compoundTaxFor(capitalGainsWithdrawn());
	}
	
	public int totalWithdrawn() {
		return totalWithdrawals + capitalGainsTaxIncurred();
	}

	public int interestEarned() {
		return interestRate.interestOn(startingBalance - totalWithdrawn());
	}
	
	public int endingBalance() {
		return startingBalance - totalWithdrawn() + interestEarned();
	}
	
	public int endingPrincipal() {
		return startingPrincipal.subtractToZero(dollars(totalWithdrawals)).amount();
	}

	public StockMarketYear nextYear() {
		return new StockMarketYear(this.endingBalance(), dollars(this.endingPrincipal()), this.interestRate(), this.capitalGainsTaxRate());
	}
}
