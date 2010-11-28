package com.test.lptdd;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static com.test.lptdd.Dollars.dollars;

import org.junit.Test;

public class TaxRateTest {

    @Test
    public void shouldNotApplyTaxesWhenTaxRateIsZero() {
        TaxRate taxRate = new TaxRate(0);
        assertThat(taxRate.simpleTaxFor(dollars(1000)), is(dollars(0)));
        assertThat(taxRate.compoundTaxFor(dollars(1000)), is(dollars(0)));
    }

    @Test
    public void simpleTaxJustAppliesTaxRateToAmount() throws Exception {
        TaxRate taxRate = new TaxRate(25);
        assertThat(taxRate.simpleTaxFor(dollars(1000)), is(dollars(250)));
    }

    @Test
    public void compoundTaxIsTheAmountOfTaxThatIsIncurredIfYouAlsoPayTaxOnTheTax() throws Exception {
        TaxRate taxRate = new TaxRate(25);
        assertThat(taxRate.compoundTaxFor(dollars(1000)), is(dollars(333)));
    }

    @Test
    public void valueObject() {
        TaxRate rate1a = new TaxRate(33);
        TaxRate rate1b = new TaxRate(33);
        TaxRate rate2 = new TaxRate(40);

        assertThat(rate1a.toString(), is("33.0%"));
        assertThat("same values should be equal", rate1a.equals(rate1b), is(true));
        assertThat("different values should be false", rate1a.equals(rate2), is(false));
        assertThat("save values have the same hash code", rate1a.hashCode() == rate1b.hashCode(), is(true));
    }
}
