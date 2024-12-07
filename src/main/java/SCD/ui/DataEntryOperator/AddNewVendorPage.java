package SCD.ui.DataEntryOperator;

import SCD.ui.Common.ButtonFactory;

import javax.swing.*;
import java.awt.*;

public class AddNewVendorPage extends JFrame {

    public AddNewVendorPage() {
        setTitle("Add New Vendor");
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        // Reuse DEOSidebar for consistency
        JPanel sidebar = new DEOSidebar();
        add(sidebar, BorderLayout.WEST);

        // Header with title
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(255, 255, 255)); // Consistent header background
        JLabel titleLabel = new JLabel("Add New Vendor");
        titleLabel.setForeground(Color.black);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        headerPanel.add(titleLabel);
        add(headerPanel, BorderLayout.NORTH);

        // Main content area
        JPanel mainPanel = new JPanel(new GridLayout(7, 2, 10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        mainPanel.setBackground(Color.WHITE);

        JLabel vendorNameLabel = new JLabel("Vendor Name:");
        JTextField vendorNameField = new JTextField();

        JLabel vendorIdLabel = new JLabel("Vendor ID:");
        JTextField vendorIdField = new JTextField();

        JLabel contactPersonLabel = new JLabel("Contact Person:");
        JTextField contactPersonField = new JTextField();

        JLabel phoneNumberLabel = new JLabel("Phone Number:");
        JTextField phoneNumberField = new JTextField();

        JLabel emailAddressLabel = new JLabel("Email Address:");
        JTextField emailAddressField = new JTextField();

        JLabel addressLabel = new JLabel("Address:");
        JTextField addressField = new JTextField();

        mainPanel.add(vendorNameLabel);
        mainPanel.add(vendorNameField);
        mainPanel.add(vendorIdLabel);
        mainPanel.add(vendorIdField);
        mainPanel.add(contactPersonLabel);
        mainPanel.add(contactPersonField);
        mainPanel.add(phoneNumberLabel);
        mainPanel.add(phoneNumberField);
        mainPanel.add(emailAddressLabel);
        mainPanel.add(emailAddressField);
        mainPanel.add(addressLabel);
        mainPanel.add(addressField);

        add(mainPanel, BorderLayout.CENTER);

        // Footer with action buttons
        JPanel footerPanel = new JPanel();
        footerPanel.setBackground(new Color(255, 255, 255)); // Consistent footer background
        footerPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));

        JButton saveButton = ButtonFactory.createStyledButton("Save");
        saveButton.addActionListener(e -> {
            // Implement save logic here
            JOptionPane.showMessageDialog(this, "Vendor Added Successfully!");
        });

        JButton cancelButton = ButtonFactory.createStyledButton("Cancel");
        cancelButton.addActionListener(e -> dispose());
        cancelButton.addActionListener(e -> new DataEntryOperatorDashboard());

        footerPanel.add(saveButton);
        footerPanel.add(cancelButton);

        add(footerPanel, BorderLayout.SOUTH);

        setLocationRelativeTo(null); // Center the window on the screen
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new AddNewVendorPage().setVisible(true);
        });
    }
}