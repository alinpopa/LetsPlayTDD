package com.test.lptdd;

import javax.swing.table.AbstractTableModel;

public class StockMarketTableModel extends AbstractTableModel{

    private static final long serialVersionUID = 1L;
    private final static String[] COLUMN_TITLES = { "Year", "Starting Balance", "Starting Principal", "Withdrawls", "Appreciation", "Deposits", "Ending Balance" };
    
    @Override
    public int getRowCount() {
        return 1;
    }

    @Override
    public String getColumnName(int column) {
        return COLUMN_TITLES[column];
    }
    
    @Override
    public int getColumnCount() {
        return COLUMN_TITLES.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return columnIndex;
    }

}
