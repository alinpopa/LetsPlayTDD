package com.test.lptdd;

import static com.test.lptdd.Dollars.dollars;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class TaxRate {

    private final BigDecimal rate;

    public TaxRate(final int rateAsPercentage) {
        this.rate = new BigDecimal(rateAsPercentage).divide(new BigDecimal(100));
    }

    public Dollars simpleTaxFor(final Dollars amount) {
        final BigDecimal amountAsBigDecimal = new BigDecimal(amount.toInt());
        return dollars(rate.multiply(amountAsBigDecimal).intValue());
    }

    public Dollars compoundTaxFor(final Dollars amount) {
        BigDecimal amountAsBigDecimal = new BigDecimal(amount.toInt());
        BigDecimal calculatedRateAsBigDecimal = BigDecimal.ONE.subtract(rate);
        return dollars(amountAsBigDecimal.divide(calculatedRateAsBigDecimal, RoundingMode.DOWN).subtract(amountAsBigDecimal).intValue());
    }

    @Override
    public String toString() {
        double rateAsDouble = rate.doubleValue();
        return (rateAsDouble * 100) + "%";
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
        TaxRate other = (TaxRate) obj;
        if (rate == null) {
            if (other.rate != null)
                return false;
        } else if (!rate.equals(other.rate))
            return false;
        return true;
    }
}
