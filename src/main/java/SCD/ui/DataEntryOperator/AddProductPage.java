package SCD.ui.DataEntryOperator;

import SCD.ui.Common.ButtonFactory;

import javax.swing.*;
import java.awt.*;

public class AddProductPage extends JFrame {

    public AddProductPage() {
        setTitle("Add New Product");
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        // Reuse DEOSidebar for consistency
        JPanel sidebar = new DEOSidebar();
        add(sidebar, BorderLayout.WEST);

        // Header with title
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(255, 255, 255)); // Consistent header background
        JLabel titleLabel = new JLabel("Add New Product");
        titleLabel.setForeground(Color.black);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        headerPanel.add(titleLabel);
        add(headerPanel, BorderLayout.NORTH);

        // Main content area
        JPanel mainPanel = new JPanel(new GridLayout(6, 2, 10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        mainPanel.setBackground(Color.WHITE);

        JLabel nameLabel = new JLabel("Name:");
        JTextField nameField = new JTextField();

        JLabel categoryLabel = new JLabel("Category:");
        JTextField categoryField = new JTextField();

        JLabel originalPriceLabel = new JLabel("Original Price:");
        JTextField originalPriceField = new JTextField();

        JLabel salePriceLabel = new JLabel("Sale Price:");
        JTextField salePriceField = new JTextField();

        JLabel priceByUnitLabel = new JLabel("Price by Unit:");
        JTextField priceByUnitField = new JTextField();

        JLabel priceByCartonLabel = new JLabel("Price by Carton:");
        JTextField priceByCartonField = new JTextField();

        mainPanel.add(nameLabel);
        mainPanel.add(nameField);
        mainPanel.add(categoryLabel);
        mainPanel.add(categoryField);
        mainPanel.add(originalPriceLabel);
        mainPanel.add(originalPriceField);
        mainPanel.add(salePriceLabel);
        mainPanel.add(salePriceField);
        mainPanel.add(priceByUnitLabel);
        mainPanel.add(priceByUnitField);
        mainPanel.add(priceByCartonLabel);
        mainPanel.add(priceByCartonField);

        add(mainPanel, BorderLayout.CENTER);

        // Footer with action buttons
        JPanel footerPanel = new JPanel();
        footerPanel.setBackground(new Color(255, 255, 255)); // Consistent footer background
        footerPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));

        JButton saveButton = ButtonFactory.createStyledButton("Save");
        saveButton.addActionListener(e -> {
            // Implement save logic here
            JOptionPane.showMessageDialog(this, "Product Added Successfully!");
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
            new AddProductPage().setVisible(true);
        });
    }
}