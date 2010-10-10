package com.test.lptdd;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Ignore;
import org.junit.Test;

public class SavingsAccountYearTest {

	@Test
	public void startingBalanceMatchesConstructor() {
		assertThat(newAccount().startingBalance(), is(10000));
	}

	@Test
	public void startingPrincipalMatchesConstructor(){
		SavingsAccountYear year = new SavingsAccountYear(10000, 3000, 10);
		assertThat(year.startingPrincipal(), is(3000));
	}
	
	@Test
	public void interestRateMatchesConstructor() {
		assertThat(newAccount().interestRate(), is(10));
	}

	@Test
	public void startingCapitalGainsIsStartingBalanceMinusStartingPrincipal(){
		SavingsAccountYear year = new SavingsAccountYear(10000, 3000, 10);
		assertThat(year.startingCapitalGains(), is(7000));
	}
	
	@Test
	public void endingPrincipalConsidersWithdrawals(){
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
	@Ignore
	public void endingCapitalGainsIncludesInterestEarned(){
		SavingsAccountYear year = new SavingsAccountYear(10000, 3000, 10);
		assertThat(year.startingCapitalGains(),is(7000));
		assertThat(year.endingCapitalGains(),is(4000));
	}
	
	@Test
	public void endingBalanceAppliesInterestRate() {
		assertThat(newAccount().endingBalance(25), is(11000));
	}

	@Test
	public void withdrawingFundsDoNotEarnInterest(){
		SavingsAccountYear year = newAccount();
		year.withdraw(1000);
		assertThat(year.endingBalance(25),is(9900));
	}
	
	@Test
	public void multipleWithdrawlsInAYearAreTotaled(){
		SavingsAccountYear year = newAccount();
		year.withdraw(1000);
		year.withdraw(2000);
		assertThat(year.totalWithdrawn(),is(3000));
	}
	
	@Test
	public void withdrawingMoreThanPrincipalTakesFromCapitalGains(){
		SavingsAccountYear year = new SavingsAccountYear(10000, 3000, 10);
		year.withdraw(1000);
		assertThat(year.capitalGainsWithdrawn(), is(0));
		year.withdraw(3000);
		assertThat(year.capitalGainsWithdrawn(), is(1000));
	}
	
	@Test
	public void capitalGainsTaxIncurredNeedsToCoverCapitalGainsWithdrawnAndTheAdditionalCapitalGainsWithdrawnToPayCapitalGainsTax(){
		SavingsAccountYear year = new SavingsAccountYear(10000, 3000, 10);
		year.withdraw(5000);
		assertThat(year.capitalGainsWithdrawn(), is(2000));
		assertThat(year.capitalGainsTaxIncurred(25), is(666));
	}
	
	@Test
	public void capitalGainsTaxIsIncludedInEndingBalance(){
		SavingsAccountYear year = new SavingsAccountYear(10000, 3000, 10);
		int amountWithdrawn = 5000;
		year.withdraw(amountWithdrawn);
		int expectedCapitalGainsTax = 666;
		assertThat(year.capitalGainsTaxIncurred(25), is(expectedCapitalGainsTax));
		int expectedStartingBalanceAfterWithdrawls = 10000 - amountWithdrawn - expectedCapitalGainsTax; 
		assertThat(year.endingBalance(25), is((int)(expectedStartingBalanceAfterWithdrawls * 1.10)));
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
	
	private SavingsAccountYear newAccount() {
		return new SavingsAccountYear(10000, 10000, 10);
	}
}