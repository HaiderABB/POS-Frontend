package SCD.ui.SuperAdmin.ManageBranchManager;

import SCD.ui.Common.ButtonFactory;
import SCD.ui.Common.NavBar;
import SCD.ui.SuperAdmin.Sidebar;

import javax.swing.*;
import java.awt.*;
import java.util.regex.Pattern;

public class UpdateBranchManagerPage extends JFrame {

    private Sidebar sidebar;
    private NavBar navBar;

    public UpdateBranchManagerPage() {
        setTitle("Update Branch Manager");
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        sidebar = new Sidebar();
        add(sidebar, BorderLayout.WEST);

        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        navBar = new NavBar();
        navBar.setTitle("Update Branch Manager");
        contentPanel.add(navBar, BorderLayout.NORTH);

        JPanel formPanel = new JPanel(new GridLayout(5, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel managerCodeLabel = new JLabel("Branch Manager Code (BM-XXXX):");
        JTextField managerCodeField = new JTextField();
        formPanel.add(managerCodeLabel);
        formPanel.add(managerCodeField);

        JLabel fieldLabel = new JLabel("Select Field to Update:");
        String[] fields = {"Name", "Email", "Branch Code"};
        JComboBox<String> fieldComboBox = new JComboBox<>(fields);
        formPanel.add(fieldLabel);
        formPanel.add(fieldComboBox);

        JLabel newValueLabel = new JLabel("New Value:");
        JTextField newValueField = new JTextField();
        formPanel.add(newValueLabel);
        formPanel.add(newValueField);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        JButton updateButton = ButtonFactory.createStyledButton("Update Manager");
        JButton validateButton = ButtonFactory.createStyledButton("Validate Field");
        buttonPanel.add(validateButton);
        buttonPanel.add(updateButton);

        contentPanel.add(formPanel, BorderLayout.CENTER);
        contentPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(contentPanel, BorderLayout.CENTER);
        setLocationRelativeTo(null);

        validateButton.addActionListener(e -> {
            String selectedField = (String) fieldComboBox.getSelectedItem();
            String newValue = newValueField.getText().trim();

            if (newValue.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter a new value!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            switch (selectedField) {
                case "Name":
                    if (!validateName(newValue)) {
                        JOptionPane.showMessageDialog(this, "Invalid Name! Name must not be empty and only contain letters and spaces.", "Error", JOptionPane.ERROR_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(this, "Valid Name!", "Success", JOptionPane.INFORMATION_MESSAGE);
                    }
                    break;

                case "Email":
                    if (!validateEmail(newValue)) {
                        JOptionPane.showMessageDialog(this, "Invalid Email! Please enter a valid email address.", "Error", JOptionPane.ERROR_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(this, "Valid Email!", "Success", JOptionPane.INFORMATION_MESSAGE);
                    }
                    break;

                case "Branch Code":
                    if (!validateBranchCode(newValue)) {
                        JOptionPane.showMessageDialog(this, "Invalid Branch Code! Branch Code must follow the format 'BR-XXXX'.", "Error", JOptionPane.ERROR_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(this, "Valid Branch Code!", "Success", JOptionPane.INFORMATION_MESSAGE);
                    }
                    break;

                default:
                    JOptionPane.showMessageDialog(this, "Unknown Field Selected!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        updateButton.addActionListener(e -> {
            String managerCode = managerCodeField.getText().trim();
            String selectedField = (String) fieldComboBox.getSelectedItem();
            String newValue = newValueField.getText().trim();

            if (managerCode.isEmpty() || !validateManagerCode(managerCode)) {
                JOptionPane.showMessageDialog(this, "Invalid Manager Code! Manager Code must follow the format 'BM-XXXX'.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (newValue.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter a new value for the selected field!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (!validateField(selectedField, newValue)) {
                return;
            }

            updateManager(managerCode, selectedField, newValue);
            managerCodeField.setText("");
            newValueField.setText("");
        });
    }

    private boolean validateManagerCode(String managerCode) {
        return managerCode.matches("BM-\\d{4}");
    }

    private boolean validateField(String field, String value) {
        switch (field) {
            case "Name":
                if (!validateName(value)) {
                    JOptionPane.showMessageDialog(this, "Invalid Name! Name must not be empty and only contain letters and spaces.", "Error", JOptionPane.ERROR_MESSAGE);
                    return false;
                }
                break;

            case "Email":
                if (!validateEmail(value)) {
                    JOptionPane.showMessageDialog(this, "Invalid Email! Please enter a valid email address.", "Error", JOptionPane.ERROR_MESSAGE);
                    return false;
                }
                break;

            case "Branch Code":
                if (!validateBranchCode(value)) {
                    JOptionPane.showMessageDialog(this, "Invalid Branch Code! Branch Code must follow the format 'BR-XXXX'.", "Error", JOptionPane.ERROR_MESSAGE);
                    return false;
                }
                break;

            default:
                JOptionPane.showMessageDialog(this, "Unknown Field Selected!", "Error", JOptionPane.ERROR_MESSAGE);
                return false;
        }
        return true;
    }

    private boolean validateName(String name) {
        return name != null && !name.isEmpty() && Pattern.matches("[A-Za-z\\s]+", name);
    }

    private boolean validateEmail(String email) {
        return email != null && Pattern.matches("^[\\w.%+-]+@[\\w.-]+\\.[a-zA-Z]{2,6}$", email);
    }

    private boolean validateBranchCode(String branchCode) {
        return branchCode != null && branchCode.matches("BR-\\d{4}");
    }

    private void updateManager(String managerCode, String field, String newValue) {
        JOptionPane.showMessageDialog(this,
                "Branch Manager with Code " + managerCode + " updated successfully!\n" +
                        "Updated Field: " + field + "\n" +
                        "New Value: " + newValue,
                "Success",
                JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            UpdateBranchManagerPage frame = new UpdateBranchManagerPage();
            frame.setVisible(true);
        });
    }
}
