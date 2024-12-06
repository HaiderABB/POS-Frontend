package SCD.ui.Cashier;

import SCD.ui.Common.ButtonFactory;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class ViewSales extends JFrame {

    public ViewSales() {
        setTitle("View Sales");
//        setSize(800, 600);
        setSize(1000, 700);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        // Sidebar
        cashierSidebar sidebar = new cashierSidebar();
        add(sidebar, BorderLayout.WEST);

        // Header with title
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(50, 50, 50));
        JLabel titleLabel = new JLabel("View Sales");
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        headerPanel.add(titleLabel);
        add(headerPanel, BorderLayout.NORTH);

        // Main table panel
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        String[] columnNames = {"Date", "Transaction ID", "Amount"};
        Object[][] data = {
                {"2023-10-01", "TXN12345", "$100.00"},
                {"2023-10-02", "TXN12346", "$150.00"},
                {"2023-10-03", "TXN12347", "$200.00"}
        };

        DefaultTableModel model = new DefaultTableModel(data, columnNames);
        JTable salesTable = new JTable(model);
        salesTable.setFillsViewportHeight(true);
        salesTable.setRowHeight(30);
        salesTable.setFont(new Font("Arial", Font.PLAIN, 14));
        JScrollPane scrollPane = new JScrollPane(salesTable);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        // Footer with refresh button
        JPanel footerPanel = new JPanel();
        footerPanel.setBackground(new Color(50, 50, 50));
        JButton refreshButton = ButtonFactory.createStyledButton("Refresh");
        refreshButton.addActionListener(e -> {
            // Implement refresh logic here
            JOptionPane.showMessageDialog(this, "Sales data refreshed!");
        });
        footerPanel.add(refreshButton);

        add(mainPanel, BorderLayout.CENTER);
        add(footerPanel, BorderLayout.SOUTH);

        setLocationRelativeTo(null); // Center window on screen
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ViewSales viewSalesPage = new ViewSales();
            viewSalesPage.setVisible(true);
        });
    }
}
