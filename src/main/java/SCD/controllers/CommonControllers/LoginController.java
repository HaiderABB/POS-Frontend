package SCD.controllers.CommonControllers;

import SCD.controllers.BranchManagerControllers.BranchManagerDashboardController;
import SCD.controllers.SuperAdminControllers.SuperAdminDashboardController;
import SCD.ui.Cashier.CashierDashboard;
import SCD.ui.Common.LoginPage;
import SCD.ui.DataEntryOperator.DataEntryOperatorDashboard;
import SCD.utils.enums.Roles;

import javax.swing.*;

import java.util.HashMap;
import java.util.Map;

import SCD.model.models.Employee;
import SCD.model.service.Common.CommonServices;

public class LoginController {

    private final LoginPage loginPage;
    private static final Map<String, String> credentials = new HashMap<>();
    CommonServices commonServices;

    static {
        credentials.put("SM-0001", "password1");
        credentials.put("BM-0002", "password2");
        credentials.put("CM-0003", "password3");
        credentials.put("DM-0004", "password4");
    }

    public LoginController(String prefix) {
        this.loginPage = new LoginPage(prefix);
        commonServices = new CommonServices();

        loginPage.getLoginButton().addActionListener(e -> validateAndNavigate());

        SwingUtilities.invokeLater(() -> loginPage.setVisible(true));
    }

    public void validateAndNavigate() {
        String emp_code = loginPage.getUsername();
        String password = loginPage.getPassword();
        String role = getRoleFromPrefix(emp_code);

        Employee employee = commonServices.Login(emp_code, password, role);

        if (employee != null) {
            JOptionPane.showMessageDialog(loginPage, "Login successful for " + role, "Success",
                    JOptionPane.INFORMATION_MESSAGE);
            navigateToDashboardController(role, employee);
        } else {
            JOptionPane.showMessageDialog(loginPage, "Invalid credentials!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private String getRoleFromPrefix(String emp_code) {
        if (emp_code.startsWith("SM-"))
            return "SUPER_ADMIN";
        if (emp_code.startsWith("BM-"))
            return "MANAGER";
        if (emp_code.startsWith("CM-"))
            return "CASHIER";
        if (emp_code.startsWith("DM-"))
            return "DATA_ENTRY_OPERATOR";
        return null;
    }

    private void navigateToDashboardController(String role, Employee employee) {
        JFrame dashboard = null;

        switch (role) {
            case "SUPER_ADMIN":
                new SuperAdminDashboardController(null, employee).showDashboard();
                break;
            case "MANAGER":
                new BranchManagerDashboardController(null).showDashboard();
                break;
            case "CASHIER":
                dashboard = new CashierDashboard();
                break;
            case "DATA_ENTRY_OPERATOR":
                dashboard = new DataEntryOperatorDashboard();
                break;
            default:
                JOptionPane.showMessageDialog(loginPage, "Unknown role: " + role, "Error", JOptionPane.ERROR_MESSAGE);
                return;
        }
        loginPage.dispose();
    }
}
