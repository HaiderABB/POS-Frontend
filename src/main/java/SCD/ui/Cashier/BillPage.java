package SCD.ui.Cashier;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class BillPage extends JFrame {

    private cashierSidebar sidebar;

    public BillPage(List<CashierInterface.Product> selectedProductList, double total) {
        setTitle("Bill Summary");
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        // Add sidebar
        sidebar = new cashierSidebar();
        add(sidebar, BorderLayout.WEST);

        // Header with title
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(255, 255, 255));
        JLabel titleLabel = new JLabel("Bill Summary");
        titleLabel.setForeground(Color.black);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        headerPanel.add(titleLabel);
        add(headerPanel, BorderLayout.NORTH);

        // Main content area with table
        String[] columnNames = {"Product Name", "Category", "Price", "Quantity"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
        JTable productTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(productTable);
        add(scrollPane, BorderLayout.CENTER);

        // Add products to table
        for (CashierInterface.Product product : selectedProductList) {
            tableModel.addRow(new Object[]{product.getName(), product.getCategory(), product.getPrice(), product.getQuantity()});
        }

        // Footer with total
        JPanel footerPanel = new JPanel();
        footerPanel.setBackground(new Color(255, 255, 255));
        JLabel totalLabel = new JLabel("Total: $" + total);
        totalLabel.setFont(new Font("Arial", Font.BOLD, 18));
        footerPanel.add(totalLabel);
        add(footerPanel, BorderLayout.SOUTH);

        setLocationRelativeTo(null);
        setVisible(true);
    }
}