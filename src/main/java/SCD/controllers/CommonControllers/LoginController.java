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

public class LoginController {

    private final LoginPage loginPage;
    private static final Map<String, String> credentials = new HashMap<>();

    static {
        credentials.put("SM-0001", "password1");
        credentials.put("BM-0002", "password2");
        credentials.put("CM-0003", "password3");
        credentials.put("DM-0004", "password4");
    }

    public LoginController(String prefix) {
        this.loginPage = new LoginPage(prefix);

        loginPage.getLoginButton().addActionListener(e -> validateAndNavigate());

        SwingUtilities.invokeLater(() -> loginPage.setVisible(true));
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

    private void navigateToDashboardController(Roles role) {
        JFrame dashboard = null;

        switch (role) {
            case SUPER_ADMIN:
                new SuperAdminDashboardController();
                break;
            case MANAGER:
                new BranchManagerDashboardController(null).showDashboard();
                break;
            case CASHIER:
                dashboard = new CashierDashboard();
                break;
            case DATA_ENTRY_OPERATOR:
                dashboard = new DataEntryOperatorDashboard();
                break;
            default:
                JOptionPane.showMessageDialog(loginPage, "Unknown role: " + role, "Error", JOptionPane.ERROR_MESSAGE);
                return;
        }
        loginPage.dispose();
    }
}
