package SCD.controllers.CommonControllers;

import java.util.HashMap;
import java.util.Map;

import javax.management.relation.Role;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import SCD.controllers.BranchManagerControllers.BranchManagerDashboardController;
import SCD.controllers.SuperAdminControllers.SuperAdminDashboardController;
import SCD.model.service.Common.CommonServices;
import SCD.ui.Cashier.CashierDashboard;
import SCD.ui.Common.LoginPage;
import SCD.ui.DataEntryOperator.DataEntryOperatorDashboard;

import SCD.utils.enums.Roles;

public class LoginController {

    private final LoginPage loginPage;
    CommonServices commonServices;

    private static final Map<String, String> credentials = new HashMap<>();

    static {
        credentials.put("SM-0001", "password1");
        credentials.put("BM-0002", "password2");
        credentials.put("CM-0003", "password3");
        credentials.put("DM-0004", "password4");
    }

    public LoginController(LoginPage loginPage) {
        this.loginPage = loginPage;
        commonServices = new CommonServices();
    }

    public void validateAndNavigate() {
        String username = loginPage.getUsername();
        String password = loginPage.getPassword();

        if (credentials.containsKey(username) && credentials.get(username).equals(password)) {
            Roles role = getRoleFromPrefix(username);
            JOptionPane.showMessageDialog(loginPage, "Login successful for " + role, "Success",
                    JOptionPane.INFORMATION_MESSAGE);
            navigateToDashboardController(role);
        } else {
            JOptionPane.showMessageDialog(loginPage, "Invalid credentials!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private Roles getRoleFromPrefix(String username) {
        if (username.startsWith("SM-"))
            return Roles.SUPER_ADMIN;
        if (username.startsWith("BM-"))
            return Roles.MANAGER;
        if (username.startsWith("CM-"))
            return Roles.CASHIER;
        if (username.startsWith("DM-"))
            return Roles.DATA_ENTRY_OPERATOR;
        return null;
    }

    private void navigateToDashboardController(String role) {
        JFrame dashboard = null;
        switch (role) {
            case "Super Admin":
                new SuperAdminDashboardController(null).showDashboard();
                break;
            case "Branch Manager":
                new BranchManagerDashboardController(null).showDashboard();
                break;
            case "Cashier":
                dashboard = new CashierDashboard();
                break;
            case "Data Entry Operator":
                dashboard = new DataEntryOperatorDashboard();
                break;
            default:
                JOptionPane.showMessageDialog(loginPage, "Unknown role: " + role, "Error", JOptionPane.ERROR_MESSAGE);
                return;
        }
        loginPage.dispose(); // Close the login page
    }
}
