package SCD.ui.SuperAdmin;

import SCD.ui.Common.ButtonFactory;
import SCD.ui.Common.NavBar;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class ManageBranchesPage extends JFrame {
    private Sidebar sidebar;
    private DefaultTableModel tableModel;
    private NavBar navBar;

    public ManageBranchesPage() {
        setTitle("Manage Branches");
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        sidebar = new Sidebar();
        add(sidebar, BorderLayout.WEST);

        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));

        navBar = new NavBar();
        contentPanel.add(navBar, BorderLayout.NORTH);
        navBar.setTitle("Manage Branches");

        String[] columnNames = {"Branch ID", "Branch Code", "City", "Phone", "Address", "Active"};
        tableModel = new DefaultTableModel(columnNames, 0);
        JTable branchTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(branchTable);
        contentPanel.add(scrollPane, BorderLayout.CENTER);

        JPanel formPanel = new JPanel(new GridLayout(5, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel codeLabel = new JLabel("Branch Code (BH-1234):");
        JTextField codeField = new JTextField();
        JLabel cityLabel = new JLabel("City:");
        JTextField cityField = new JTextField();
        JLabel phoneLabel = new JLabel("Phone (0321-1234567):");
        JTextField phoneField = new JTextField();
        JLabel addressLabel = new JLabel("Address:");
        JTextField addressField = new JTextField();
        JLabel activeLabel = new JLabel("Active:");
        JCheckBox activeCheckBox = new JCheckBox();

        formPanel.add(codeLabel);
        formPanel.add(codeField);
        formPanel.add(cityLabel);
        formPanel.add(cityField);
        formPanel.add(phoneLabel);
        formPanel.add(phoneField);
        formPanel.add(addressLabel);
        formPanel.add(addressField);
        formPanel.add(activeLabel);
        formPanel.add(activeCheckBox);

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
            String code = codeField.getText().trim();
            String city = cityField.getText().trim();
            String phone = phoneField.getText().trim();
            String address = addressField.getText().trim();
            boolean active = activeCheckBox.isSelected();

            if (validateBranchInputs(code, city, phone)) {
                int newBranchId = tableModel.getRowCount() + 1;
                tableModel.addRow(new Object[]{newBranchId, code, city, phone, address, active});
                clearFields(codeField, cityField, phoneField, addressField, activeCheckBox);
            }
        });

        updateButton.addActionListener(e -> {
            int selectedRow = branchTable.getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(this, "Please select a branch to update!", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                String code = codeField.getText().trim();
                String city = cityField.getText().trim();
                String phone = phoneField.getText().trim();
                String address = addressField.getText().trim();
                boolean active = activeCheckBox.isSelected();

                if (validateBranchInputs(code, city, phone)) {
                    tableModel.setValueAt(code, selectedRow, 1);
                    tableModel.setValueAt(city, selectedRow, 2);
                    tableModel.setValueAt(phone, selectedRow, 3);
                    tableModel.setValueAt(address, selectedRow, 4);
                    tableModel.setValueAt(active, selectedRow, 5);
                    clearFields(codeField, cityField, phoneField, addressField, activeCheckBox);
                }
            }
        });

        deleteButton.addActionListener(e -> {
            int selectedRow = branchTable.getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(this, "Please select a branch to delete!", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                tableModel.removeRow(selectedRow);
                clearFields(codeField, cityField, phoneField, addressField, activeCheckBox);
            }
        });
    }

    private boolean validateBranchInputs(String code, String city, String phone) {
        if (!code.matches("BH-\\d{4}")) {
            JOptionPane.showMessageDialog(this, "Branch Code must follow the format 'BH-1234'", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (!city.matches("[A-Za-z\\s]+")) {
            JOptionPane.showMessageDialog(this, "City must contain only letters", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (!phone.matches("03\\d{2}-\\d{7}")) {
            JOptionPane.showMessageDialog(this, "Phone must follow the format '0321-1234567'", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }

    private void clearFields(JTextField codeField, JTextField cityField, JTextField phoneField, JTextField addressField, JCheckBox activeCheckBox) {
        codeField.setText("");
        cityField.setText("");
        phoneField.setText("");
        addressField.setText("");
        activeCheckBox.setSelected(false);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ManageBranchesPage frame = new ManageBranchesPage();
            frame.setVisible(true);
        });
    }
}
