package com.test.lptdd;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class StockMarketYearTest {

	private static final int INTEREST_RATE = 10;
	private static final int STARTING_PRINCIPAL = 3000;
	private static final int STARTING_BALANCE = 10000;
	private static final TaxRate CAPITAL_GAINS_TAX_RATE = new TaxRate(25);

	@Test
	public void startingValues() {
		StockMarketYear year = newYear();
		assertThat("starting balance", year.startingBalance(), is(STARTING_BALANCE));
		assertThat("starting principal", year.startingPrincipal(), is(STARTING_PRINCIPAL));
		assertThat("interest rate", year.interestRate(), is(INTEREST_RATE));
		assertThat("capital gains tax rate",year.capitalGainsTaxRate(), is(CAPITAL_GAINS_TAX_RATE.rate()));
		assertThat("total withdrawn default", year.totalWithdrawn(), is(0));
	}

	@Test
	public void capitalGainsTax(){
		StockMarketYear year = newYear();
		year.withdraw(4000);
		assertThat("capital gains tax includes tax on withdrawls to cover",year.capitalGainsTaxIncurred(),is(333));
		assertThat("total withdrawn includes capital gains tax", year.totalWithdrawn(), is(4333));
	}
	
	@Test
	public void interestEarned(){
		StockMarketYear year = newYear();
		assertThat("basic interest earned", year.interestEarned(), is(1000));
		year.withdraw(2000);
		assertThat("withdrawal don't earn interest", year.interestEarned(), is(800));
		year.withdraw(2000);
		assertThat("capital gains tax withdrawals don't earn interest",year.interestEarned(),is(566));
	}

	@Test
	public void endingPrincipal(){
		StockMarketYear year = newYear();
		year.withdraw(1000);
		assertThat("ending principal considers withdrawls", year.endingPrincipal(), is(2000));
		year.withdraw(500);
		assertThat("ending principal considers total multiple withdrawls", year.endingPrincipal(), is(1500));
		year.withdraw(3000);
		assertThat("ending principal never goes below zero", year.endingPrincipal(), is(0));
	}
	
	@Test
	public void endingBalance() {
		StockMarketYear year = newYear();
		assertThat("ending balance includes interest", year.endingBalance(), is(11000));
		year.withdraw(1000);
		assertThat("ending balance includes withdrawls",year.endingBalance(),is(9900));
		year.withdraw(3000);
		assertThat("ending balance includes capital gains tax withdrawals",year.endingBalance(),is(6233));
	}
	
	@Test
	public void nextYearStartingValuesMatchesThisYearEndingValues() {
		StockMarketYear thisYear = newYear();
		StockMarketYear nextYear = thisYear.nextYear();
		assertThat("starting balance", nextYear.startingBalance(), is(thisYear.endingBalance()));
		assertThat("starting principal", nextYear.startingPrincipal(), is(thisYear.endingPrincipal()));
		assertThat("interest", nextYear.interestRate(), is(thisYear.interestRate()));
		assertThat("capital gains tax rate",nextYear.capitalGainsTaxRate(),is(thisYear.capitalGainsTaxRate()));
	}

	private StockMarketYear newYear() {
		return new StockMarketYear(STARTING_BALANCE, STARTING_PRINCIPAL, INTEREST_RATE, CAPITAL_GAINS_TAX_RATE.rate());
	}
}
