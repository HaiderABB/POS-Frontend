package SCD.ui.SuperAdmin;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;

public class ReportGeneratorPanel extends JFrame {

    public ReportGeneratorPanel(String branchCode, String duration, String startDate, String endDate) {
        setTitle("Report Generator");
        setSize(600, 500);
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

        add(headerPanel, BorderLayout.NORTH);

        JPanel summaryPanel = new JPanel(new GridLayout(3, 1));
        summaryPanel.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));

        JLabel totalSalesLabel = new JLabel("Total Sales: $10,000", SwingConstants.LEFT);
        totalSalesLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        summaryPanel.add(totalSalesLabel);

        JLabel totalProfitLabel = new JLabel("Total Profit: $3,000", SwingConstants.LEFT);
        totalProfitLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        summaryPanel.add(totalProfitLabel);

        JLabel stockLabel = new JLabel("Remaining Stock: 500 Units", SwingConstants.LEFT);
        stockLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        summaryPanel.add(stockLabel);

        add(summaryPanel, BorderLayout.CENTER);

        JLabel footerLabel = new JLabel("Generated on: " + LocalDate.now(), SwingConstants.CENTER);
        footerLabel.setFont(new Font("Arial", Font.ITALIC, 14));
        footerLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(footerLabel, BorderLayout.SOUTH);

        setLocationRelativeTo(null);
    }
}
