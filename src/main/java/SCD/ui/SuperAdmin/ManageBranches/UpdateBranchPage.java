package SCD.ui.SuperAdmin.ManageBranches;

import SCD.ui.Common.ButtonFactory;
import SCD.ui.Common.NavBar;
import SCD.ui.SuperAdmin.Sidebar;

import javax.swing.*;
import java.awt.*;
import java.util.regex.Pattern;

public class UpdateBranchPage extends JFrame {

    private Sidebar sidebar;
    private NavBar navBar;

    public UpdateBranchPage() {
        setTitle("Update Branch");
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        sidebar = new Sidebar();
        add(sidebar, BorderLayout.WEST);

        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        navBar = new NavBar();
        navBar.setTitle("Update Branch");
        contentPanel.add(navBar, BorderLayout.NORTH);

        JPanel formPanel = new JPanel(new GridLayout(5, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel branchCodeLabel = new JLabel("Branch Code (BR-XXXX):");
        JTextField branchCodeField = new JTextField();
        formPanel.add(branchCodeLabel);
        formPanel.add(branchCodeField);

        JLabel fieldLabel = new JLabel("Select Field to Update:");
        String[] fields = {"Name", "City", "Phone", "Address"};
        JComboBox<String> fieldComboBox = new JComboBox<>(fields);
        formPanel.add(fieldLabel);
        formPanel.add(fieldComboBox);

        JLabel newValueLabel = new JLabel("New Value:");
        JTextField newValueField = new JTextField();
        formPanel.add(newValueLabel);
        formPanel.add(newValueField);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        JButton updateButton = ButtonFactory.createStyledButton("Update Branch");
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

                case "City":
                    if (!validateCity(newValue)) {
                        JOptionPane.showMessageDialog(this, "Invalid City! City must contain only letters.", "Error", JOptionPane.ERROR_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(this, "Valid City!", "Success", JOptionPane.INFORMATION_MESSAGE);
                    }
                    break;

                case "Phone":
                    if (!validatePhone(newValue)) {
                        JOptionPane.showMessageDialog(this, "Invalid Phone! Phone must follow the format '0321-1234567'.", "Error", JOptionPane.ERROR_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(this, "Valid Phone!", "Success", JOptionPane.INFORMATION_MESSAGE);
                    }
                    break;

                case "Address":
                    if (!validateAddress(newValue)) {
                        JOptionPane.showMessageDialog(this, "Invalid Address! Address must not be empty and should have at least 10 characters.", "Error", JOptionPane.ERROR_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(this, "Valid Address!", "Success", JOptionPane.INFORMATION_MESSAGE);
                    }
                    break;

                default:
                    JOptionPane.showMessageDialog(this, "Unknown Field Selected!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        updateButton.addActionListener(e -> {
            String branchCode = branchCodeField.getText().trim();
            String selectedField = (String) fieldComboBox.getSelectedItem();
            String newValue = newValueField.getText().trim();

            if (branchCode.isEmpty() || !validateBranchCode(branchCode)) {
                JOptionPane.showMessageDialog(this, "Invalid Branch Code! Branch Code must follow the format 'BR-XXXX'.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (newValue.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter a new value for the selected field!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (!validateField(selectedField, newValue)) {
                return;
            }

            updateBranch(branchCode, selectedField, newValue);
            branchCodeField.setText("");
            newValueField.setText("");
        });
    }

    private boolean validateBranchCode(String branchCode) {
        return branchCode.matches("BR-\\d{4}");
    }

    private boolean validateField(String field, String value) {
        switch (field) {
            case "Name":
                if (!validateName(value)) {
                    JOptionPane.showMessageDialog(this, "Invalid Name! Name must not be empty and only contain letters and spaces.", "Error", JOptionPane.ERROR_MESSAGE);
                    return false;
                }
                break;

            case "City":
                if (!validateCity(value)) {
                    JOptionPane.showMessageDialog(this, "Invalid City! City must contain only letters.", "Error", JOptionPane.ERROR_MESSAGE);
                    return false;
                }
                break;

            case "Phone":
                if (!validatePhone(value)) {
                    JOptionPane.showMessageDialog(this, "Invalid Phone! Phone must follow the format '0321-1234567'.", "Error", JOptionPane.ERROR_MESSAGE);
                    return false;
                }
                break;

            case "Address":
                if (!validateAddress(value)) {
                    JOptionPane.showMessageDialog(this, "Invalid Address! Address must not be empty and should have at least 10 characters.", "Error", JOptionPane.ERROR_MESSAGE);
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

    private boolean validateCity(String city) {
        return city != null && !city.isEmpty() && Pattern.matches("[A-Za-z\\s]+", city);
    }

    private boolean validatePhone(String phone) {
        return phone != null && Pattern.matches("03\\d{2}-\\d{7}", phone);
    }

    private boolean validateAddress(String address) {
        return address != null && address.length() >= 10;
    }

    private void updateBranch(String branchCode, String field, String newValue) {
        JOptionPane.showMessageDialog(this,
                "Branch with Code " + branchCode + " updated successfully!\n" +
                        "Updated Field: " + field + "\n" +
                        "New Value: " + newValue,
                "Success",
                JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            UpdateBranchPage frame = new UpdateBranchPage();
            frame.setVisible(true);
        });
    }
}
