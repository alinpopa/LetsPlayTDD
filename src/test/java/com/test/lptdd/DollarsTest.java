package com.test.lptdd;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static com.test.lptdd.Dollars.dollars;

import org.junit.Test;

public class DollarsTest {
	
	@Test
	public void dollarsShouldHaveValidStringRepresentation() throws Exception {
		Dollars dollars = new Dollars(10);
		
		assertThat(dollars.toString(), is("$10"));
	}
	
	@Test
	public void dollarsWithSameAmountShouldBeEqual() throws Exception {
		Dollars dollars1a = new Dollars(10);
		Dollars dollars1b = new Dollars(10);
		
		assertThat(dollars1a, is(dollars1b));
	}
	
	@Test
	public void dollarsWithDifferentAmountsShouldNotBeEqual() throws Exception {
		Dollars dollars1a = new Dollars(10);
		Dollars dollars1b = new Dollars(20);
		
		assertThat(dollars1a, is(not(dollars1b)));
	}
	
	@Test
	public void equalDollarsShouldHaveSameHashCode() throws Exception {
		Dollars dollars1a = new Dollars(10);
		Dollars dollars1b = new Dollars(10);
		
		assertThat(dollars1a.hashCode(), is(dollars1b.hashCode()));
	}
	
	@Test
	public void addition() throws Exception {
		assertThat(dollars(10).add(dollars(30)), is(dollars(40)));
	}
	
	@Test
	public void positiveResultSubtraction() throws Exception {
		assertThat(dollars(50).subtract(dollars(30)), is(dollars(20)));
	}
	
	@Test
	public void negativeResultSubtraction() throws Exception {
		assertThat(dollars(40).subtract(dollars(100)), is(dollars(-60)));
	}
	
	@Test
	public void positiveResultWhenSubtractToZero() throws Exception {
		assertThat(dollars(50).subtractToZero(dollars(30)), is(dollars(20)));
	}
	
	@Test
	public void noNegativeResultWhenSubtractToZeroButReturnZeroInstead() throws Exception {
		assertThat(dollars(40).subtractToZero(dollars(100)), is(dollars(0)));
	}
	
	@Test
	public void toInt() throws Exception {
		assertThat(dollars(20).toInt(), is(20));
	}
	
	@Test
	public void multiply() throws Exception {
		assertThat(dollars(20).multiply(dollars(5)), is(dollars(100)));
	}
}
