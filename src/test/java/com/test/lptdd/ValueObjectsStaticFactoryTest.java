package com.test.lptdd;

import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.is;

import org.junit.Test;

public class ValueObjectsStaticFactoryTest {
	@Test
	public void dollarsFactoryMethodShouldReturnNewDollarsObject() throws Exception {
		Dollars dollars = ValueObjectsStaticFactory.dollars(100);
		
		assertThat(dollars.toString(), is("$100"));
	}
}
