package SCD.ui.Cashier;

import SCD.model.models.SaleItem;
import SCD.model.models.Product;
import SCD.model.models.Vendor;
import SCD.ui.Common.ButtonFactory;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class SalesPage extends JFrame {

    private JTable salesTable;
    private DefaultTableModel tableModel;
    private JLabel totalBillLabel;

    private List<SaleItem> saleItems = new ArrayList<>();
    private double totalBill = 0.0;

    public SalesPage() {
        setTitle("Sales Page");
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        // Reuse DEOSidebar for consistency
        JPanel sidebar = new cashierSidebar();
        add(sidebar, BorderLayout.WEST);

        // Header with title
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(255, 255, 255)); // Consistent header background
        JLabel titleLabel = new JLabel("Sales Page");
        titleLabel.setForeground(Color.black);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        headerPanel.add(titleLabel);
        add(headerPanel, BorderLayout.NORTH);

        // Main content area
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BorderLayout());
        add(contentPanel, BorderLayout.CENTER);

        JPanel inputPanel = createInputPanel();
        contentPanel.add(inputPanel, BorderLayout.NORTH);

        JScrollPane tableScrollPane = createSalesTable();
        contentPanel.add(tableScrollPane, BorderLayout.CENTER);

        JPanel totalBillPanel = createTotalBillPanel();
        contentPanel.add(totalBillPanel, BorderLayout.SOUTH);

        // Footer with action buttons
        JPanel footerPanel = new JPanel();
        footerPanel.setBackground(new Color(255, 255, 255)); // Consistent footer background
        footerPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));

        JButton proceedButton = ButtonFactory.createStyledButton("Proceed to Payment");
        proceedButton.addActionListener(e -> openPaymentsPage());

        JButton cancelButton = ButtonFactory.createStyledButton("Cancel");
        cancelButton.addActionListener(e -> dispose());

        footerPanel.add(proceedButton);
        footerPanel.add(cancelButton);

        add(footerPanel, BorderLayout.SOUTH);

        setLocationRelativeTo(null); // Center the window on the screen
    }

    private JPanel createInputPanel() {
        JPanel inputPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        inputPanel.setBorder(BorderFactory.createTitledBorder("Add/Remove Product"));
        inputPanel.setBackground(Color.WHITE);

        JLabel productCodeLabel = new JLabel("Product Code (PM-XXXX):");
        JTextField productCodeField = new JTextField(10);

        JLabel quantityLabel = new JLabel("Quantity:");
        JTextField quantityField = new JTextField(5);

        JButton addButton = ButtonFactory.createStyledButton("Add");

        addButton.addActionListener(e -> {
            String productCode = productCodeField.getText().trim();
            String quantityText = quantityField.getText().trim();

            if (validateProductCode(productCode) && validateQuantity(quantityText)) {
                int quantity = Integer.parseInt(quantityText);
                addSaleItem(productCode, quantity);
                productCodeField.setText("");
                quantityField.setText("");
            } else {
                JOptionPane.showMessageDialog(this, "Invalid input! Ensure correct product code and quantity.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        JButton removeButton = ButtonFactory.createStyledButton("Remove");


        removeButton.addActionListener(e -> {
            String productCode = productCodeField.getText().trim();
            String quantityText = quantityField.getText().trim();

            if (validateProductCode(productCode) && validateQuantity(quantityText)) {
                int quantity = Integer.parseInt(quantityText);
                removeSaleItem(productCode, quantity);
                productCodeField.setText("");
                quantityField.setText("");
            } else {
                JOptionPane.showMessageDialog(this, "Invalid input! Ensure correct product code and quantity.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        inputPanel.add(productCodeLabel);
        inputPanel.add(productCodeField);
        inputPanel.add(quantityLabel);
        inputPanel.add(quantityField);
        inputPanel.add(addButton);
        inputPanel.add(removeButton);

        return inputPanel;
    }

    private JScrollPane createSalesTable() {
        tableModel = new DefaultTableModel(new Object[]{"Product Code", "Quantity", "Sale Price", "Total"}, 0);
        salesTable = new JTable(tableModel);
        return new JScrollPane(salesTable);
    }

    private JPanel createTotalBillPanel() {
        JPanel totalBillPanel = new JPanel(new BorderLayout());
        totalBillPanel.setBorder(BorderFactory.createTitledBorder("Total Bill"));
        totalBillPanel.setBackground(Color.WHITE);

        totalBillLabel = new JLabel("Total: $" + String.format("%.2f", totalBill));
        totalBillLabel.setFont(new Font("Arial", Font.BOLD, 16));
        totalBillLabel.setHorizontalAlignment(SwingConstants.RIGHT);

        totalBillPanel.add(totalBillLabel, BorderLayout.CENTER);
        return totalBillPanel;
    }

    private boolean validateProductCode(String productCode) {
        return Pattern.matches("PM-\\d{4}", productCode);
    }

    private boolean validateQuantity(String quantityText) {
        try {
            int quantity = Integer.parseInt(quantityText);
            return quantity > 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private void addSaleItem(String productCode, int quantity) {
        // Assuming a method to get product by code
        Product product = getProductByCode(productCode);
        if (product != null) {
            if (quantity <= product.getStockQuantity()) {
                boolean itemExists = false;
                for (SaleItem item : saleItems) {
                    if (item.getProduct().getProductCode().equals(productCode)) {
                        if (item.getQuantity() + quantity <= product.getStockQuantity()) {
                            item.setQuantity(item.getQuantity() + quantity);
                            item.setTotalPrice(item.getSalePrice() * item.getQuantity());
                            itemExists = true;
                            break;
                        } else {
                            JOptionPane.showMessageDialog(this, "Insufficient quantity in stock!", "Error", JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                    }
                }
                if (!itemExists) {
                    SaleItem saleItem = new SaleItem(product, quantity, product.getSalePrice(), product.getOriginalPrice());
                    saleItems.add(saleItem);
                }
                updateTable();
                calculateTotalBill();
                totalBillLabel.setText("Total: $" + String.format("%.2f", totalBill));
            } else {
                JOptionPane.showMessageDialog(this, "Insufficient quantity in stock!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Product not found!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void removeSaleItem(String productCode, int quantity) {
        for (SaleItem item : saleItems) {
            if (item.getProduct().getProductCode().equals(productCode)) {
                if (item.getQuantity() > quantity) {
                    item.setQuantity(item.getQuantity() - quantity);
                    item.setTotalPrice(item.getSalePrice() * item.getQuantity());
                } else {
                    saleItems.remove(item);
                }
                updateTable();
                calculateTotalBill();
                totalBillLabel.setText("Total: $" + String.format("%.2f", totalBill));
                return;
            }
        }
        JOptionPane.showMessageDialog(this, "Product not found in the list!", "Error", JOptionPane.ERROR_MESSAGE);
    }

    private void updateTable() {
        tableModel.setRowCount(0);
        for (SaleItem item : saleItems) {
            tableModel.addRow(new Object[]{item.getProduct().getProductCode(), item.getQuantity(), item.getSalePrice(), item.getTotalPrice()});
        }
    }

    private void calculateTotalBill() {
        totalBill = saleItems.stream().mapToDouble(SaleItem::getTotalPrice).sum();
    }

    private Product getProductByCode(String productCode) {
        // Mock implementation, replace with actual product retrieval logic
        Vendor vendor = new Vendor();
        vendor.setVendorCode("V001");
        vendor.setName("Sample Vendor");
        vendor.setPhoneNumber("123456789");
        vendor.setAddress("123 Market Street");

        switch (productCode) {
            case "PM-0001":
                return new Product("PM-0001", vendor, "Product A", "Category 1", 100.0, 120.0, 10.0, 90.0, 50);
            case "PM-0002":
                return new Product("PM-0002", vendor, "Product B", "Category 2", 200.0, 220.0, 20.0, 180.0, 30);
            case "PM-0003":
                return new Product("PM-0003", vendor, "Product C", "Category 3", 50.0, 60.0, 5.0, 45.0, 100);
            case "PM-0004":
                return new Product("PM-0004", vendor, "Product D", "Category 4", 150.0, 170.0, 15.0, 140.0, 40);
            case "PM-0005":
                return new Product("PM-0005", vendor, "Product E", "Category 5", 75.0, 85.0, 7.5, 70.0, 60);
            case "PM-0006":
                return new Product("PM-0006", vendor, "Product F", "Category 6", 120.0, 130.0, 12.0, 110.0, 45);
            case "PM-0007":
                return new Product("PM-0007", vendor, "Product G", "Category 7", 90.0, 100.0, 9.0, 80.0, 80);
            default:
                return null;
        }
    }

    private void openPaymentsPage() {
        SwingUtilities.invokeLater(() -> {
            PaymentsPage paymentsPage = new PaymentsPage(saleItems, totalBill);
            paymentsPage.setVisible(true);
            dispose();
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            SalesPage salesPage = new SalesPage();
            salesPage.setVisible(true);
        });
    }
}