package com.test.lptdd;

import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.is;

import org.junit.Before;
import org.junit.Test;

public class StockMarketTableModelTest {
    
    
    private StockMarketTableModel model;
    
    @Before
    public void setUp(){
        model = new StockMarketTableModel();
    }

    @Test
    public void columns() throws Exception {
        assertThat(model.getColumnCount(), is(7));
        assertThat(model.getColumnName(0), is("Year"));
        assertThat(model.getColumnName(1), is("Starting Balance"));
        assertThat(model.getColumnName(2), is("Starting Principal"));
    }

    @Test
    public void firstRow(){
    }
}
