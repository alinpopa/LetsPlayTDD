package com.test.lptdd;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class TaxRateTest {

	@Test
	public void nothing(){
		TaxRate taxRate = new TaxRate(0);
		assertThat(taxRate.taxFor(1000),is(0));
	}
	
	@Test
	public void simpleTax() throws Exception {
		TaxRate taxRate = new TaxRate(25);
		assertThat(taxRate.taxFor(1000),is(250));
	}
}
