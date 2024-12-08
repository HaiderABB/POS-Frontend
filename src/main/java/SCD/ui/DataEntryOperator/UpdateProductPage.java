package SCD.ui.DataEntryOperator;

import SCD.ui.Common.ButtonFactory;
import SCD.ui.Common.NavBar;

import javax.swing.*;
import java.awt.*;
import java.util.regex.Pattern;

public class UpdateProductPage extends JFrame {

    private DEOSidebar sidebar;
    private NavBar navBar;

    public UpdateProductPage() {
        setTitle("Update Product");
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        sidebar = new DEOSidebar();
        add(sidebar, BorderLayout.WEST);

        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        navBar = new NavBar();
        navBar.setTitle("Update Product");
        contentPanel.add(navBar, BorderLayout.NORTH);

        JPanel formPanel = new JPanel(new GridLayout(5, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel productCodeLabel = new JLabel("Product Code (PM-XXXX):");
        JTextField productCodeField = new JTextField();
        formPanel.add(productCodeLabel);
        formPanel.add(productCodeField);

        JLabel fieldLabel = new JLabel("Select Field to Update:");
        String[] fields = {"Name", "Price"};
        JComboBox<String> fieldComboBox = new JComboBox<>(fields);
        formPanel.add(fieldLabel);
        formPanel.add(fieldComboBox);

        JLabel newValueLabel = new JLabel("New Value:");
        JTextField newValueField = new JTextField();
        formPanel.add(newValueLabel);
        formPanel.add(newValueField);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        JButton updateButton = ButtonFactory.createStyledButton("Update Product");
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

                case "Price":
                    if (!validatePrice(newValue)) {
                        JOptionPane.showMessageDialog(this, "Invalid Price! Please enter a valid price.", "Error", JOptionPane.ERROR_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(this, "Valid Price!", "Success", JOptionPane.INFORMATION_MESSAGE);
                    }
                    break;

                default:
                    JOptionPane.showMessageDialog(this, "Unknown Field Selected!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        updateButton.addActionListener(e -> {
            String productCode = productCodeField.getText().trim();
            String selectedField = (String) fieldComboBox.getSelectedItem();
            String newValue = newValueField.getText().trim();

            if (productCode.isEmpty() || !validateProductCode(productCode)) {
                JOptionPane.showMessageDialog(this, "Invalid Product Code! Product Code must follow the format 'PM-XXXX'.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (newValue.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter a new value for the selected field!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (!validateField(selectedField, newValue)) {
                return;
            }

            updateProduct(productCode, selectedField, newValue);
            productCodeField.setText("");
            newValueField.setText("");
        });
    }

    private boolean validateProductCode(String productCode) {
        return productCode.matches("PM-\\d{4}");
    }

    private boolean validateField(String field, String value) {
        switch (field) {
            case "Name":
                if (!validateName(value)) {
                    JOptionPane.showMessageDialog(this, "Invalid Name! Name must not be empty and only contain letters and spaces.", "Error", JOptionPane.ERROR_MESSAGE);
                    return false;
                }
                break;

            case "Price":
                if (!validatePrice(value)) {
                    JOptionPane.showMessageDialog(this, "Invalid Price! Please enter a valid price.", "Error", JOptionPane.ERROR_MESSAGE);
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

    private boolean validatePrice(String price) {
        try {
            Double.parseDouble(price);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private void updateProduct(String productCode, String field, String newValue) {
        JOptionPane.showMessageDialog(this,
                "Product with Code " + productCode + " updated successfully!\n" +
                        "Updated Field: " + field + "\n" +
                        "New Value: " + newValue,
                "Success",
                JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            UpdateProductPage frame = new UpdateProductPage();
            frame.setVisible(true);
        });
    }
}