package SCD.ui.BranchManager.ManageDataEntryOperator;


import SCD.ui.BranchManager.BranchSidebar;
import SCD.ui.Common.ButtonFactory;
import SCD.ui.Common.NavBar;

import javax.swing.*;
import java.awt.*;
import java.util.regex.Pattern;

public class UpdateDataEntryPage extends JFrame {

    private BranchSidebar sidebar;
    private NavBar navBar;

    public UpdateDataEntryPage() {
        setTitle("Update Data Entry Personnel");
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        sidebar = new BranchSidebar();
        add(sidebar, BorderLayout.WEST);

        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        navBar = new NavBar();
        navBar.setTitle("Update Data Entry Personnel");
        contentPanel.add(navBar, BorderLayout.NORTH);

        JPanel formPanel = new JPanel(new GridLayout(5, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel dataEntryCodeLabel = new JLabel("Data Entry Code (DM-XXXX):");
        JTextField dataEntryCodeField = new JTextField();
        formPanel.add(dataEntryCodeLabel);
        formPanel.add(dataEntryCodeField);

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
        JButton updateButton = ButtonFactory.createStyledButton("Update Data Entry");
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
            String dataEntryCode = dataEntryCodeField.getText().trim();
            String selectedField = (String) fieldComboBox.getSelectedItem();
            String newValue = newValueField.getText().trim();

            if (dataEntryCode.isEmpty() || !validateDataEntryCode(dataEntryCode)) {
                JOptionPane.showMessageDialog(this, "Invalid Data Entry Code! Data Entry Code must follow the format 'DM-XXXX'.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (newValue.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter a new value for the selected field!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (!validateField(selectedField, newValue)) {
                return;
            }

            updateDataEntry(dataEntryCode, selectedField, newValue);
            dataEntryCodeField.setText("");
            newValueField.setText("");
        });
    }

    private boolean validateDataEntryCode(String dataEntryCode) {
        return dataEntryCode.matches("DM-\\d{4}");
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

    private void updateDataEntry(String dataEntryCode, String field, String newValue) {
        JOptionPane.showMessageDialog(this,
                "Data Entry Personnel with Code " + dataEntryCode + " updated successfully!\n" +
                        "Updated Field: " + field + "\n" +
                        "New Value: " + newValue,
                "Success",
                JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            UpdateDataEntryPage frame = new UpdateDataEntryPage();
            frame.setVisible(true);
        });
    }
}
