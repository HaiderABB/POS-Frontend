package SCD.ui.DataEntryOperator.Product;

import SCD.controllers.DataEntryOperatorControllers.DEOSidebarController;
import SCD.ui.Common.ButtonFactory;
import SCD.ui.Common.NavBar;
import SCD.ui.Common.Props;

import javax.swing.*;
import java.awt.*;

public class AddProductPage extends JFrame implements Props {
    private JTextField nameField;
    private JComboBox<String> categoryComboBox;
    private JTextField originalPriceField;
    private JTextField salePriceField;
    private JTextField priceByUnitField;
    private JTextField priceByCartonField;
    private JTextField stockQuantityField;
    private JTextField vendorCodeField;
    private JButton saveButton;

    public AddProductPage() {
        setTitle("Add Product");
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        DEOSidebarController sidebar = new DEOSidebarController(this);
        add(sidebar.getView(), BorderLayout.WEST);

        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        NavBar navBar = new NavBar();
        navBar.setTitle("Add Product");
        contentPanel.add(navBar, BorderLayout.NORTH);

        JPanel formPanel = new JPanel(new GridLayout(9, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel nameLabel = new JLabel("Product Name:");
        nameField = new JTextField();

        JLabel categoryLabel = new JLabel("Category:");
        String[] categories = {
                "Electronics", "Home Appliances", "Fashion", "Health & Beauty",
                "Grocery", "Books & Stationery", "Home Decor", "Automotive"
        };
        categoryComboBox = new JComboBox<>(categories);

        JLabel originalPriceLabel = new JLabel("Original Price:");
        originalPriceField = new JTextField();

        JLabel salePriceLabel = new JLabel("Sale Price:");
        salePriceField = new JTextField();

        JLabel priceByUnitLabel = new JLabel("Price per Unit:");
        priceByUnitField = new JTextField();

        JLabel priceByCartonLabel = new JLabel("Price per Carton:");
        priceByCartonField = new JTextField();

        JLabel stockQuantityLabel = new JLabel("Stock Quantity:");
        stockQuantityField = new JTextField();

        JLabel vendorCodeLabel = new JLabel("Vendor Code (VM-XXXX):");
        vendorCodeField = new JTextField();

        formPanel.add(nameLabel);
        formPanel.add(nameField);
        formPanel.add(categoryLabel);
        formPanel.add(categoryComboBox);
        formPanel.add(originalPriceLabel);
        formPanel.add(originalPriceField);
        formPanel.add(salePriceLabel);
        formPanel.add(salePriceField);
        formPanel.add(priceByUnitLabel);
        formPanel.add(priceByUnitField);
        formPanel.add(priceByCartonLabel);
        formPanel.add(priceByCartonField);
        formPanel.add(stockQuantityLabel);
        formPanel.add(stockQuantityField);
        formPanel.add(vendorCodeLabel);
        formPanel.add(vendorCodeField);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        saveButton = ButtonFactory.createStyledButton("Add Product");
        buttonPanel.add(saveButton);

        contentPanel.add(formPanel, BorderLayout.CENTER);
        contentPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(contentPanel, BorderLayout.CENTER);
        setLocationRelativeTo(null);
    }

    public JTextField getNameField() {
        return nameField;
    }

    public JComboBox<String> getCategoryComboBox() {
        return categoryComboBox;
    }

    public JTextField getOriginalPriceField() {
        return originalPriceField;
    }

    public JTextField getSalePriceField() {
        return salePriceField;
    }

    public JTextField getPriceByUnitField() {
        return priceByUnitField;
    }

    public JTextField getPriceByCartonField() {
        return priceByCartonField;
    }

    public JTextField getStockQuantityField() {
        return stockQuantityField;
    }

    public JTextField getVendorCodeField() {
        return vendorCodeField;
    }

    public JButton getSaveButton() {
        return saveButton;
    }
}
