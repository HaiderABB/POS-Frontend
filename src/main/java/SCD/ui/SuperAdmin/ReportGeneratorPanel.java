package SCD.ui.SuperAdmin;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.time.LocalDate;
import java.util.List;
import SCD.ui.Common.Props;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import SCD.model.models.Product;
import SCD.model.models.Sale;
import SCD.model.service.Json.GetReportJSON;

public class ReportGeneratorPanel extends JFrame implements Props {

    public JLabel totalSalesLabel;
    public JLabel totalProfitLabel;
    public JLabel stockLabel;
    public JPanel summaryPanel;
    public JScrollPane scrollPane;

    public DefaultTableModel tableModel;
    public JTable productsTable;
    String[] columnNames = { "Product Code ", "Product Name", "Vendor Code", "Vendor Name", "Remaining Stock" };

    public ReportGeneratorPanel(String branchCode, String duration) {
        setTitle("Report Generator");
        setSize(800, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel headerPanel = new JPanel(new GridLayout(3, 1));
        headerPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel titleLabel = new JLabel("Branch Report", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        headerPanel.add(titleLabel);

        JLabel branchCodeLabel = new JLabel("Branch Code: " + branchCode, SwingConstants.CENTER);
        branchCodeLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        headerPanel.add(branchCodeLabel);

        JLabel durationLabel = new JLabel("Duration: " + duration, SwingConstants.CENTER);
        durationLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        headerPanel.add(durationLabel);

        add(headerPanel, BorderLayout.NORTH);

        summaryPanel = new JPanel(new GridLayout(3, 1));
        summaryPanel.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));

        tableModel = new DefaultTableModel(columnNames, 0);
        productsTable = new JTable(tableModel);
        scrollPane = new JScrollPane(productsTable);

    }

    public ReportGeneratorPanel(String branchCode, String duration, String startDate, String endDate) {
        setTitle("Report Generator");
        setSize(800, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel headerPanel = new JPanel(new GridLayout(4, 1));
        headerPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel titleLabel = new JLabel("Branch Report", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        headerPanel.add(titleLabel);

        JLabel branchCodeLabel = new JLabel("Branch Code: " + branchCode, SwingConstants.CENTER);
        branchCodeLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        headerPanel.add(branchCodeLabel);

        JLabel durationLabel = new JLabel("Duration: " + duration, SwingConstants.CENTER);
        durationLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        headerPanel.add(durationLabel);

        JLabel dateRangeLabel = new JLabel("Date Range: " + startDate + " to " + endDate, SwingConstants.CENTER);
        dateRangeLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        headerPanel.add(dateRangeLabel);

        summaryPanel = new JPanel(new GridLayout(3, 1));
        summaryPanel.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));

        add(headerPanel, BorderLayout.NORTH);
        tableModel = new DefaultTableModel(columnNames, 0);
        productsTable = new JTable(tableModel);

        scrollPane = new JScrollPane(productsTable);

    }

    public void setReportData(GetReportJSON json) {

        List<Sale> sales = json.getSalesData();
        List<Product> products = json.getProducts();

        if (sales == null) {
            totalSalesLabel = new JLabel("Total Sales: PKR 0", SwingConstants.LEFT);
            totalSalesLabel.setFont(new Font("Arial", Font.PLAIN, 16));
            summaryPanel.add(totalSalesLabel);

            totalProfitLabel = new JLabel("Total Profit: PKR 0", SwingConstants.LEFT);
            totalProfitLabel.setFont(new Font("Arial", Font.PLAIN, 16));
            summaryPanel.add(totalProfitLabel);
        } else {
            int totalSales = 0;
            double totalProfit = 0.0;
            for (Sale sale : sales) {
                totalSales += sale.getTotalAmount();
                totalProfit += sale.getProfit();
            }
            totalSalesLabel = new JLabel("Total Sales: PKR " + totalSales, SwingConstants.LEFT);
            totalSalesLabel.setFont(new Font("Arial", Font.PLAIN, 16));
            summaryPanel.add(totalSalesLabel);

            totalProfitLabel = new JLabel("Total Profit: PKR " + totalProfit, SwingConstants.LEFT);
            totalProfitLabel.setFont(new Font("Arial", Font.PLAIN, 16));
            summaryPanel.add(totalProfitLabel);
        }

        if (products == null) {
            clearTable();
            addRow(new Object[] { "-", "-", "-", "-", "-" });

        } else {
            clearTable();
            for (Product product : products) {
                addRow(new Object[] {
                        product.getProductCode(),
                        product.getName(),
                        product.getVendor().getVendorCode(),
                        product.getVendor().getName(),
                        product.getStockQuantity(),

                });
            }
        }

        add(summaryPanel, BorderLayout.WEST);
        add(scrollPane, BorderLayout.CENTER);

        JLabel footerLabel = new JLabel("Generated on: " + LocalDate.now(), SwingConstants.CENTER);
        footerLabel.setFont(new Font("Arial", Font.ITALIC, 14));
        footerLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        add(footerLabel, BorderLayout.SOUTH);

        setLocationRelativeTo(null);
    }

    public void clearTable() {
        tableModel.setRowCount(0);
    }

    public void addRow(Object[] rowData) {
        tableModel.addRow(rowData);
    }

}
