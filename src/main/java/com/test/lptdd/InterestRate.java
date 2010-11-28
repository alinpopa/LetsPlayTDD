package com.test.lptdd;

import static com.test.lptdd.Dollars.dollars;

import java.math.BigDecimal;

public class InterestRate {

    private final BigDecimal rate;

    public InterestRate(final int rateAsPercentage) {
        this.rate = new BigDecimal(rateAsPercentage).divide(new BigDecimal(100));
    }

    public Dollars interestOn(final Dollars amount) {
        final BigDecimal amountAsBigDecimal = new BigDecimal(amount.toInt());
        return dollars(amountAsBigDecimal.multiply(rate).intValue());
    }

    @Override
    public String toString() {
        return rate.multiply(new BigDecimal(100)) + "%";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((rate == null) ? 0 : rate.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        InterestRate other = (InterestRate) obj;
        if (rate == null) {
            if (other.rate != null)
                return false;
        } else if (!rate.equals(other.rate))
            return false;
        return true;
    }
}
