package SCD.ui.DataEntryOperator;

import SCD.model.models.Vendor;
import SCD.ui.Common.ButtonFactory;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
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

        add(footerPanel, BorderLayout.SOUTH);

        setLocationRelativeTo(null); // Center the window on the screen
        setVisible(true);

        // Populate the table with vendors
        List<Vendor> vendors = createVendorList();
        populateTable(vendors);
    }

    private List<Vendor> createVendorList() {
        List<Vendor> vendors = new ArrayList<>();

        // Add vendors to the list with unique vendor codes using setVendorCode
        Vendor vendor1 = new Vendor("Vendor A", "1234567890", "123 Main St, City A");
        vendor1.setVendorCode("V001");
        vendors.add(vendor1);

        Vendor vendor2 = new Vendor("Vendor B", "2345678901", "456 Second St, City B");
        vendor2.setVendorCode("V002");
        vendors.add(vendor2);

        Vendor vendor3 = new Vendor("Vendor C", "3456789012", "789 Third St, City C");
        vendor3.setVendorCode("V003");
        vendors.add(vendor3);

        Vendor vendor4 = new Vendor("Vendor D", "4567890123", "101 Fourth St, City D");
        vendor4.setVendorCode("V004");
        vendors.add(vendor4);

        Vendor vendor5 = new Vendor("Vendor E", "5678901234", "202 Fifth St, City E");
        vendor5.setVendorCode("V005");
        vendors.add(vendor5);

        Vendor vendor6 = new Vendor("Vendor F", "6789012345", "303 Sixth St, City F");
        vendor6.setVendorCode("V006");
        vendors.add(vendor6);

        Vendor vendor7 = new Vendor("Vendor G", "7890123456", "404 Seventh St, City G");
        vendor7.setVendorCode("V007");
        vendors.add(vendor7);

        Vendor vendor8 = new Vendor("Vendor H", "8901234567", "505 Eighth St, City H");
        vendor8.setVendorCode("V008");
        vendors.add(vendor8);
        return vendors;
    }

    public void populateTable(List<Vendor> vendors) {
        tableModel.setRowCount(0); // Clear existing data
        for (Vendor vendor : vendors) {
            tableModel.addRow(new Object[]{
                    vendor.getVendorCode(),
                    vendor.getName(),
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