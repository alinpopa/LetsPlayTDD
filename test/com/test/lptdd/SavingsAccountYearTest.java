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
		assertThat(newAccount().endingBalance(), is(11000));
	}

	@Test
	public void nextYearStartingBalanceEqualsThisYearEndingBalance() {
		SavingsAccountYear thisYear = newAccount();
		assertThat(thisYear.nextYear().startingBalance(), is(thisYear.endingBalance()));
	}

	@Test
	public void nextYearInterestRateEqualsThisYearInterestRate() {
		SavingsAccountYear thisYear = newAccount();
		assertThat("next year interest rate", thisYear.nextYear().interestRate(), is(thisYear.interestRate()));
	}

	@Test
	public void withdrawingFundsOccursAtTheBeginningOfTheYear(){
		SavingsAccountYear year = new SavingsAccountYear(10000, 10);
		year.withdraw(1000);
		assertThat(year.endingBalance(),is(9900));
	}
	
	@Test
	public void multipleWithdrawlsInAYear(){
		SavingsAccountYear year = new SavingsAccountYear(10000, 10);
		year.withdraw(1000);
		year.withdraw(2000);
		assertThat(year.endingBalance(),is(7700));
	}
	
	@Test
	public void startingPrincipal(){
		SavingsAccountYear year = new SavingsAccountYear(10000, 7000, 10);
		assertThat(year.startingPrincipal(), is(3000));
	}
	
	@Test
	public void endingPrinciple(){
		SavingsAccountYear year = new SavingsAccountYear(10000, 7000, 10);
		assertThat("starting principle", year.startingPrincipal(), is(3000));
		year.withdraw(2000);
		assertThat("ending principle", year.endingPrincipal(), is(1000));
	}
	
	@Test
	public void endingPrincipalNeverGoesBelowZero(){
		SavingsAccountYear year = new SavingsAccountYear(10000, 7000, 10);
		assertThat("starting principle", year.startingPrincipal(), is(3000));
		year.withdraw(4000);
		assertThat("ending principle", year.endingPrincipal(), is(0));
	}
	
//	@Test
//	public void withdrawingMoreThanPrincipleIncursCapitalGainsTax(){
//		SavingsAccountYear year = new SavingsAccountYear(10000, 7000, 10);
//		year.withdraw(3000);
//		assertThat(year.endingBalance(), is(7700));
//		year.withdraw(5000);
//		assertThat(year.endingBalance(), is(2000 + 200 - 1250));
//	}
	
	private SavingsAccountYear newAccount() {
		return new SavingsAccountYear(10000, 10);
	}
}
