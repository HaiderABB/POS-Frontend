package SCD.ui.Cashier;

import SCD.ui.Common.ButtonFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.List;

public class CashierInterface extends JFrame {

    private JLabel totalLabel;
    private JLabel selectedProductsLabel;
    private int selectedProducts = 0;
    private List<Product> selectedProductList = new ArrayList<>();
    private cashierSidebar sidebar;

    public CashierInterface() {
        setTitle("Cashier Interface");
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        // Add sidebar
        sidebar = new cashierSidebar();
        add(sidebar, BorderLayout.WEST);

        // Header with title
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(255, 255, 255));
        JLabel titleLabel = new JLabel("Cashier Interface");
        titleLabel.setForeground(Color.black);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        headerPanel.add(titleLabel);
        add(headerPanel, BorderLayout.NORTH);

        // Main content area with cards
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(Color.WHITE);

        JPanel cardPanel = new JPanel(new GridLayout(0, 3, 10, 10));
        cardPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        JScrollPane scrollPane = new JScrollPane(cardPanel);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        // Sample data
        for (int i = 1; i <= 100; i++) {
            addProductCard(cardPanel, "Product " + i, "Category " + ((i % 10) + 1), 10.00 + i);
        }

        add(mainPanel, BorderLayout.CENTER);

        // Footer with action buttons
        JPanel footerPanel = new JPanel(new BorderLayout());
        footerPanel.setBackground(new Color(255, 255, 255));
        footerPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));

        totalLabel = new JLabel("Total: $0.00");
        totalLabel.setFont(new Font("Arial", Font.BOLD, 18));
        footerPanel.add(totalLabel, BorderLayout.EAST);

        selectedProductsLabel = new JLabel("Selected Products: 0");
        selectedProductsLabel.setFont(new Font("Arial", Font.BOLD, 18));
        footerPanel.add(selectedProductsLabel, BorderLayout.WEST);

        JButton generateBillButton = ButtonFactory.createStyledButton("Generate Bill");
        generateBillButton.setSize(100, 50);
        generateBillButton.addActionListener(e -> generateBill());
        footerPanel.add(generateBillButton, BorderLayout.CENTER);

        add(footerPanel, BorderLayout.SOUTH);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void addProductCard(JPanel cardPanel, String productName, String category, double price) {
        JPanel card = new JPanel();
        card.setLayout(new BorderLayout());
        card.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
        card.setBackground(Color.WHITE);

        JLabel nameLabel = new JLabel(productName);
        nameLabel.setFont(new Font("Arial", Font.BOLD, 16));
        nameLabel.setHorizontalAlignment(SwingConstants.CENTER);
        card.add(nameLabel, BorderLayout.NORTH);

        JLabel categoryLabel = new JLabel("Category: " + category);
        categoryLabel.setHorizontalAlignment(SwingConstants.CENTER);
        card.add(categoryLabel, BorderLayout.CENTER);

        JLabel priceLabel = new JLabel("Price: $" + price);
        priceLabel.setHorizontalAlignment(SwingConstants.CENTER);
        card.add(priceLabel, BorderLayout.SOUTH);

        JPanel buttonPanel = new JPanel();
        JLabel quantityLabel = new JLabel("Quantity: 0");
        quantityLabel.setFont(new Font("Arial", Font.BOLD, 16));
        buttonPanel.add(quantityLabel);

        JButton addButton = ButtonFactory.createStyledButton("+");
        addButton.addActionListener(e -> {
            int quantity = Integer.parseInt(quantityLabel.getText().replace("Quantity: ", ""));
            quantity++;
            quantityLabel.setText("Quantity: " + quantity);
            updateTotal(price, true, productName, category, price, quantity);
        });

        JButton removeButton = ButtonFactory.createStyledButton("-");
        removeButton.addActionListener(e -> {
            int quantity = Integer.parseInt(quantityLabel.getText().replace("Quantity: ", ""));
            if (quantity > 0) {
                quantity--;
                quantityLabel.setText("Quantity: " + quantity);
                updateTotal(-price, false, productName, category, price, quantity);
            }
        });

        buttonPanel.add(addButton);
        buttonPanel.add(removeButton);
        card.add(buttonPanel, BorderLayout.SOUTH);

        JCheckBox selectBox = new JCheckBox();
        selectBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    selectedProducts++;
                    selectedProductList.add(new Product(productName, category, price, Integer.parseInt(quantityLabel.getText().replace("Quantity: ", ""))));
                } else {
                    selectedProducts--;
                    selectedProductList.removeIf(product -> product.getName().equals(productName));
                }
                selectedProductsLabel.setText("Selected Products: " + selectedProducts);
            }
        });
        card.add(selectBox, BorderLayout.WEST);

        cardPanel.add(card);
    }


    private void updateTotal(double amount, boolean isAdd, String productName, String category, double price, int quantity) {
        String totalText = totalLabel.getText().replace("Total: $", "");
        double total = Double.parseDouble(totalText);
        total += amount;
        totalLabel.setText(String.format("Total: $%.2f", total));
    }

    private void generateBill() {
        double total = Double.parseDouble(totalLabel.getText().replace("Total: $", ""));
        new BillPage(selectedProductList, total);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new CashierInterface().setVisible(true);
        });
    }

    class Product {
        private String name;
        private String category;
        private double price;
        private int quantity;

        public Product(String name, String category, double price, int quantity) {
            this.name = name;
            this.category = category;
            this.price = price;
            this.quantity = quantity;
        }

        public String getName() {
            return name;
        }

        public String getCategory() {
            return category;
        }

        public double getPrice() {
            return price;
        }

        public int getQuantity() {
            return quantity;
        }
    }
}