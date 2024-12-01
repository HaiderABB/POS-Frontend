package SCD.ui.SuperAdmin;

import SCD.ui.Common.ButtonFactory;
import SCD.ui.Common.NavBar;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class ManageUsersPage extends JFrame {
    private Sidebar sidebar;
    private DefaultTableModel tableModel;
    private NavBar navBar;

    public ManageUsersPage() {
        setTitle("Manage Branch Managers");
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        sidebar = new Sidebar();
        add(sidebar, BorderLayout.WEST);

        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));

        navBar = new NavBar();
        contentPanel.add(navBar, BorderLayout.NORTH);
        navBar.setTitle("Manage Branch Managers");

        String[] columnNames = {"Employee Code", "Username", "Email", "Phone", "Branch Code"};
        tableModel = new DefaultTableModel(columnNames, 0);
        JTable userTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(userTable);
        contentPanel.add(scrollPane, BorderLayout.CENTER);

        JPanel formPanel = new JPanel(new GridLayout(5, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel empCodeLabel = new JLabel("Employee Code (MH-1234):");
        JTextField empCodeField = new JTextField();
        JLabel usernameLabel = new JLabel("Username (Max 11 chars):");
        JTextField usernameField = new JTextField();
        JLabel emailLabel = new JLabel("Email:");
        JTextField emailField = new JTextField();
        JLabel phoneLabel = new JLabel("Phone (0321-1234567):");
        JTextField phoneField = new JTextField();
        JLabel branchCodeLabel = new JLabel("Branch Code (BH-1234):");
        JTextField branchCodeField = new JTextField();

        formPanel.add(empCodeLabel);
        formPanel.add(empCodeField);
        formPanel.add(usernameLabel);
        formPanel.add(usernameField);
        formPanel.add(emailLabel);
        formPanel.add(emailField);
        formPanel.add(phoneLabel);
        formPanel.add(phoneField);
        formPanel.add(branchCodeLabel);
        formPanel.add(branchCodeField);

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

        // Add Branch Manager logic
        addButton.addActionListener(e -> {
            String empCode = empCodeField.getText().trim();
            String username = usernameField.getText().trim();
            String email = emailField.getText().trim();
            String phone = phoneField.getText().trim();
            String branchCode = branchCodeField.getText().trim();

            if (validateBranchManagerInputs(empCode, username, email, phone, branchCode)) {
                // Check if the employee code already exists
                if (isExistingEmployeeCode(empCode)) {
                    JOptionPane.showMessageDialog(this, "Employee Code already exists!", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    tableModel.addRow(new Object[]{empCode, username, email, phone, branchCode});
                    clearFields(empCodeField, usernameField, emailField, phoneField, branchCodeField);
                    JOptionPane.showMessageDialog(this, "Branch Manager added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

        // Update Branch Manager logic
        updateButton.addActionListener(e -> {
            int selectedRow = userTable.getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(this, "Please select a branch manager to update!", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                String empCode = empCodeField.getText().trim();
                String username = usernameField.getText().trim();
                String email = emailField.getText().trim();
                String phone = phoneField.getText().trim();
                String branchCode = branchCodeField.getText().trim();

                if (validateBranchManagerInputs(empCode, username, email, phone, branchCode)) {
                    tableModel.setValueAt(empCode, selectedRow, 0);
                    tableModel.setValueAt(username, selectedRow, 1);
                    tableModel.setValueAt(email, selectedRow, 2);
                    tableModel.setValueAt(phone, selectedRow, 3);
                    tableModel.setValueAt(branchCode, selectedRow, 4);
                    clearFields(empCodeField, usernameField, emailField, phoneField, branchCodeField);
                    JOptionPane.showMessageDialog(this, "Branch Manager updated successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

        // Delete Branch Manager logic
        deleteButton.addActionListener(e -> {
            int selectedRow = userTable.getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(this, "Please select a branch manager to delete!", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                tableModel.removeRow(selectedRow);
                JOptionPane.showMessageDialog(this, "Branch Manager deleted successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            }
        });
    }

    private boolean validateBranchManagerInputs(String empCode, String username, String email, String phone, String branchCode) {
        if (!empCode.matches("MH-\\d{4}")) {
            JOptionPane.showMessageDialog(this, "Employee Code must follow the format 'MH-1234'", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (username.isEmpty() || username.length() > 11) {
            JOptionPane.showMessageDialog(this, "Username must not be empty and must not exceed 11 characters", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (!email.matches("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}")) {
            JOptionPane.showMessageDialog(this, "Invalid email format", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (!phone.matches("03\\d{2}-\\d{7}")) {
            JOptionPane.showMessageDialog(this, "Phone must follow the format '0321-1234567'", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (!branchCode.matches("BH-\\d{4}")) {
            JOptionPane.showMessageDialog(this, "Branch Code must follow the format 'BH-1234'", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }

    private boolean isExistingEmployeeCode(String empCode) {
        for (int i = 0; i < tableModel.getRowCount(); i++) {
            if (tableModel.getValueAt(i, 0).equals(empCode)) {
                return true;
            }
        }
        return false;
    }

    private void clearFields(JTextField... fields) {
        for (JTextField field : fields) {
            field.setText("");
        }
    }
}
