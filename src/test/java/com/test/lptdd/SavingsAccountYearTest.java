package com.test.lptdd;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class SavingsAccountYearTest {

	@Test
	public void startingBalanceMatchesConstructor() {
		assertThat(newAccount().startingBalance(), is(10000));
	}

	@Test
	public void interestRateMatchesConstructor() {
		assertThat(newAccount().interestRate(), is(10));
	}

	@Test
	public void endingBalanceAppliesInterestRate() {
		assertThat(newAccount().endingBalance(25), is(11000));
	}

	@Test
	public void nextYearStartingBalanceEqualsThisYearEndingBalance() {
		SavingsAccountYear thisYear = newAccount();
		assertThat(thisYear.nextYear(25).startingBalance(), is(thisYear.endingBalance(25)));
	}

	@Test
	public void nextYearInterestRateEqualsThisYearInterestRate() {
		SavingsAccountYear thisYear = newAccount();
		assertThat("next year interest rate", thisYear.nextYear(25).interestRate(), is(thisYear.interestRate()));
	}

	@Test
	public void withdrawingFundsOccursAtTheBeginningOfTheYear(){
		SavingsAccountYear year = new SavingsAccountYear(10000, 10);
		year.withdraw(1000);
		assertThat(year.endingBalance(25),is(9900));
	}
	
	@Test
	public void multipleWithdrawlsInAYear(){
		SavingsAccountYear year = new SavingsAccountYear(10000, 10);
		year.withdraw(1000);
		year.withdraw(2000);
		assertThat(year.totalWithdrawn(),is(3000));
	}
	
	@Test
	public void startingPrincipal(){
		SavingsAccountYear year = new SavingsAccountYear(10000, 3000, 10);
		assertThat(year.startingPrincipal(), is(3000));
	}
	
	@Test
	public void endingPrincipal(){
		SavingsAccountYear year = new SavingsAccountYear(10000, 3000, 10);
		year.withdraw(2000);
		assertThat("ending principal", year.endingPrincipal(), is(1000));
	}
	
	@Test
	public void endingPrincipalNeverGoesBelowZero(){
		SavingsAccountYear year = new SavingsAccountYear(10000, 3000, 10);
		year.withdraw(4000);
		assertThat("ending principal", year.endingPrincipal(), is(0));
	}
	
	@Test
	public void capitalGainsWithdrawn(){
		SavingsAccountYear year = new SavingsAccountYear(10000, 3000, 10);
		year.withdraw(1000);
		assertThat(year.capitalGainsWithdrawn(), is(0));
		year.withdraw(3000);
		assertThat(year.capitalGainsWithdrawn(), is(1000));
	}
	
	@Test
	public void capitalGainsTaxIncurred(){
		SavingsAccountYear year = new SavingsAccountYear(10000, 3000, 10);
		year.withdraw(5000);
		assertThat(year.capitalGainsWithdrawn(), is(2000));
		assertThat(year.capitalGainsTaxIncurred(25), is(500));
	}
	
	@Test
	public void capitalGainsTaxIsIncludedInEndingBalance(){
		SavingsAccountYear year = new SavingsAccountYear(10000, 3000, 10);
		year.withdraw(5000);
		assertThat(year.capitalGainsTaxIncurred(25), is(500));
		assertThat(year.endingBalance(25), is(10000 - 5000 - 500 + 450));
		
		// TODO: Need to withdraw enough money to cover capital gains tax; that money will also be taxed
	}
	
//	@Test
//	public void withdrawingMoreThanPrincipalIncursCapitalGainsTax(){
//		SavingsAccountYear year = new SavingsAccountYear(10000, 3000, 10);
//		year.withdraw(3000);
//		assertThat(year.endingBalance(), is(7700));
//		year.withdraw(5000);
//		assertThat(year.endingBalance(), is(2000 + 200 - 1250));
//	}
	
	private SavingsAccountYear newAccount() {
		return new SavingsAccountYear(10000, 10);
	}
}
