package SCD.controllers.SuperAdminControllers.ManageBranchesController;

import java.awt.event.ActionEvent;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import SCD.model.models.Branch;
import SCD.model.service.Json.AddResponseJSON;
import SCD.model.service.SuperAdminService.SuperAdminService;
import SCD.ui.SuperAdmin.ManageBranches.UpdateBranchPage;

public class UpdateBranchController {

    private UpdateBranchPage updateBranchPage;
    SuperAdminService superAdminService = new SuperAdminService();

    public UpdateBranchController() {

        updateBranchPage = new UpdateBranchPage();

        updateBranchPage.setVisible(true);

        updateBranchPage.getValidateButton().addActionListener(e -> {
            String selectedField = updateBranchPage.getSelectedField();
            String newValue = updateBranchPage.getNewValue();

            if (newValue.isEmpty()) {
                JOptionPane.showMessageDialog(updateBranchPage, "Please enter a new value!", "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            boolean isValid = validateField(selectedField, newValue);
            if (isValid) {
                JOptionPane.showMessageDialog(updateBranchPage, "Valid " + selectedField + "!", "Success",
                        JOptionPane.INFORMATION_MESSAGE);
            }
        });

        updateBranchPage.getUpdateButton().addActionListener((ActionEvent e) -> {
            String branchCode = updateBranchPage.getBranchCode();
            String selectedField = updateBranchPage.getSelectedField();
            String newValue = updateBranchPage.getNewValue();

            String temp;

            if (!validateBranchCode(branchCode)) {
                JOptionPane.showMessageDialog(updateBranchPage,
                        "Invalid Branch Code! Branch Code must follow the format 'BR-XXXX'.", "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (!validateField(selectedField, newValue)) {
                return;
            }

            if (branchCode.equals("BR-0001")) {

                JOptionPane.showMessageDialog(updateBranchPage, "Cannot update the default branch!", "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;

            }

            Branch branch = superAdminService.getBranchByCode(branchCode);

            if (branch == null) {
                JOptionPane.showMessageDialog(updateBranchPage, "Branch not found!", "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            temp = "";

            if (selectedField.equals("Name")) {
                temp = branch.getName();
                branch.setName(newValue);
            } else if (selectedField.equals("City")) {
                temp = branch.getCity();
                branch.setCity(newValue);
            } else if (selectedField.equals("Phone")) {

                boolean res = superAdminService.checkPhoneExists(newValue);
                if (res) {
                    JOptionPane.showMessageDialog(updateBranchPage, "Phone number already exists!", "Error",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }

                temp = branch.getPhone();
                branch.setPhone(newValue);
            } else if (selectedField.equals("Address")) {
                temp = branch.getAddress();
                branch.setAddress(newValue);
            }

            AddResponseJSON json = superAdminService.updateBranch(branch);

            if (json.isSuccess()) {
                updateBranch(branchCode, selectedField, newValue);
            } else {

                if (selectedField.equals("Name")) {
                    branch.setName(temp);
                } else if (selectedField.equals("City")) {
                    branch.setCity(temp);
                } else if (selectedField.equals("Phone")) {
                    branch.setPhone(temp);
                } else if (selectedField.equals("Address")) {
                    branch.setAddress(temp);
                }

                JOptionPane.showMessageDialog(updateBranchPage, "Failed to update branch!", "Error",
                        JOptionPane.ERROR_MESSAGE);

            }

            updateBranchPage.clearFields();
        });

    }

    public UpdateBranchController(UpdateBranchPage updateBranchPage) {
        this.updateBranchPage = updateBranchPage;

    }

    public boolean validateBranchCode(String branchCode) {
        return branchCode.matches("BR-\\d{4}");
    }

    public boolean validateField(String field, String value) {
        switch (field) {
            case "Name":
                return validateName(value);
            case "City":
                return validateCity(value);
            case "Phone":
                return validatePhone(value);
            case "Address":
                return validateAddress(value);
            default:
                JOptionPane.showMessageDialog(updateBranchPage, "Unknown Field Selected!", "Error",
                        JOptionPane.ERROR_MESSAGE);
                return false;
        }
    }

    private boolean validateName(String name) {
        if (!Pattern.matches("[A-Za-z\\s]+", name)) {
            JOptionPane.showMessageDialog(updateBranchPage,
                    "Invalid Name! Name must not be empty and only contain letters and spaces.", "Error",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }

    private boolean validateCity(String city) {
        if (!Pattern.matches("[A-Za-z\\s]+", city)) {
            JOptionPane.showMessageDialog(updateBranchPage, "Invalid City! City must contain only letters.", "Error",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }

    private boolean validatePhone(String phone) {

        if (!Pattern.matches("03\\d{9}", phone)) {
            JOptionPane.showMessageDialog(updateBranchPage,
                    "Invalid Phone! Phone must follow the format '03XXXXXXXXX'.", "Error", JOptionPane.ERROR_MESSAGE);

            return false;
        }
        return true;
    }

    private boolean validateAddress(String address) {
        if (address.length() < 10) {
            JOptionPane.showMessageDialog(updateBranchPage,
                    "Invalid Address! Address must not be empty and should have at least 10 characters.", "Error",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }

    void updateBranch(String branchCode, String field, String newValue) {
        JOptionPane.showMessageDialog(updateBranchPage,
                "Branch with Code " + branchCode + " updated successfully!\n" +
                        "Updated Field: " + field + "\n" +
                        "New Value: " + newValue,
                "Success",
                JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            UpdateBranchPage page = new UpdateBranchPage();
            new UpdateBranchController(page);
            page.setVisible(true);
        });
    }
}
