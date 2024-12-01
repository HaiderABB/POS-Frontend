package SCD.ui.BranchManager;

import SCD.ui.Common.ButtonFactory;
import SCD.ui.Common.NavBar;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class InventoryManagementPage extends JFrame {
    private BranchSidebar sidebar;
    private DefaultTableModel tableModel;
    private NavBar navBar;

    public InventoryManagementPage() {
        setTitle("Inventory Management");
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        sidebar = new BranchSidebar();
        add(sidebar, BorderLayout.WEST);

        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));

        navBar = new NavBar();
        contentPanel.add(navBar, BorderLayout.NORTH);
        navBar.setTitle("Inventory Management");

        String[] columnNames = {"Item ID", "Item Name", "Quantity", "Price", "Category"};
        tableModel = new DefaultTableModel(columnNames, 0);
        JTable inventoryTable = new JTable(tableModel);
        JScrollPane tableScrollPane = new JScrollPane(inventoryTable);
        contentPanel.add(tableScrollPane, BorderLayout.CENTER);

        JPanel formPanel = new JPanel(new GridLayout(6, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel idLabel = new JLabel("Item ID:");
        JTextField idField = new JTextField();
        JLabel nameLabel = new JLabel("Item Name:");
        JTextField nameField = new JTextField();
        JLabel quantityLabel = new JLabel("Quantity:");
        JTextField quantityField = new JTextField();
        JLabel priceLabel = new JLabel("Price:");
        JTextField priceField = new JTextField();
        JLabel categoryLabel = new JLabel("Category:");
        JTextField categoryField = new JTextField();

        formPanel.add(idLabel);
        formPanel.add(idField);
        formPanel.add(nameLabel);
        formPanel.add(nameField);
        formPanel.add(quantityLabel);
        formPanel.add(quantityField);
        formPanel.add(priceLabel);
        formPanel.add(priceField);
        formPanel.add(categoryLabel);
        formPanel.add(categoryField);

        JPanel buttonPanel = new JPanel(new GridLayout(1, 3, 10, 10));
        JButton addButton = ButtonFactory.createStyledButton("Add");
        JButton updateButton = ButtonFactory.createStyledButton("Update");
        JButton deleteButton = ButtonFactory.createStyledButton("Delete");
        buttonPanel.add(addButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);

        contentPanel.add(formPanel, BorderLayout.SOUTH);
        contentPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(contentPanel, BorderLayout.CENTER);
        setLocationRelativeTo(null);

        addButton.addActionListener(e -> {
            String id = idField.getText().trim();
            String name = nameField.getText().trim();
            String quantity = quantityField.getText().trim();
            String price = priceField.getText().trim();
            String category = categoryField.getText().trim();

            if (validateInputs(id, name, quantity, price, category)) {
                tableModel.addRow(new Object[]{id, name, quantity, price, category});
                clearFields(idField, nameField, quantityField, priceField, categoryField);
            }
        });

        updateButton.addActionListener(e -> {
            int selectedRow = inventoryTable.getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(this, "Please select an item to update!", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                String id = idField.getText().trim();
                String name = nameField.getText().trim();
                String quantity = quantityField.getText().trim();
                String price = priceField.getText().trim();
                String category = categoryField.getText().trim();

                if (validateInputs(id, name, quantity, price, category)) {
                    tableModel.setValueAt(id, selectedRow, 0);
                    tableModel.setValueAt(name, selectedRow, 1);
                    tableModel.setValueAt(quantity, selectedRow, 2);
                    tableModel.setValueAt(price, selectedRow, 3);
                    tableModel.setValueAt(category, selectedRow, 4);
                    clearFields(idField, nameField, quantityField, priceField, categoryField);
                }
            }
        });

        deleteButton.addActionListener(e -> {
            int selectedRow = inventoryTable.getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(this, "Please select an item to delete!", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                tableModel.removeRow(selectedRow);
                clearFields(idField, nameField, quantityField, priceField, categoryField);
            }
        });
    }

    private boolean validateInputs(String id, String name, String quantity, String price, String category) {
        if (id.isEmpty() || name.isEmpty() || quantity.isEmpty() || price.isEmpty() || category.isEmpty()) {
            JOptionPane.showMessageDialog(this, "All fields must be filled!", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (!quantity.matches("\\d+")) {
            JOptionPane.showMessageDialog(this, "Quantity must be a valid number!", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (!price.matches("\\d+(\\.\\d{1,2})?")) {
            JOptionPane.showMessageDialog(this, "Price must be a valid decimal number!", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }

    private void clearFields(JTextField idField, JTextField nameField, JTextField quantityField, JTextField priceField, JTextField categoryField) {
        idField.setText("");
        nameField.setText("");
        quantityField.setText("");
        priceField.setText("");
        categoryField.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            InventoryManagementPage frame = new InventoryManagementPage();
            frame.setVisible(true);
        });
    }
}
