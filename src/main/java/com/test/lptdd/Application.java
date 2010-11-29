package com.test.lptdd;

import java.awt.Container;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Application extends JFrame {

    private static final long serialVersionUID = 1L;

    public Application() {
        this.setSize(900, 400);
        this.setLocation(400, 300);

        final Container content = this.getContentPane();
        content.add(table());
    }

    private JScrollPane table() {
        final JTable table = new JTable(new StockMarketTableModel());
        return new JScrollPane(table);
    }

    public static void main(String[] args) {
        new Application().setVisible(true);
    }
}
