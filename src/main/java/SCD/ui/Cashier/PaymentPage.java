//package SCD.ui.Cashier;
//
//import SCD.model.models.SaleItem;
//import SCD.ui.Common.ButtonFactory;
//
//import javax.swing.*;
//import javax.swing.table.DefaultTableModel;
//import java.awt.*;
//import java.util.List;
//
//public class PaymentPage extends JFrame {
//
//    private List<SaleItem> saleItems;
//    private double totalBill;
//
//    public PaymentPage(List<SaleItem> saleItems, double totalBill) {
//        this.saleItems = saleItems;
//        this.totalBill = totalBill;
//
//        setTitle("Payment Page");
//        setSize(1000, 700);
//        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//        setLayout(new BorderLayout());
//
//        // Main content area
//        JPanel mainPanel = new JPanel(new BorderLayout());
//        mainPanel.setBackground(Color.WHITE);
//
//        String[] columnNames = {"Product Code", "Quantity", "Sale Price", "Total Price"};
//        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
//        JTable salesTable = new JTable(tableModel);
//        JScrollPane tableScrollPane = new JScrollPane(salesTable);
//        mainPanel.add(tableScrollPane, BorderLayout.CENTER);
//
//        for (SaleItem saleItem : saleItems) {
//            tableModel.addRow(new Object[]{
//                    saleItem.getProductCode(),
//                    saleItem.getQuantity(),
//                    saleItem.getSalePrice(),
//                    saleItem.getTotalPrice()
//            });
//        }
//
//        // Footer with action buttons
//        JPanel footerPanel = new JPanel();
//        footerPanel.setBackground(new Color(255, 255, 255));
//        footerPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
//
//        JLabel totalBillLabel = new JLabel("Total Bill: $" + totalBill);
//        totalBillLabel.setFont(new Font("Arial", Font.BOLD, 24));
//
//        JButton proceedButton = ButtonFactory.createStyledButton("Complete Payment");
//        proceedButton.addActionListener(e -> {
//            new SummaryPage(saleItems, totalBill).setVisible(true);
//            dispose();
//        });
//
//        footerPanel.add(totalBillLabel);
//        footerPanel.add(proceedButton);
//
//        add(mainPanel, BorderLayout.CENTER);
//        add(footerPanel, BorderLayout.SOUTH);
//
//        setLocationRelativeTo(null);
//        setVisible(true);
//    }
//}