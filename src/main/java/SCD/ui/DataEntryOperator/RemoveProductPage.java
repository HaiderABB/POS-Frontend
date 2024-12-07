package SCD.ui.DataEntryOperator;

import SCD.ui.Common.ButtonFactory;
import javax.swing.*;
import java.awt.*;
import java.util.regex.Pattern;

public class RemoveProductPage extends JFrame {

    public RemoveProductPage() {
        setTitle("Remove Product");
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        // Reuse DEOSidebar for consistency
        JPanel sidebar = new DEOSidebar();
        add(sidebar, BorderLayout.WEST);

        // Header with title
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(255, 255, 255)); // Consistent header background
        JLabel titleLabel = new JLabel("Remove Product");
        titleLabel.setForeground(Color.black);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        headerPanel.add(titleLabel);
        add(headerPanel, BorderLayout.NORTH);

        // Main content area
        JPanel mainPanel = new JPanel(new GridLayout(2, 2, 10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        mainPanel.setBackground(Color.WHITE);

        JLabel productIdLabel = new JLabel("Product ID:");
        JTextField productIdField = new JTextField();
        productIdLabel.setFont(new Font("Arial", Font.PLAIN, 30));
        productIdField.setFont(new Font("Arial", Font.PLAIN, 26));
        productIdField.setPreferredSize(new Dimension(200, 40));

        mainPanel.add(productIdLabel);
        mainPanel.add(productIdField);

        add(mainPanel, BorderLayout.CENTER);

        // Footer with action buttons
        JPanel footerPanel = new JPanel();
        footerPanel.setBackground(new Color(255, 255, 255)); // Consistent footer background
        footerPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));

        JButton removeButton = ButtonFactory.createStyledButton("Remove");
        removeButton.addActionListener(e -> {
            String productId = productIdField.getText().trim();

            if (!validateProductId(productId)) {
                return;
            }

            if (!checkProductExists(productId)) {
                JOptionPane.showMessageDialog(this, "Product ID does not exist!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Implement remove logic here
            JOptionPane.showMessageDialog(this, "Product Removed Successfully!");
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

    private boolean validateProductId(String productId) {
        if (productId.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter a Product ID!", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if (!Pattern.matches("PM-\\d{4}", productId)) {
            JOptionPane.showMessageDialog(this, "Product ID must follow the format 'PM-XXXX'.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        return true;
    }

    private boolean checkProductExists(String productId) {
        // Placeholder for database check
        // Replace with actual database call
        return productId.equals("PM-0001"); // Example: only "PM-0001" exists
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new RemoveProductPage().setVisible(true);
        });
    }
}