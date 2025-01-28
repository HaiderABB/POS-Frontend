package SCD.controllers.SuperAdminControllers.ManagesBranchManagerController;

import java.util.regex.Pattern;

import javax.swing.JOptionPane;

import SCD.model.models.Employee;
import SCD.model.service.Common.CommonServices;
import SCD.model.service.Json.AddResponseJSON;
import SCD.model.service.SuperAdminService.SuperAdminService;
import SCD.ui.SuperAdmin.ManageBranchManager.UpdateBranchManagerPage;

public class UpdateBranchManagerController {

    private UpdateBranchManagerPage view;
    SuperAdminService superAdminService = new SuperAdminService();
    CommonServices commonServices = new CommonServices();

    public UpdateBranchManagerController() {

        view = new UpdateBranchManagerPage();
        view.setVisible(true);
        initController();

    }

    public UpdateBranchManagerController(UpdateBranchManagerPage view) {
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

    void handleUpdate() {
        String managerCode = view.getManagerCodeField().getText().trim();
        String selectedField = (String) view.getFieldComboBox().getSelectedItem();
        String newValue = view.getNewValueField().getText().trim();

        if (managerCode.isEmpty() || !validateManagerCode(managerCode)) {
            JOptionPane.showMessageDialog(view, "Invalid Manager Code! Manager Code must follow the format 'BM-XXXX'.",
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

        Employee manager = commonServices.getEmployeeByEmployeeCode(managerCode);

        if (manager == null) {
            JOptionPane.showMessageDialog(view, "Manager not found!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (manager.isActive() == false) {
            JOptionPane.showMessageDialog(view, "Manager is not active!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String temp;

        if (selectedField.equals("Name")) {
            temp = manager.getName();
            manager.setName(newValue);
        } else if (selectedField.equals("Email")) {
            temp = manager.getEmail();

            boolean res = commonServices.checkEmailExists(newValue);
            if (res) {
                JOptionPane.showMessageDialog(view, "Email already exists!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            manager.setEmail(newValue);
        } else {
            temp = manager.getPhoneNumber();
            boolean res = commonServices.checkPhoneNumberExists(newValue);
            if (res) {
                JOptionPane.showMessageDialog(view, "Phone Number already exists!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            manager.setPhoneNumber(newValue);
        }

        AddResponseJSON json = commonServices.UpdateEmployee(manager);

        if (json.isSuccess()) {
            updateManager(managerCode, selectedField, newValue);
            view.getManagerCodeField().setText("");
            view.getNewValueField().setText("");
        } else {
            JOptionPane.showMessageDialog(view, "Update failed!", "Error", JOptionPane.ERROR_MESSAGE);
        }

    }

    public boolean validateManagerCode(String managerCode) {
        boolean isValid = managerCode.matches("BM-\\d{4}");
        if (!isValid) {
            JOptionPane.showMessageDialog(view, "Manager Code must follow the format 'BM-XXXX'.", "Error",
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
            case "Phone Number": // scd- proj initValidation for phone number
                return validatePhoneNumber(value);
            default:
                JOptionPane.showMessageDialog(view, "Unknown Field Selected!", "Error", JOptionPane.ERROR_MESSAGE);
                return false;
        }
    }

    boolean validateName(String name) {
        boolean isValid = name != null && !name.isEmpty() && Pattern.matches("[A-Za-z\\s]+", name);
        if (!isValid) {
            JOptionPane.showMessageDialog(view,
                    "Invalid Name! Name must not be empty and only contain letters and spaces.", "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
        return isValid;
    }

    boolean validateEmail(String email) {
        boolean isValid = email != null && Pattern.matches("^[\\w.%+-]+@[\\w.-]+\\.[a-zA-Z]{2,6}$", email);
        if (!isValid) {
            JOptionPane.showMessageDialog(view, "Invalid Email! Please enter a valid email address.", "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
        return isValid;
    }

    boolean validateBranchCode(String branchCode) {
        boolean isValid = branchCode != null && branchCode.matches("BR-\\d{4}");
        if (!isValid) {
            JOptionPane.showMessageDialog(view, "Invalid Branch Code! Branch Code must follow the format 'BR-XXXX'.",
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
        return isValid;
    }

    boolean validatePhoneNumber(String phoneNumber) {
        boolean isValid = phoneNumber != null && phoneNumber.matches("03\\d{9}");
        if (!isValid) {
            JOptionPane.showMessageDialog(view,
                    "Invalid Phone Number! Phone Number must follow the format '03XXXXXXXXX'.", "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
        return isValid;
    }

    void updateManager(String managerCode, String field, String newValue) {
        JOptionPane.showMessageDialog(view,
                "Branch Manager with Code " + managerCode + " updated successfully!\n" +
                        "Updated Field: " + field + "\n" +
                        "New Value: " + newValue,
                "Success",
                JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String[] args) {
        new UpdateBranchManagerController(); // scd- proj initUse the default constructor
    }
}
