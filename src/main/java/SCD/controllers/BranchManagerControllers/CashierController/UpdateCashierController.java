package SCD.controllers.BranchManagerControllers.CashierController;

import java.util.regex.Pattern;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import SCD.controllers.cache.Cache;
import SCD.model.models.Employee;
import SCD.model.service.Common.CommonServices;
import SCD.model.service.Json.AddResponseJSON;
import SCD.ui.BranchManager.ManageCashier.UpdateCashierPage;

public class UpdateCashierController {
    UpdateCashierPage view;
    CommonServices commonServices = new CommonServices();

    public UpdateCashierController() {

        view = new UpdateCashierPage();
        view.setVisible(true);
        initController();

    }

    public UpdateCashierController(UpdateCashierPage view) {
        this.view = view;
        initController();
    }

    private void initController() {
        view.getValidateButton().addActionListener(e -> handleValidation());
        view.getUpdateButton().addActionListener(e -> handleUpdate());
    }

    void handleValidation() {
        String selectedField = (String) view.getFieldComboBox().getSelectedItem();
        String newValue = view.getNewValueField().getText().trim();

        if (newValue.isEmpty()) {
            JOptionPane.showMessageDialog(view, "Please enter a new value!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        boolean isValid = validateField(selectedField, newValue);

        if (isValid) {
            JOptionPane.showMessageDialog(view, "Valid " + selectedField + "!", "Success",
                    JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(view, "Invalid " + selectedField + "!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void handleUpdate() {
        String cashierCode = view.getCashierCodeField().getText().trim();
        String selectedField = (String) view.getFieldComboBox().getSelectedItem();
        String newValue = view.getNewValueField().getText().trim();

        if (cashierCode.isEmpty() || !validateCashierCode(cashierCode)) {
            JOptionPane.showMessageDialog(view, "Invalid Cashier Code! Cashier Code must follow the format 'CM-XXXX'.",
                    "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (newValue.isEmpty()) {
            JOptionPane.showMessageDialog(view, "Please enter a new value for the selected field!", "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!validateField(selectedField, newValue)) {
            return;
        }

        Cache cache = new Cache();
        String emp_code = cache.getCache();

        Employee cashier = commonServices.getEmployeeByEmployeeCode(cashierCode);
        Employee manager = commonServices.getEmployeeByEmployeeCode(emp_code);

        if (cashier == null) {
            JOptionPane.showMessageDialog(view, "Cashier not found!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (cashier.isActive() == false) {
            JOptionPane.showMessageDialog(view, "Cashier is not active!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (manager.getBranch().getBranchCode().equals(cashier.getBranch().getBranchCode())) {

            String temp;

            if (selectedField.equals("Name")) {
                temp = cashier.getName();
                cashier.setName(newValue);
            } else if (selectedField.equals("Email")) {
                temp = cashier.getEmail();

                boolean res = commonServices.checkEmailExists(newValue);
                if (res) {
                    JOptionPane.showMessageDialog(view, "Email already exists!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                cashier.setEmail(newValue);
            } else {
                temp = cashier.getPhoneNumber();
                boolean res = commonServices.checkPhoneNumberExists(newValue);
                if (res) {
                    JOptionPane.showMessageDialog(view, "Phone Number already exists!", "Error",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }
                cashier.setPhoneNumber(newValue);
            }

            AddResponseJSON json = commonServices.UpdateEmployee(cashier);

            if (json.isSuccess()) {
                updateCashier(cashierCode, selectedField, newValue);
                view.getCashierCodeField().setText("");
                view.getNewValueField().setText("");
            } else {
                JOptionPane.showMessageDialog(view, "Update failed!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(view, "You can only update your own branch!", "Error",
                    JOptionPane.ERROR_MESSAGE);
        }

    }

    public boolean validateCashierCode(String cashierCode) {
        boolean isValid = cashierCode.matches("CM-\\d{4}");
        if (!isValid) {
            JOptionPane.showMessageDialog(view, "Cashier Code must follow the format 'CM-XXXX'.", "Error",
                    JOptionPane.ERROR_MESSAGE);
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
            case "Phone Number": // scd- proj initAdded validation for phone number
                return validatePhoneNumber(value);
            default:
                JOptionPane.showMessageDialog(view, "Unknown Field Selected!", "Error", JOptionPane.ERROR_MESSAGE);
                return false;
        }
    }

    private boolean validateName(String name) {
        boolean isValid = name != null && !name.isEmpty() && Pattern.matches("[A-Za-z\\s]+", name);
        if (!isValid) {
            JOptionPane.showMessageDialog(view,
                    "Invalid Name! Name must not be empty and only contain letters and spaces.", "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
        return isValid;
    }

    private boolean validateEmail(String email) {
        boolean isValid = email != null && Pattern.matches("^[\\w.%+-]+@[\\w.-]+\\.[a-zA-Z]{2,6}$", email);
        if (!isValid) {
            JOptionPane.showMessageDialog(view, "Invalid Email! Please enter a valid email address.", "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
        return isValid;
    }

    private boolean validateBranchCode(String branchCode) {
        boolean isValid = branchCode != null && branchCode.matches("BR-\\d{4}");
        if (!isValid) {
            JOptionPane.showMessageDialog(view, "Invalid Branch Code! Branch Code must follow the format 'BR-XXXX'.",
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
        return isValid;
    }

    private boolean validatePhoneNumber(String phoneNumber) {
        boolean isValid = phoneNumber != null && phoneNumber.matches("03\\d{9}");
        if (!isValid) {
            JOptionPane.showMessageDialog(view,
                    "Invalid Phone Number! Phone Number must follow the format '03XXXXXXXXX'.", "Error",
                    JOptionPane.ERROR_MESSAGE);
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
