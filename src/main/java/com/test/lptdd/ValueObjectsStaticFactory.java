package com.test.lptdd;

public final class ValueObjectsStaticFactory {
	public static Dollars dollars(final int amount){
		return new Dollars(amount);
	}
}
