//package SCD.ui.Cashier;
//
//import SCD.model.models.SaleItem;
//import SCD.ui.Common.ButtonFactory;
//
//import javax.swing.*;
//import javax.swing.table.DefaultTableModel;
//import java.awt.*;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.regex.Pattern;
//
//public class SalesPage extends JFrame {
//
//    private DefaultTableModel tableModel;
//    private List<SaleItem> saleItems;
//    private JLabel totalBillLabel;
//    private double totalBill;
//
//    public SalesPage() {
//        saleItems = new ArrayList<>();
//        totalBill = 0.0;
//
//        setTitle("Sales Page");
//        setSize(1000, 700);
//        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//        setLayout(new BorderLayout());
//
//        // Main content area
//        JPanel mainPanel = new JPanel(new BorderLayout());
//        mainPanel.setBackground(Color.WHITE);
//
//        String[] columnNames = {"Product Code", "Quantity", "Sale Price", "Total Price"};
//        tableModel = new DefaultTableModel(columnNames, 0);
//        JTable salesTable = new JTable(tableModel);
//        JScrollPane tableScrollPane = new JScrollPane(salesTable);
//        mainPanel.add(tableScrollPane, BorderLayout.CENTER);
//
//        // Input area
//        JPanel inputPanel = new JPanel(new GridLayout(2, 2, 10, 10));
//        inputPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
//        inputPanel.setBackground(Color.WHITE);
//
//        JLabel productCodeLabel = new JLabel("Product Code (PM-XXXX):");
//        JTextField productCodeField = new JTextField();
//        JLabel quantityLabel = new JLabel("Quantity:");
//        JTextField quantityField = new JTextField();
//
//        inputPanel.add(productCodeLabel);
//        inputPanel.add(productCodeField);
//        inputPanel.add(quantityLabel);
//        inputPanel.add(quantityField);
//
//        mainPanel.add(inputPanel, BorderLayout.NORTH);
//
//        // Footer with action buttons
//        JPanel footerPanel = new JPanel();
//        footerPanel.setBackground(new Color(255, 255, 255));
//        footerPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
//
//        JButton addButton = ButtonFactory.createStyledButton("Add");
//        addButton.addActionListener(e -> {
//            String productCode = productCodeField.getText().trim();
//            String quantityText = quantityField.getText().trim();
//
//            if (productCode.isEmpty() || quantityText.isEmpty()) {
//                JOptionPane.showMessageDialog(this, "Please fill out all fields!", "Error", JOptionPane.ERROR_MESSAGE);
//                return;
//            }
//
//            int quantity = Integer.parseInt(quantityText);
//
//            if (!validateProductCode(productCode) || !checkProductExists(productCode) || !checkProductQuantity(productCode, quantity)) {
//                return;
//            }
//
//            double salePrice = getProductSalePrice(productCode);
//            double totalPrice = salePrice * quantity;
//
//            SaleItem saleItem = new SaleItem();
//            saleItem.setProductCode(productCode);
//            saleItem.setQuantity(quantity);
//            saleItem.setSalePrice(salePrice);
//            saleItem.setTotalPrice(totalPrice);
//
//            saleItems.add(saleItem);
//            tableModel.addRow(new Object[]{productCode, quantity, salePrice, totalPrice});
//
//            totalBill += totalPrice;
//            totalBillLabel.setText("Total Bill: $" + totalBill);
//        });
//
//        JButton removeButton = ButtonFactory.createStyledButton("Remove");
//        removeButton.addActionListener(e -> {
//            new RemoveItemPage(saleItems, totalBill, tableModel, totalBillLabel).setVisible(true);
//        });
//
//        JButton proceedButton = ButtonFactory.createStyledButton("Proceed to Payment");
//        proceedButton.addActionListener(e -> {
//            new PaymentPage(saleItems, totalBill).setVisible(true);
//            dispose();
//        });
//
//        totalBillLabel = new JLabel("Total Bill: $0.0");
//        totalBillLabel.setFont(new Font("Arial", Font.BOLD, 24));
//
//        footerPanel.add(totalBillLabel);
//        footerPanel.add(addButton);
//        footerPanel.add(removeButton);
//        footerPanel.add(proceedButton);
//
//        add(mainPanel, BorderLayout.CENTER);
//        add(footerPanel, BorderLayout.SOUTH);
//
//        setLocationRelativeTo(null);
//        setVisible(true);
//    }
//
//    private boolean validateProductCode(String productCode) {
//        if (!Pattern.matches("PM-\\d{4}", productCode)) {
//            JOptionPane.showMessageDialog(this, "Product Code must follow the format 'PM-XXXX'.", "Error", JOptionPane.ERROR_MESSAGE);
//            return false;
//        }
//        return true;
//    }
//
//    private boolean checkProductExists(String productCode) {
//        // Placeholder for database check
//        return true; // Assume product exists
//    }
//
//    private boolean checkProductQuantity(String productCode, int quantity) {
//        // Placeholder for database check
//        return true; // Assume sufficient quantity
//    }
//
//    private double getProductSalePrice(String productCode) {
//        // Placeholder for database call to get sale price
//        return 10.0; // Example sale price
//    }
//
//    public static void main(String[] args) {
//        SwingUtilities.invokeLater(() -> {
//            new SalesPage().setVisible(true);
//        });
//    }
//}