package SCD.ui.DataEntryOperator.Product;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import SCD.controllers.DataEntryOperatorControllers.DEOSidebarController;
import SCD.model.models.Product;
import SCD.ui.Common.NavBar;
import SCD.ui.Common.Props;

public class ViewProductsByVendorPage extends JFrame implements Props {

    private JTextField vendorCodeField;
    private JButton searchButton;
    private DefaultTableModel tableModel;
    private JTable productTable;

    public ViewProductsByVendorPage() {
        setTitle("View Products by Vendor");
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        DEOSidebarController sidebarController = new DEOSidebarController(this);
        JPanel sidebar = sidebarController.getView();
        add(sidebar, BorderLayout.WEST);

        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(contentPanel, BorderLayout.CENTER);

        NavBar navBar = new NavBar();
        navBar.setTitle("View Products by Vendor");
        contentPanel.add(navBar, BorderLayout.NORTH);

        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        JLabel vendorCodeLabel = new JLabel("Vendor Code (VM-XXXX):");
        vendorCodeField = new JTextField(15);
        searchButton = new JButton("Search");
        searchPanel.add(vendorCodeLabel);
        searchPanel.add(vendorCodeField);
        searchPanel.add(searchButton);
        contentPanel.add(searchPanel, BorderLayout.NORTH);

        JPanel tablePanel = new JPanel(new BorderLayout());
        String[] columnNames = { "Product Code", "Name", "Category", "Original Price", "Sale Price", "Price by Unit",
                "Price by Carton", "Stock Quantity" };
        tableModel = new DefaultTableModel(columnNames, 0);
        productTable = new JTable(tableModel);
        productTable.setFillsViewportHeight(true);

        JScrollPane scrollPane = new JScrollPane(productTable);
        tablePanel.add(scrollPane, BorderLayout.CENTER);
        contentPanel.add(tablePanel, BorderLayout.CENTER);

        setLocationRelativeTo(null);
    }

    public JTextField getVendorCodeField() {
        return vendorCodeField;
    }

    public JButton getSearchButton() {
        return searchButton;
    }

    public DefaultTableModel getTableModel() {
        return tableModel;
    }

    public void populateTable(List<Product> productData) {
        tableModel.setRowCount(0);

        for (Product product : productData) {
            tableModel.addRow(new Object[] {
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
}
