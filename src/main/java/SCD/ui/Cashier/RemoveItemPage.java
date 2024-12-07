//        package SCD.ui.Cashier;
//
//import SCD.model.models.SaleItem;
//import SCD.ui.Common.ButtonFactory;
//
//import javax.swing.*;
//import javax.swing.table.DefaultTableModel;
//import java.awt.*;
//import java.util.List;
//import java.util.regex.Pattern;
//
//public class RemoveItemPage extends JFrame {
//
//    private List<SaleItem> saleItems;
//    private JLabel totalBillLabel;
//    private double totalBill;
//    private DefaultTableModel tableModel;
//
//    public RemoveItemPage(List<SaleItem> saleItems, double totalBill, DefaultTableModel tableModel, JLabel totalBillLabel) {
//        this.saleItems = saleItems;
//        this.totalBill = totalBill;
//        this.tableModel = tableModel;
//        this.totalBillLabel = totalBillLabel;
//
//        setTitle("Remove Item");
//        setSize(400, 300);
//        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//        setLayout(new BorderLayout());
//
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
//        add(inputPanel, BorderLayout.CENTER);
//
//        JButton removeButton = ButtonFactory.createStyledButton("Remove");
//        removeButton.addActionListener(e -> {
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
//            if (!validateProductCode(productCode)) {
//                return;
//            }
//
//            removeSaleItem(productCode, quantity);
//        });
//
//        JPanel buttonPanel = new JPanel();
//        buttonPanel.setBackground(Color.WHITE);
//        buttonPanel.add(removeButton);
//
//        add(buttonPanel, BorderLayout.SOUTH);
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
//    private void removeSaleItem(String productCode, int quantity) {
//        for (int i = 0; i < saleItems.size(); i++) {
//            SaleItem saleItem = saleItems.get(i);
//            if (saleItem.getProductCode().equals(productCode)) {
//                if (saleItem.getQuantity() >= quantity) {
//                    saleItem.setQuantity(saleItem.getQuantity() - quantity);
//                    double totalPrice = saleItem.getSalePrice() * saleItem.getQuantity();
//                    saleItem.setTotalPrice(totalPrice);
//
//                    totalBill -= saleItem.getSalePrice() * quantity;
//                    totalBillLabel.setText("Total Bill: $" + totalBill);
//
//                    tableModel.setValueAt(saleItem.getQuantity(), i, 1);
//                    tableModel.setValueAt(totalPrice, i, 3);
//
//                    if (saleItem.getQuantity() == 0) {
//                        saleItems.remove(i);
//                        tableModel.removeRow(i);
//                    }
//                    break;
//                } else {
//                    JOptionPane.showMessageDialog(this, "Quantity to remove exceeds current quantity!", "Error", JOptionPane.ERROR_MESSAGE);
//                    return;
//                }
//            }
//        }
//    }
//}