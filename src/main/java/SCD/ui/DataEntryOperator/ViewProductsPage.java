package SCD.ui.DataEntryOperator;

import SCD.ui.Common.ButtonFactory;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class ViewProductsPage extends JFrame {

    private DefaultTableModel tableModel;

    public ViewProductsPage() {
        setTitle("View Products");
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        // Reuse DEOSidebar for consistency
        JPanel sidebar = new DEOSidebar();
        add(sidebar, BorderLayout.WEST);

        // Header with title
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(255, 255, 255)); // Consistent header background
        JLabel titleLabel = new JLabel("Products List");
        titleLabel.setForeground(Color.black);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        headerPanel.add(titleLabel);
        add(headerPanel, BorderLayout.NORTH);

        // Main content area
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(Color.WHITE);

        String[] columnNames = {"Name", "Category", "Original Price", "Sale Price", "Price by Unit", "Price by Carton"};
        tableModel = new DefaultTableModel(columnNames, 0);
        JTable productTable = new JTable(tableModel);
        JScrollPane tableScrollPane = new JScrollPane(productTable);
        mainPanel.add(tableScrollPane, BorderLayout.CENTER);

        // Sample data
        tableModel.addRow(new Object[]{"Product A", "Category 1", "10.00", "12.00", "1.00", "10.00"});
        tableModel.addRow(new Object[]{"Product B", "Category 2", "20.00", "25.00", "2.50", "20.00"});

        add(mainPanel, BorderLayout.CENTER);

        // Footer with action buttons
        JPanel footerPanel = new JPanel();
        footerPanel.setBackground(new Color(255, 255, 255)); // Consistent footer background
        footerPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));

        JButton closeButton = ButtonFactory.createStyledButton("Close");
        closeButton.addActionListener(e -> new DataEntryOperatorDashboard());
        closeButton.addActionListener(e -> dispose());

        footerPanel.add(closeButton);

        add(footerPanel, BorderLayout.SOUTH);

        setLocationRelativeTo(null); // Center the window on the screen
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new ViewProductsPage().setVisible(true);
        });
    }
}