package com.test.lptdd;

import static com.test.lptdd.Dollars.dollars;

public class StockMarketYear {

	private Dollars startingBalance;
	private Dollars startingPrincipal;
	private InterestRate interestRate;
	private TaxRate capitalGainsTaxRate;
	private Dollars totalWithdrawals;

	public StockMarketYear(final Dollars startingBalance, final Dollars startingPrincipal, final InterestRate interestRate, final TaxRate capitalGainsTaxRate) {
		this.startingBalance = startingBalance;
		this.startingPrincipal = startingPrincipal;
		this.interestRate = interestRate;
		this.capitalGainsTaxRate = capitalGainsTaxRate;
		this.totalWithdrawals = dollars(0);
	}

	public Dollars startingBalance() {
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
	
	public void withdraw(final Dollars amount) {
		totalWithdrawals = totalWithdrawals.add(amount);
	}

	private Dollars capitalGainsWithdrawn() {
		return totalWithdrawals.subtractToZero(startingPrincipal());
	}

	public int capitalGainsTaxIncurred() {
		return capitalGainsTaxRate.compoundTaxFor(capitalGainsWithdrawn().amount());
	}
	
	public Dollars totalWithdrawn() {
		return totalWithdrawals.add(dollars(capitalGainsTaxIncurred()));
	}

	public int interestEarned() {
		return interestRate.interestOn(startingBalance.amount() - totalWithdrawn().amount());
	}
	
	public Dollars endingBalance() {
		return startingBalance.subtract(totalWithdrawn()).add(dollars(interestEarned()));
	}
	
	public int endingPrincipal() {
		return startingPrincipal.subtractToZero(totalWithdrawals).amount();
	}

	public StockMarketYear nextYear() {
		return new StockMarketYear(this.endingBalance(), dollars(this.endingPrincipal()), this.interestRate(), this.capitalGainsTaxRate());
	}
}
