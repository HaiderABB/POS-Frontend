package SCD.controllers.BranchManagerControllers.DataEntryOperatorController;

import java.util.regex.Pattern;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import SCD.controllers.cache.Cache;
import SCD.model.models.Branch;
import SCD.model.models.Employee;
import SCD.model.service.Common.CommonServices;
import SCD.model.service.Json.AddResponseJSON;
import SCD.model.service.SuperAdminService.SuperAdminService;
import SCD.ui.BranchManager.ManageDataEntryOperator.AddDataEntryOperatorPage;

public class AddDataEntryOperatorController {

    AddDataEntryOperatorPage view;
    CommonServices commonServices = new CommonServices();
    SuperAdminService superAdminService = new SuperAdminService();

    public AddDataEntryOperatorController() {

        view = new AddDataEntryOperatorPage();
        view.setVisible(true);
        initController();

    }

    public AddDataEntryOperatorController(AddDataEntryOperatorPage view) {
        this.view = view;
        initController();
    }

    private void initController() {
        view.getAddButton().addActionListener(e -> handleAddOperator());
    }

    void handleAddOperator() {
        String name = view.getNameField().getText().trim();
        String email = view.getEmailField().getText().trim();
        String phone = view.getPhoneField().getText().trim(); // scd- proj initAdded phone number input

        if (!validateInputs(name, email, phone)) {
            return;
        }

        Cache cache = new Cache();
        String emp_code = cache.getCache();

        boolean res;

        Employee emp = commonServices.getEmployeeByEmployeeCode(emp_code);

        Branch br = superAdminService.getBranchByCode(emp.getBranch().getBranchCode());

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

        Employee employee = new Employee(name, "DATA_ENTRY_OPERATOR", br, phone, email);

        AddResponseJSON json = commonServices.AddEmployee(employee);

        if (json.isSuccess()) {
            JOptionPane.showMessageDialog(view, "Data Entry Operator added successfully!", "Success",
                    JOptionPane.INFORMATION_MESSAGE);
            saveOperator(name, email, emp.getBranch().getBranchCode(), phone, json.getCode());
            clearFields();
        } else {
            JOptionPane.showMessageDialog(view, "Failed to add Data Entry Operator!", "Error",
                    JOptionPane.ERROR_MESSAGE);
        }

    }

    void clearFields() {
        view.getNameField().setText("");
        view.getEmailField().setText("");
        view.getPhoneField().setText("");
    }

    void saveOperator(String name, String email, String branchCode, String phone, String code) {
        JOptionPane.showMessageDialog(view,
                "Data Entry Operator successfully added:\n" +
                        "Code: " + code + "\n" +
                        "Name: " + name + "\n" +
                        "Email: " + email + "\n" +
                        "Branch Code: " + branchCode + "\n" +
                        "Phone: " + phone,
                "Success",
                JOptionPane.INFORMATION_MESSAGE);
    }

    public boolean validateInputs(String name, String email, String phone) {
        if (name.isEmpty() || email.isEmpty() || phone.isEmpty()) {
            JOptionPane.showMessageDialog(view, "Please fill out all fields!", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if (!Pattern.matches("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}", email)) {
            JOptionPane.showMessageDialog(view, "Invalid email format!", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if (!Pattern.matches("03\\d{9}", phone)) { // scd- proj initAdded phone number validation
            JOptionPane.showMessageDialog(view, "Phone Number must follow the format '03XXXXXXXXX'!", "Error",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }

        return true;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            AddDataEntryOperatorPage page = new AddDataEntryOperatorPage();
            new AddDataEntryOperatorController(page);
            page.setVisible(true);
        });
    }
}
