package com.test.lptdd;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class StockMarketYearTest {

	@Test
	public void startingValues() {
		StockMarketYear year = new StockMarketYear(10000, 3000, 10);
		assertThat("starting balance", year.startingBalance(), is(10000));
		assertThat("starting principal", year.startingPrincipal(), is(3000));
		assertThat("interest rate", year.interestRate(), is(10));
		assertThat("total withdrawn default", year.totalWithdrawn(25), is(0));
	}

	@Test
	public void endingPrincipal(){
		StockMarketYear year = new StockMarketYear(10000, 3000, 10);
		year.withdraw(1000);
		assertThat("ending principal considers withdrawls", year.endingPrincipal(), is(2000));
		year.withdraw(500);
		assertThat("ending principal considers total multiple withdrawls", year.endingPrincipal(), is(1500));
		year.withdraw(3000);
		assertThat("ending principal never goes below zero", year.endingPrincipal(), is(0));
	}

	@Test
	public void interestEarned(){
		StockMarketYear year = new StockMarketYear(10000, 3000, 10);
		assertThat("basic interest earned", year.interestEarned(25), is(1000));
		year.withdraw(2000);
		assertThat("withdrawal don't earn interest", year.interestEarned(25), is(800));
	}
	
	@Test
	public void interestEarnedIsStartingBalanceCombinedWithInterestRate(){
		StockMarketYear year = new StockMarketYear(10000, 3000, 10);
		assertThat(year.interestEarned(25), is(1000));
	}
	
	@Test
	public void withdrawingFundsDoNotEarnInterest(){
		StockMarketYear year = newAccount();
		year.withdraw(1000);
		assertThat(year.interestEarned(25),is(900));
	}
	
	@Test
	public void totalWithdrawnIncludingCapitalGains(){
		StockMarketYear year = new StockMarketYear(10000, 0, 10);
		year.withdraw(1000);
		assertThat("capital gains tax", year.capitalGainsTaxIncurred(25), is(333));
		assertThat("total withdrawn",year.totalWithdrawn(25),is(1333));
	}
	
	@Test
	public void capitalGainsTaxesDoNotEarnInterest(){
		StockMarketYear year = new StockMarketYear(10000, 0, 10);
		year.withdraw(1000);
		assertThat("capital gains withdrawn", year.capitalGainsWithdrawn(), is(1000));
		assertThat("capital gains tax", year.capitalGainsTaxIncurred(25), is(333));
		assertThat("total withdrawn",year.totalWithdrawn(25),is(1333));
		assertThat("interest earned",year.interestEarned(25),is(866));
	}
	
	@Test
	public void endingBalanceAppliesInterestRate() {
		assertThat(newAccount().endingBalance(25), is(11000));
	}
	
	@Test
	public void multipleWithdrawlsInAYearAreTotaled(){
		StockMarketYear year = newAccount();
		year.withdraw(1000);
		year.withdraw(2000);
		assertThat(year.totalWithdrawn(25),is(3000));
	}
	
	@Test
	public void withdrawingMoreThanPrincipalTakesFromCapitalGains(){
		StockMarketYear year = new StockMarketYear(10000, 3000, 10);
		year.withdraw(1000);
		assertThat(year.capitalGainsWithdrawn(), is(0));
		year.withdraw(3000);
		assertThat(year.capitalGainsWithdrawn(), is(1000));
	}
	
	@Test
	public void capitalGainsTaxIncurredNeedsToCoverCapitalGainsWithdrawnAndTheAdditionalCapitalGainsWithdrawnToPayCapitalGainsTax(){
		StockMarketYear year = new StockMarketYear(10000, 3000, 10);
		year.withdraw(5000);
		assertThat(year.capitalGainsWithdrawn(), is(2000));
		assertThat(year.capitalGainsTaxIncurred(25), is(666));
	}
	
	@Test
	public void capitalGainsTaxIsIncludedInEndingBalance(){
		StockMarketYear year = new StockMarketYear(10000, 3000, 10);
		int amountWithdrawn = 5000;
		year.withdraw(amountWithdrawn);
		int expectedCapitalGainsTax = 666;
		assertThat(year.capitalGainsTaxIncurred(25), is(expectedCapitalGainsTax));
		int expectedStartingBalanceAfterWithdrawls = 10000 - amountWithdrawn - expectedCapitalGainsTax; 
		assertThat(year.endingBalance(25), is((int)(expectedStartingBalanceAfterWithdrawls * 1.10)));
	}

	@Test
	public void nextYear() {
		StockMarketYear thisYear = newAccount();
		StockMarketYear nextYear = thisYear.nextYear(25);
		assertThat("starting balance", nextYear.startingBalance(), is(thisYear.endingBalance(25)));
		assertThat("starting principal", nextYear.startingPrincipal(), is(thisYear.endingPrincipal()));
		assertThat("interest", nextYear.interestRate(), is(thisYear.interestRate()));
	}
	
	private StockMarketYear newAccount() {
		return new StockMarketYear(10000, 10000, 10);
	}
}
