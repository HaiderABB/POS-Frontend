package SCD.controllers.SuperAdminControllers.ManagesBranchManagerController;

import java.util.regex.Pattern;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import SCD.model.models.Employee;
import SCD.ui.SuperAdmin.ManageBranchManager.UpdateBranchManagerPage;

public class UpdateBranchManagerController {
    private UpdateBranchManagerPage view;
    Employee employee;

    public UpdateBranchManagerController(Employee employee) {
        this.employee = employee;
        SwingUtilities.invokeLater(() -> {
            UpdateBranchManagerPage page = new UpdateBranchManagerPage(employee);

            page.setVisible(true);
        });

    }

    public UpdateBranchManagerController(UpdateBranchManagerPage view, Employee employee) {
        this.employee = employee;
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

        updateManager(managerCode, selectedField, newValue);
        view.getManagerCodeField().setText("");
        view.getNewValueField().setText("");
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

    private void updateManager(String managerCode, String field, String newValue) {
        JOptionPane.showMessageDialog(view,
                "Branch Manager with Code " + managerCode + " updated successfully!\n" +
                        "Updated Field: " + field + "\n" +
                        "New Value: " + newValue,
                "Success",
                JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            UpdateBranchManagerPage page = new UpdateBranchManagerPage(null);
            new UpdateBranchManagerController(page, null);
            page.setVisible(true);
        });
    }

    public void showPage() {
        new UpdateBranchManagerPage(employee);
    }
}
