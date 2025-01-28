package SCD.ui.Cashier;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import SCD.controllers.CashierControllers.cashierSidebarController;
import SCD.model.models.Product;
import SCD.model.models.SaleItem;
import SCD.ui.Common.ButtonFactory;
import SCD.ui.Common.Props;

public class SalesPage extends JFrame implements Props {

    private JTextField productCodeField;
    private JTextField quantityField;
    private JTextField searchField;
    private JButton addButton;
    private JButton removeButton;
    private JButton searchButton;
    private JButton proceedButton;
    private JButton cancelButton;
    private JTable salesTable;
    private JTable productTable;
    private JLabel totalBillLabel;
    private DefaultTableModel salesTableModel;
    private DefaultTableModel productTableModel;
    public cashierSidebarController sidebarController;

    public SalesPage() {
        setTitle("Sales Page");
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        sidebarController = new cashierSidebarController(this);
        JPanel sidebar = sidebarController.getView();
        add(sidebar, BorderLayout.WEST);

        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.setBorder(BorderFactory.createEmptyBorder(30, 20, 20, 20));
        add(contentPanel, BorderLayout.CENTER);

        JPanel inputPanel = createInputPanel();
        contentPanel.add(inputPanel, BorderLayout.NORTH);

        JSplitPane tablesPanel = createTablesPanel();
        contentPanel.add(tablesPanel, BorderLayout.CENTER);

        JPanel footerPanel = new JPanel(new BorderLayout());
        footerPanel.add(createTotalBillPanel(), BorderLayout.NORTH);
        footerPanel.add(createFooterPanel(), BorderLayout.SOUTH);

        contentPanel.add(footerPanel, BorderLayout.SOUTH);

        setLocationRelativeTo(null);
    }

    private JPanel createInputPanel() {
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.Y_AXIS));
        inputPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Props.tableBg),
                "Add/Remove Product"));

        JPanel firstRow = new JPanel(new FlowLayout(FlowLayout.CENTER, 8, 5));
        JLabel productCodeLabel = new JLabel("Product Code (PM-XXXX):");
        productCodeLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        productCodeField = new JTextField(15);

        JLabel quantityLabel = new JLabel("Quantity:");
        quantityLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        quantityField = new JTextField(8);

        addButton = ButtonFactory.createStyledButton("Add");
        removeButton = ButtonFactory.createStyledButton("Remove");

        firstRow.add(productCodeLabel);
        firstRow.add(productCodeField);
        firstRow.add(quantityLabel);
        firstRow.add(quantityField);
        firstRow.add(addButton);
        firstRow.add(removeButton);

        JPanel secondRow = new JPanel(new FlowLayout(FlowLayout.CENTER, 8, 5));
        JLabel searchLabel = new JLabel("Search Product:");
        searchLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        searchField = new JTextField(20);
        searchButton = ButtonFactory.createStyledButton("Search");

        secondRow.add(searchLabel);
        secondRow.add(searchField);
        secondRow.add(searchButton);

        inputPanel.add(firstRow);
        inputPanel.add(secondRow);

        return inputPanel;
    }

    private JSplitPane createTablesPanel() {
        salesTableModel = new DefaultTableModel(new Object[] { "Product Code", "Quantity", "Sale Price", "Total" }, 0);
        salesTable = createStyledTable(salesTableModel);

        JScrollPane salesScrollPane = new JScrollPane(salesTable);

        productTableModel = new DefaultTableModel(new Object[] { "Product Code", "Name", "Category", "Price", "Stock" },
                0);
        productTable = createStyledTable(productTableModel);

        JScrollPane productScrollPane = new JScrollPane(productTable);

        JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, salesScrollPane, productScrollPane);
        splitPane.setDividerLocation(250);
        splitPane.setResizeWeight(0.5);

        return splitPane;
    }

    private JTable createStyledTable(DefaultTableModel model) {
        JTable table = new JTable(model);
        table.setRowHeight(30);
        table.getTableHeader().setBackground(Props.tableBg);
        table.getTableHeader().setForeground(Color.BLACK);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        return table;
    }

    private JPanel createTotalBillPanel() {
        JPanel totalBillPanel = new JPanel(new BorderLayout());
        totalBillPanel.setBorder(
                BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Props.tableBg), "Total Bill"));

        totalBillLabel = new JLabel("Total: $0.00");
        totalBillLabel.setFont(new Font("Arial", Font.BOLD, 16));
        totalBillLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        totalBillLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        totalBillPanel.add(totalBillLabel, BorderLayout.CENTER);
        return totalBillPanel;
    }

    private JPanel createFooterPanel() {
        JPanel footerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        proceedButton = ButtonFactory.createStyledButton("Proceed to Payment");
        cancelButton = ButtonFactory.createStyledButton("Cancel");

        footerPanel.add(proceedButton);
        footerPanel.add(cancelButton);

        return footerPanel;
    }

    public JTextField getProductCodeField() {
        return productCodeField;
    }

    public JTextField getQuantityField() {
        return quantityField;
    }

    public JTextField getSearchField() {
        return searchField;
    }

    public JButton getAddButton() {
        return addButton;
    }

    public JButton getRemoveButton() {
        return removeButton;
    }

    public JButton getSearchButton() {
        return searchButton;
    }

    public JButton getProceedButton() {
        return proceedButton;
    }

    public JButton getCancelButton() {
        return cancelButton;
    }

    public void updateSalesTable(List<SaleItem> saleItems) {
        salesTableModel.setRowCount(0);
        for (SaleItem item : saleItems) {
            salesTableModel.addRow(new Object[] { item.getProduct().getProductCode(), item.getQuantity(),
                    item.getSalePrice(), item.getTotalPrice() });
        }
    }

    public void populateProductTable(List<Product> products) {
        productTableModel.setRowCount(0);
        for (Product product : products) {
            productTableModel.addRow(new Object[] { product.getProductCode(), product.getName(), product.getCategory(),
                    product.getSalePrice(), product.getStockQuantity() });
        }
    }

    public void updateTotalBill(double totalBill) {
        totalBillLabel.setText("Total: $" + String.format("%.2f", totalBill));
    }

    public void clearInputFields() {
        productCodeField.setText("");
        quantityField.setText("");
    }

    public boolean validateProductCode(String productCode) {

        return productCode.matches("PM-\\d{4}");
    }

    public boolean validateQuantity(String quantityText) {
        try {
            int quantity = Integer.parseInt(quantityText);
            return quantity > 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
