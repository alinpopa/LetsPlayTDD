package com.test.lptdd;

import static com.test.lptdd.Dollars.dollars;

public class TaxRate {

	private final double rate;

	public TaxRate(final double rateAsPercentage) {
		this.rate = rateAsPercentage / 100.0;
	}

	public Dollars simpleTaxFor(final Dollars amount) {
		return dollars((int) (rate * amount.toInt()));
	}

	public Dollars compoundTaxFor(final Dollars amount) {
		int amountAsInt = amount.toInt();
		return dollars((int) ((amountAsInt / (1 - rate)) - amountAsInt));
	}

	@Override
	public String toString() {
		return (rate * 100) + "%";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(rate);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		TaxRate other = (TaxRate) obj;
		if (Double.doubleToLongBits(rate) != Double
				.doubleToLongBits(other.rate)) {
			return false;
		}
		return true;
	}
}
