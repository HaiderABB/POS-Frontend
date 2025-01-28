package SCD.ui.DataEntryOperator.Product;

import SCD.controllers.DataEntryOperatorControllers.DEOSidebarController;
import SCD.ui.Common.NavBar;
import SCD.ui.Common.Props;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class ViewProductsPage extends JFrame implements Props {

    private DefaultTableModel tableModel;
    private JTable productTable;

    public ViewProductsPage() {
        setTitle("View Products");
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        // scd- proj initSidebar
        DEOSidebarController sidebarController = new DEOSidebarController(this);
        JPanel sidebar = sidebarController.getView();
        add(sidebar, BorderLayout.WEST);

        // scd- proj initContent Panel
        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(contentPanel, BorderLayout.CENTER);

        // scd- proj initNavBar
        NavBar navBar = new NavBar();
        navBar.setTitle("View Products");
        contentPanel.add(navBar, BorderLayout.NORTH);

        // scd- proj initTable Panel
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

    public DefaultTableModel getTableModel() {
        return tableModel;
    }

    public JTable getProductTable() {
        return productTable;
    }

    public void populateTable(List<Object[]> productData) {
        tableModel.setRowCount(0);
        for (Object[] row : productData) {
            tableModel.addRow(row);
        }
    }
}
