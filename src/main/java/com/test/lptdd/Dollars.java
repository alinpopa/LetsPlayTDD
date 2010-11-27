package com.test.lptdd;


public class Dollars {

	private final int amount;

	public Dollars(final int amount) {
		this.amount = amount;
	}

	public static Dollars dollars(final int amount){
		return new Dollars(amount);
	}
	
	public int toInt() {
		return amount;
	}
	
	public Dollars add(final Dollars dollars) {
		return dollars(amount + dollars.amount);
	}
	
	public Dollars subtract(final Dollars dollars) {
		return dollars(amount - dollars.amount);
	}

	public Dollars subtractToZero(final Dollars dollars) {
		int result = amount - dollars.amount;
		return dollars(Math.max(0, result));
	}

	public Dollars multiply(final Dollars with) {
		return dollars(this.amount * with.amount);
	}
	
	@Override
	public String toString() {
		return "$"+amount;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + amount;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		if (getClass() != obj.getClass()) return false;
		Dollars other = (Dollars) obj;
		if (amount != other.amount) return false;
		return true;
	}
}
