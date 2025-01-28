package SCD.controllers.BranchManagerControllers.DataEntryOperatorController;

import java.util.regex.Pattern;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import SCD.controllers.cache.Cache;
import SCD.model.models.Employee;
import SCD.model.service.Common.CommonServices;
import SCD.model.service.Json.AddResponseJSON;
import SCD.ui.BranchManager.ManageDataEntryOperator.UpdateDataEntryPage;

public class UpdateDataEntryController {
    private UpdateDataEntryPage view;
    CommonServices commonServices = new CommonServices();

    public UpdateDataEntryController() {

        view = new UpdateDataEntryPage();
        view.setVisible(true);
        initController();

    }

    public UpdateDataEntryController(UpdateDataEntryPage view) {
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
            JOptionPane.showMessageDialog(view, "Valid " + selectedField + "!", "Success",
                    JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(view, "Invalid " + selectedField + "!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void handleUpdate() {
        String dataEntryCode = view.getDataEntryCodeField().getText().trim();
        String selectedField = (String) view.getFieldComboBox().getSelectedItem();
        String newValue = view.getNewValueField().getText().trim();

        if (dataEntryCode.isEmpty() || !validateDataEntryCode(dataEntryCode)) {
            JOptionPane.showMessageDialog(view,
                    "Invalid Data Entry Code! Data Entry Code must follow the format 'DM-XXXX'.", "Error",
                    JOptionPane.ERROR_MESSAGE);
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

        Employee entryOP = commonServices.getEmployeeByEmployeeCode(dataEntryCode);
        Employee manager = commonServices.getEmployeeByEmployeeCode(emp_code);

        if (entryOP == null) {
            JOptionPane.showMessageDialog(view, "Data Entry Operator not found!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (entryOP.isActive() == false) {
            JOptionPane.showMessageDialog(view, "Data Entry Operator is not active!", "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (manager.getBranch().getBranchCode().equals(entryOP.getBranch().getBranchCode())) {

            String temp;

            if (selectedField.equals("Name")) {
                temp = entryOP.getName();
                entryOP.setName(newValue);
            } else if (selectedField.equals("Email")) {
                temp = entryOP.getEmail();

                boolean res = commonServices.checkEmailExists(newValue);
                if (res) {
                    JOptionPane.showMessageDialog(view, "Email already exists!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                entryOP.setEmail(newValue);
            } else {
                temp = entryOP.getPhoneNumber();
                boolean res = commonServices.checkPhoneNumberExists(newValue);
                if (res) {
                    JOptionPane.showMessageDialog(view, "Phone Number already exists!", "Error",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }
                entryOP.setPhoneNumber(newValue);
            }

            AddResponseJSON json = commonServices.UpdateEmployee(entryOP);

            if (json.isSuccess()) {
                updateDataEntry(dataEntryCode, selectedField, newValue);
                view.getDataEntryCodeField().setText("");
                view.getNewValueField().setText("");
            } else {
                JOptionPane.showMessageDialog(view, "Update failed!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(view, "You can only update your own branch!", "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    public boolean validateDataEntryCode(String dataEntryCode) {
        boolean isValid = dataEntryCode.matches("DM-\\d{4}");
        if (!isValid) {
            JOptionPane.showMessageDialog(view, "Data Entry Code must follow the format 'DM-XXXX'.", "Error",
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

    private void updateDataEntry(String dataEntryCode, String field, String newValue) {
        JOptionPane.showMessageDialog(view,
                "Data Entry Personnel with Code " + dataEntryCode + " updated successfully!\n" +
                        "Updated Field: " + field + "\n" +
                        "New Value: " + newValue,
                "Success",
                JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            UpdateDataEntryPage page = new UpdateDataEntryPage();
            new UpdateDataEntryController(page);
            page.setVisible(true);
        });
    }
}
