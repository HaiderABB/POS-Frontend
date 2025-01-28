package SCD.controllers.SuperAdminControllers.ManagesBranchManagerController;

import java.util.regex.Pattern;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import SCD.model.models.Branch;
import SCD.model.models.Employee;
import SCD.model.service.Common.CommonServices;
import SCD.model.service.Json.AddResponseJSON;
import SCD.model.service.SuperAdminService.SuperAdminService;
import SCD.ui.SuperAdmin.ManageBranchManager.AddBranchManagerPage;

public class AddBranchManagerController {

    private AddBranchManagerPage view;
    CommonServices commonServices = new CommonServices();
    SuperAdminService superAdminService = new SuperAdminService();

    public AddBranchManagerController() {

        view = new AddBranchManagerPage();
        view.setVisible(true);
        initController();

    }

    public AddBranchManagerController(AddBranchManagerPage view) {
        this.view = view;

    }

    private void initController() {
        this.view.getAddButton().addActionListener(e -> handleAddBranchManager());
    }

    private void handleAddBranchManager() {
        String name = view.getNameField().getText().trim();
        String email = view.getEmailField().getText().trim();
        String branchCode = view.getBranchCodeField().getText().trim();
        String phone = view.getPhoneField().getText().trim();

        if (!validateInputs(name, email, branchCode, phone)) {
            return;
        }

        boolean res;
        Branch br = superAdminService.getBranchByCode(branchCode);
        if (br == null) {
            JOptionPane.showMessageDialog(view, "Branch does not exist!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        res = commonServices.checkEmailExists(email);
        if (res) {
            JOptionPane.showMessageDialog(view, "Email already exists!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        res = commonServices.checkPhoneNumberExists(phone);
        if (res) {
            JOptionPane.showMessageDialog(view, "Phone number already exists!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Employee employee = new Employee(name, "MANAGER", br, phone, email);

        AddResponseJSON json = commonServices.AddEmployee(employee);

        if (json.isSuccess()) {
            JOptionPane.showMessageDialog(view, "Branch Manager added successfully!", "Success",
                    JOptionPane.INFORMATION_MESSAGE);
            saveBranchManager(name, email, branchCode, phone, json.getCode());
            clearFields();
        } else {
            JOptionPane.showMessageDialog(view, "Failed to add branch manager!", "Error", JOptionPane.ERROR_MESSAGE);
        }

    }

    public boolean validateInputs(String name, String email, String branchCode, String phone) {
        if (name.isEmpty() || email.isEmpty() || branchCode.isEmpty() || phone.isEmpty()) {
            JOptionPane.showMessageDialog(view, "Please fill out all fields!", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if (!Pattern.matches("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}", email)) {
            JOptionPane.showMessageDialog(view, "Invalid email format!", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if (!Pattern.matches("BR-\\d{4}", branchCode)) {
            JOptionPane.showMessageDialog(view, "Branch Code must follow the format 'BR-XXXX'!", "Error",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if (!Pattern.matches("03\\d{9}", phone)) {
            JOptionPane.showMessageDialog(view, "Phone Number must follow the format '03XXXXXXXXX'!", "Error",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }

        return true;
    }

    void saveBranchManager(String name, String email, String branchCode, String phone, String code) {
        JOptionPane.showMessageDialog(view,
                "Branch Manager successfully added:\n" +
                        "Code: " + code + "\n" +
                        "Name: " + name + "\n" +
                        "Email: " + email + "\n" +
                        "Branch Code: " + branchCode + "\n" +
                        "Phone: " + phone,
                "Success",
                JOptionPane.INFORMATION_MESSAGE);
    }

    void clearFields() {
        view.getNameField().setText("");
        view.getEmailField().setText("");
        view.getBranchCodeField().setText("");
        view.getPhoneField().setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            AddBranchManagerPage page = new AddBranchManagerPage();
            new AddBranchManagerController(page);
            page.setVisible(true);
        });
    }
}
