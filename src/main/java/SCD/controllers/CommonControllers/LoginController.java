package SCD.controllers.CommonControllers;


import SCD.controllers.BranchManagerControllers.BranchManagerDashboardController;
import SCD.controllers.SuperAdminControllers.SuperAdminDashboardController;
import SCD.ui.Cashier.CashierDashboard;
import SCD.ui.Common.LoginPage;
import SCD.ui.DataEntryOperator.DataEntryOperatorDashboard;

import javax.swing.*;
import java.util.HashMap;
import java.util.Map;

public class LoginController {

    private final LoginPage loginPage;

    private static final Map<String, String> credentials = new HashMap<>();

    static {
        credentials.put("SM-0001", "password1");
        credentials.put("BM-0002", "password2");
        credentials.put("CM-0003", "password3");
        credentials.put("DM-0004", "password4");
    }

    public LoginController(LoginPage loginPage) {
        this.loginPage = loginPage;
    }

    public void validateAndNavigate() {
        String username = loginPage.getUsername();
        String password = loginPage.getPassword();

        if (credentials.containsKey(username) && credentials.get(username).equals(password)) {
            String role = getRoleFromPrefix(username);
            JOptionPane.showMessageDialog(loginPage, "Login successful for " + role, "Success", JOptionPane.INFORMATION_MESSAGE);
            navigateToDashboardController(role);
        } else {
            JOptionPane.showMessageDialog(loginPage, "Invalid credentials!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private String getRoleFromPrefix(String username) {
        if (username.startsWith("SM-")) return "Super Admin";
        if (username.startsWith("BM-")) return "Branch Manager";
        if (username.startsWith("CM-")) return "Cashier";
        if (username.startsWith("DM-")) return "Data Entry Operator";
        return "Unknown Role";
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
