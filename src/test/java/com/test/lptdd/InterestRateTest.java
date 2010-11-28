package com.test.lptdd;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static com.test.lptdd.Dollars.dollars;

import org.junit.Test;

public class InterestRateTest {
    @Test
    public void noRateShouldApplyWhenInterestIsZero() throws Exception {
        InterestRate rate = new InterestRate(0);
        assertThat(rate.interestOn(dollars(1000)), is(dollars(0)));
    }

    @Test
    public void interest() throws Exception {
        InterestRate rate = new InterestRate(10);
        assertThat(rate.interestOn(dollars(1000)), is(dollars(100)));
    }

    @Test
    public void valueObject() throws Exception {
        InterestRate rate1a = new InterestRate(10);
        InterestRate rate1b = new InterestRate(10);
        InterestRate rate2 = new InterestRate(20);

        assertThat(rate1a.toString(), is("10.0%"));
        assertThat("same rate is equal", rate1a.equals(rate1b), is(true));
        assertThat("different rates are not equal", rate1a.equals(rate2), is(false));
        assertThat("same rates have some hash code", rate1a.hashCode() == rate1b.hashCode(), is(true));
    }
}
