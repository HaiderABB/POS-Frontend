package SCD.ui.DataEntryOperator;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.text.NumberFormat;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.text.NumberFormatter;

import SCD.ui.Common.ButtonFactory;

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

        JPanel mainPanel = new JPanel(new GridLayout(3, 2, 10, 10));

        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        mainPanel.setBackground(Color.WHITE);

        JLabel vendorNameLabel = new JLabel("Vendor Name:");
        JTextField vendorNameField = new JTextField();

        JLabel addressLabel = new JLabel("Address:");
        JTextField addressField = new JTextField();

        JLabel phoneNumberLabel = new JLabel("Phone Number:");
        NumberFormat format = NumberFormat.getInstance();
        format.setGroupingUsed(false);
        NumberFormatter formatter = new NumberFormatter(format);
        formatter.setValueClass(Long.class);
        formatter.setAllowsInvalid(false);
        formatter.setMinimum(0L);
        JFormattedTextField phoneNumberField = new JFormattedTextField(formatter);

        mainPanel.add(vendorNameLabel);
        mainPanel.add(vendorNameField);
        mainPanel.add(addressLabel);
        mainPanel.add(addressField);
        mainPanel.add(phoneNumberLabel);
        mainPanel.add(phoneNumberField);

        add(mainPanel, BorderLayout.CENTER);

        // Footer with action buttons
        JPanel footerPanel = new JPanel();
        footerPanel.setBackground(new Color(255, 255, 255)); // Consistent footer background
        footerPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));

        JButton saveButton = ButtonFactory.createStyledButton("Save");
        saveButton.addActionListener(e -> {

            String vendorName = vendorNameField.getText().trim();
            String address = addressField.getText().trim();
            String phoneNumber = phoneNumberField.getText().trim();

            if (vendorName.isEmpty() || address.isEmpty() || phoneNumber.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill out all fields!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Assume vendor code will be received from backend
            String vendorCode = receiveVendorCodeFromBackend();

            // Implement save logic here
            JOptionPane.showMessageDialog(this, "Vendor Added Successfully!\nVendor Code: " + vendorCode);
        });

        JButton cancelButton = ButtonFactory.createStyledButton("Cancel");
        cancelButton.addActionListener(e -> {
            dispose();
            new DataEntryOperatorDashboard().setVisible(true);
        });

        footerPanel.add(saveButton);
        footerPanel.add(cancelButton);

        add(footerPanel, BorderLayout.SOUTH);

        setLocationRelativeTo(null); // Center the window on the screen
        setVisible(true);
    }

    private String receiveVendorCodeFromBackend() {
        // Placeholder for backend call to receive vendor code
        return "VM-0001"; // Example code, replace with actual backend call
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new AddNewVendorPage().setVisible(true);
        });
    }
}