package SCD.ui.DataEntryOperator;

import SCD.model.models.Product;
import SCD.model.models.Vendor;
import SCD.ui.Common.ButtonFactory;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ViewProductsPage extends JFrame {

    private DefaultTableModel tableModel;

    public ViewProductsPage() {
        setTitle("View Products");
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        // Reuse DEOSidebar for consistency
        JPanel sidebar = new DEOSidebar();
        add(sidebar, BorderLayout.WEST);

        // Header with title
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(255, 255, 255)); // Consistent header background
        JLabel titleLabel = new JLabel("Products List");
        titleLabel.setForeground(Color.black);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        headerPanel.add(titleLabel);
        add(headerPanel, BorderLayout.NORTH);

        // Main content area
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(Color.WHITE);

        String[] columnNames = {"Product Code", "Name", "Category", "Original Price", "Sale Price", "Price by Unit", "Price by Carton", "Stock Quantity"};
        tableModel = new DefaultTableModel(columnNames, 0);
        JTable productTable = new JTable(tableModel);
        JScrollPane tableScrollPane = new JScrollPane(productTable);
        mainPanel.add(tableScrollPane, BorderLayout.CENTER);

        add(mainPanel, BorderLayout.CENTER);

        // Footer with action buttons
        JPanel footerPanel = new JPanel();
        footerPanel.setBackground(new Color(255, 255, 255)); // Consistent footer background
        footerPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));

        JButton closeButton = ButtonFactory.createStyledButton("Close");
        closeButton.addActionListener(e -> new DataEntryOperatorDashboard());
        closeButton.addActionListener(e -> dispose());

        footerPanel.add(closeButton);

        add(footerPanel, BorderLayout.SOUTH);

        setLocationRelativeTo(null); // Center the window on the screen
        setVisible(true);

        // Populate the table with products
        List<Product> products = createProductList();
        populateTable(products);
    }

    private List<Product> createProductList() {
        List<Product> products = new ArrayList<>();
        Vendor vendor = new Vendor();
        vendor.setVendorCode("V001");
        vendor.setName("Sample Vendor");
        vendor.setPhoneNumber("123456789");
        vendor.setAddress("123 Market Street");

        products.add(new Product("PM-0001", vendor, "Product A", "Category 1", 100.0, 120.0, 10.0, 90.0, 50));
        products.add(new Product("PM-0002", vendor, "Product B", "Category 2", 200.0, 220.0, 20.0, 180.0, 30));
        products.add(new Product("PM-0003", vendor, "Product C", "Category 3", 50.0, 60.0, 5.0, 45.0, 100));
        products.add(new Product("PM-0004", vendor, "Product D", "Category 4", 150.0, 170.0, 15.0, 140.0, 40));
        products.add(new Product("PM-0005", vendor, "Product E", "Category 5", 75.0, 85.0, 7.5, 70.0, 60));
        products.add(new Product("PM-0006", vendor, "Product F", "Category 6", 120.0, 130.0, 12.0, 110.0, 45));
        products.add(new Product("PM-0007", vendor, "Product G", "Category 7", 90.0, 100.0, 9.0, 80.0, 80));

        return products;
    }

    public void populateTable(List<Product> products) {
        tableModel.setRowCount(0); // Clear existing data
        for (Product product : products) {
            tableModel.addRow(new Object[]{
                    product.getProductCode(),
                    product.getName(),
                    product.getCategory(),
                    product.getOriginalPrice(),
                    product.getSalePrice(),
                    product.getPriceByUnit(),
                    product.getPriceByCarton(),
                    product.getStockQuantity()
            });
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ViewProductsPage frame = new ViewProductsPage();
            frame.setVisible(true);
        });
    }
}