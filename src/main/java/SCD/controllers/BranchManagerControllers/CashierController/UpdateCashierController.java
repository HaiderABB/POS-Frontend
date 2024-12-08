package SCD.controllers.BranchManagerControllers.CashierController;

import SCD.ui.BranchManager.ManageCashier.UpdateCashierPage;

import javax.swing.*;
import java.util.regex.Pattern;

public class UpdateCashierController {
    private final UpdateCashierPage view;

    public UpdateCashierController(UpdateCashierPage view) {
        this.view = view;
        initController();
    }

    private void initController() {
        view.getValidateButton().addActionListener(e -> handleValidation());
        view.getUpdateButton().addActionListener(e -> handleUpdate());
    }

    private void handleValidation() {
        String selectedField = (String) view.getFieldComboBox().getSelectedItem();
        String newValue = view.getNewValueField().getText().trim();

        if (newValue.isEmpty()) {
            JOptionPane.showMessageDialog(view, "Please enter a new value!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        boolean isValid = validateField(selectedField, newValue);

        if (isValid) {
            JOptionPane.showMessageDialog(view, "Valid " + selectedField + "!", "Success", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(view, "Invalid " + selectedField + "!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void handleUpdate() {
        String cashierCode = view.getCashierCodeField().getText().trim();
        String selectedField = (String) view.getFieldComboBox().getSelectedItem();
        String newValue = view.getNewValueField().getText().trim();

        if (cashierCode.isEmpty() || !validateCashierCode(cashierCode)) {
            JOptionPane.showMessageDialog(view, "Invalid Cashier Code! Cashier Code must follow the format 'CM-XXXX'.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (newValue.isEmpty()) {
            JOptionPane.showMessageDialog(view, "Please enter a new value for the selected field!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!validateField(selectedField, newValue)) {
            return;
        }

        updateCashier(cashierCode, selectedField, newValue);
        view.getCashierCodeField().setText("");
        view.getNewValueField().setText("");
    }

    public boolean validateCashierCode(String cashierCode) {
        boolean isValid = cashierCode.matches("CM-\\d{4}");
        if (!isValid) {
            JOptionPane.showMessageDialog(view, "Cashier Code must follow the format 'CM-XXXX'.", "Error", JOptionPane.ERROR_MESSAGE);
        }
        return isValid;
    }

    public boolean validateField(String field, String value) {
        switch (field) {
            case "Name":
                return validateName(value);
            case "Email":
                return validateEmail(value);
            case "Branch Code":
                return validateBranchCode(value);
            default:
                JOptionPane.showMessageDialog(view, "Unknown Field Selected!", "Error", JOptionPane.ERROR_MESSAGE);
                return false;
        }
    }

    private boolean validateName(String name) {
        boolean isValid = name != null && !name.isEmpty() && Pattern.matches("[A-Za-z\\s]+", name);
        if (!isValid) {
            JOptionPane.showMessageDialog(view, "Invalid Name! Name must not be empty and only contain letters and spaces.", "Error", JOptionPane.ERROR_MESSAGE);
        }
        return isValid;
    }

    private boolean validateEmail(String email) {
        boolean isValid = email != null && Pattern.matches("^[\\w.%+-]+@[\\w.-]+\\.[a-zA-Z]{2,6}$", email);
        if (!isValid) {
            JOptionPane.showMessageDialog(view, "Invalid Email! Please enter a valid email address.", "Error", JOptionPane.ERROR_MESSAGE);
        }
        return isValid;
    }

    private boolean validateBranchCode(String branchCode) {
        boolean isValid = branchCode != null && branchCode.matches("BR-\\d{4}");
        if (!isValid) {
            JOptionPane.showMessageDialog(view, "Invalid Branch Code! Branch Code must follow the format 'BR-XXXX'.", "Error", JOptionPane.ERROR_MESSAGE);
        }
        return isValid;
    }

    private void updateCashier(String cashierCode, String field, String newValue) {
        JOptionPane.showMessageDialog(view,
                "Cashier with Code " + cashierCode + " updated successfully!\n" +
                        "Updated Field: " + field + "\n" +
                        "New Value: " + newValue,
                "Success",
                JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            UpdateCashierPage page = new UpdateCashierPage();
            new UpdateCashierController(page);
            page.setVisible(true);
        });
    }
}
