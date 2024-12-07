package SCD.ui.DataEntryOperator;

import SCD.ui.Common.ButtonFactory;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class ViewVendorsPage extends JFrame {

    private DefaultTableModel tableModel;

    public ViewVendorsPage() {
        setTitle("View Vendors");
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        // Reuse DEOSidebar for consistency
        JPanel sidebar = new DEOSidebar();
        add(sidebar, BorderLayout.WEST);

        // Header with title
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(255, 255, 255)); // Consistent header background
        JLabel titleLabel = new JLabel("Vendors List");
        titleLabel.setForeground(Color.black);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        headerPanel.add(titleLabel);
        add(headerPanel, BorderLayout.NORTH);

        // Main content area
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(Color.WHITE);

        String[] columnNames = {"Vendor Code", "Vendor Name", "Phone Number", "Address"};
        tableModel = new DefaultTableModel(columnNames, 0);
        JTable vendorTable = new JTable(tableModel);
        JScrollPane tableScrollPane = new JScrollPane(vendorTable);
        mainPanel.add(tableScrollPane, BorderLayout.CENTER);

        add(mainPanel, BorderLayout.CENTER);

        // Footer with action buttons
        JPanel footerPanel = new JPanel();
        footerPanel.setBackground(new Color(255, 255, 255)); // Consistent footer background
        footerPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));

        JButton closeButton = ButtonFactory.createStyledButton("Close");
        closeButton.addActionListener(e -> new DataEntryOperatorDashboard());
        closeButton.addActionListener(e -> dispose());

        footerPanel.add(closeButton);
        tableModel.addRow(new Object[]{"V001", "Vendor A", "123-456-7890", "123 Main St, City, State, 12345"});
        tableModel.addRow(new Object[]{"V002", "Vendor B", "098-765-4321", "456 Elm St, City, State, 67890"});

        add(footerPanel, BorderLayout.SOUTH);

        setLocationRelativeTo(null); // Center the window on the screen
        setVisible(true);
    }

    public void populateTable(List<Vendor> vendors) {
        tableModel.setRowCount(0); // Clear existing data
        for (Vendor vendor : vendors) {
            tableModel.addRow(new Object[]{
                    vendor.getVendorCode(),
                    vendor.getVendorName(),
                    vendor.getPhoneNumber(),
                    vendor.getAddress()
            });
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ViewVendorsPage frame = new ViewVendorsPage();
            frame.setVisible(true);
        });
    }
}