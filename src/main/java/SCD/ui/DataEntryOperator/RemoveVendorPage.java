package SCD.ui.DataEntryOperator;

import SCD.ui.Common.ButtonFactory;

import javax.swing.*;
import java.awt.*;
import java.util.regex.Pattern;

public class RemoveVendorPage extends JFrame {

    public RemoveVendorPage() {
        setTitle("Remove Vendor");
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        // Reuse DEOSidebar for consistency
        JPanel sidebar = new DEOSidebar();
        add(sidebar, BorderLayout.WEST);

        // Header with title
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(255, 255, 255)); // Consistent header background
        JLabel titleLabel = new JLabel("Remove Vendor");
        titleLabel.setForeground(Color.black);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        headerPanel.add(titleLabel);
        add(headerPanel, BorderLayout.NORTH);

        // Main content area
        JPanel mainPanel = new JPanel(new GridLayout(2, 2, 10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        mainPanel.setBackground(Color.WHITE);

        JLabel vendorIdLabel = new JLabel("Vendor ID:");
        JTextField vendorIdField = new JTextField();
        vendorIdLabel.setFont(new Font("Arial", Font.PLAIN, 30));
        vendorIdField.setFont(new Font("Arial", Font.PLAIN, 26));
        vendorIdField.setPreferredSize(new Dimension(200, 40));

        mainPanel.add(vendorIdLabel);
        mainPanel.add(vendorIdField);

        add(mainPanel, BorderLayout.CENTER);

        // Footer with action buttons
        JPanel footerPanel = new JPanel();
        footerPanel.setBackground(new Color(255, 255, 255)); // Consistent footer background
        footerPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));

        JButton removeButton = ButtonFactory.createStyledButton("Remove");
        removeButton.addActionListener(e -> {
            String vendorId = vendorIdField.getText().trim();

            if (!validateVendorId(vendorId)) {
                return;
            }

            if (!checkVendorExists(vendorId)) {
                JOptionPane.showMessageDialog(this, "Vendor ID does not exist!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Implement remove logic here
            JOptionPane.showMessageDialog(this, "Vendor Removed Successfully!");
        });

        JButton cancelButton = ButtonFactory.createStyledButton("Cancel");
        cancelButton.addActionListener(e -> dispose());
        cancelButton.addActionListener(e -> new DataEntryOperatorDashboard());

        footerPanel.add(removeButton);
        footerPanel.add(cancelButton);

        add(footerPanel, BorderLayout.SOUTH);

        setLocationRelativeTo(null); // Center the window on the screen
        setVisible(true);
    }

    private boolean validateVendorId(String vendorId) {
        if (vendorId.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter a Vendor ID!", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if (!Pattern.matches("VM-\\d{4}", vendorId)) {
            JOptionPane.showMessageDialog(this, "Vendor ID must follow the format 'VM-XXXX'.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        return true;
    }

    private boolean checkVendorExists(String vendorId) {
        // Placeholder for database check
        // Replace with actual database call
        return vendorId.equals("VM-0001"); // Example: only "VM-0001" exists
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new RemoveVendorPage().setVisible(true);
        });
    }
}