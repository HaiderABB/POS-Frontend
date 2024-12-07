package SCD.ui.DataEntryOperator;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import SCD.ui.Common.ButtonFactory;

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

        JPanel mainPanel = new JPanel(new GridLayout(7, 2, 10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        mainPanel.setBackground(Color.WHITE);

        JLabel nameLabel = new JLabel("Name:");
        JTextField nameField = new JTextField();

        JLabel categoryLabel = new JLabel("Category:");

        String[] categories = {
                "Electronics", "Home Appliances", "Fashion",
                "Health & Beauty", "Grocery", "Books & Stationery",
                "Home Decor", "Automotive"
        };
        JComboBox<String> categoryComboBox = new JComboBox<>(categories);
        styleComboBox(categoryComboBox);

        JLabel originalPriceLabel = new JLabel("Original Price:");
        JTextField originalPriceField = new JTextField();

        JLabel salePriceLabel = new JLabel("Sale Price:");
        JTextField salePriceField = new JTextField();

        JLabel priceByUnitLabel = new JLabel("Price by Unit:");
        JTextField priceByUnitField = new JTextField();

        JLabel priceByCartonLabel = new JLabel("Price by Carton:");
        JTextField priceByCartonField = new JTextField();

        JLabel stockQuantityLabel = new JLabel("Stock Quantity:");
        JTextField stockQuantityField = new JTextField();

        mainPanel.add(nameLabel);
        mainPanel.add(nameField);
        mainPanel.add(categoryLabel);
        mainPanel.add(categoryComboBox);
        mainPanel.add(originalPriceLabel);
        mainPanel.add(originalPriceField);
        mainPanel.add(salePriceLabel);
        mainPanel.add(salePriceField);
        mainPanel.add(priceByUnitLabel);
        mainPanel.add(priceByUnitField);
        mainPanel.add(priceByCartonLabel);
        mainPanel.add(priceByCartonField);

        mainPanel.add(stockQuantityLabel);
        mainPanel.add(stockQuantityField);

        add(mainPanel, BorderLayout.CENTER);

        // Footer with action buttons
        JPanel footerPanel = new JPanel();
        footerPanel.setBackground(new Color(255, 255, 255)); // Consistent footer background
        footerPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));

        JButton saveButton = ButtonFactory.createStyledButton("Save");
        saveButton.addActionListener(e -> {

            String name = nameField.getText().trim();
            String category = (String) categoryComboBox.getSelectedItem();
            String originalPriceText = originalPriceField.getText().trim();
            String salePriceText = salePriceField.getText().trim();
            String priceByUnitText = priceByUnitField.getText().trim();
            String priceByCartonText = priceByCartonField.getText().trim();
            String stockQuantityText = stockQuantityField.getText().trim();

            if (name.isEmpty() || category.isEmpty() || originalPriceText.isEmpty() || salePriceText.isEmpty()
                    || priceByUnitText.isEmpty() || priceByCartonText.isEmpty() || stockQuantityText.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill out all fields with valid values!", "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            try {
                double originalPrice = Double.parseDouble(originalPriceText);
                double salePrice = Double.parseDouble(salePriceText);
                double priceByUnit = Double.parseDouble(priceByUnitText);
                double priceByCarton = Double.parseDouble(priceByCartonText);
                int stockQuantity = Integer.parseInt(stockQuantityText);

                if (salePrice <= originalPrice) {
                    JOptionPane.showMessageDialog(this, "Sale price must be greater than original price!", "Error",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (priceByCarton <= priceByUnit) {
                    JOptionPane.showMessageDialog(this, "Price by carton must be greater than price by unit!", "Error",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (priceByUnit > salePrice) {
                    JOptionPane.showMessageDialog(this, "Price by unit must be equal to or less than sale price!",
                            "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Assume product code will be received from backend
                String productCode = receiveProductCodeFromBackend();

                // Implement save logic here
                JOptionPane.showMessageDialog(this, "Product Added Successfully!\nProduct Code: " + productCode);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Please enter valid numeric values for prices and stock quantity!",
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
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

    private void styleComboBox(JComboBox<String> comboBox) {
        // Apply consistent font and background
        comboBox.setFont(new Font("Arial", Font.PLAIN, 14));
        comboBox.setBackground(Color.WHITE);
        comboBox.setForeground(Color.BLACK);
        comboBox.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 1));
        comboBox.setPreferredSize(new Dimension(200, 30));

        // Set selection background and foreground colors
        UIManager.put("ComboBox.selectionBackground", new Color(251, 124, 124)); // Light blue highlight
        UIManager.put("ComboBox.selectionForeground", Color.BLACK); // Black text on highlight
        SwingUtilities.updateComponentTreeUI(comboBox);
    }

    private String receiveProductCodeFromBackend() {
        // Placeholder for backend call to receive product code
        return "PM-0001"; // Example code, replace with actual backend call
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new AddProductPage().setVisible(true);
        });
    }

}