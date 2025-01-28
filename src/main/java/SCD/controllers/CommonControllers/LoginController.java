package SCD.controllers.CommonControllers;

import javax.swing.JOptionPane;

import SCD.controllers.BranchManagerControllers.BranchManagerDashboardController;
import SCD.controllers.CashierControllers.CashierDashboardController;
import SCD.controllers.DataEntryOperatorControllers.DataEntryOperatorController;
import SCD.controllers.SuperAdminControllers.SuperAdminDashboardController;
import SCD.controllers.cache.Cache;
import SCD.model.models.Employee;
import SCD.model.service.Common.CommonServices;
import SCD.ui.Common.ChangePasswordPage;
import SCD.ui.Common.LoginPage;

public class LoginController {

    final LoginPage loginPage;

    CommonServices commonServices = new CommonServices();
    String userRole;

    public LoginController(String prefix) {
        this.loginPage = new LoginPage(prefix);
        userRole = getRoleFromPrefix(prefix);
        loginPage.getLoginButton().addActionListener(e -> validateAndNavigate());
        loginPage.setVisible(true);
    }

    public void validateAndNavigate() {
        String empCode = loginPage.getUsername();
        String password = loginPage.getPassword();

        Employee emp = commonServices.Login(empCode, password, userRole);

        if (emp != null) {
            if (emp.isFirstLogin()) {
                showChangePasswordPage(empCode);
            } else {

                Cache cache = new Cache();
                cache.clearCache();
                cache.setCache(empCode);
                JOptionPane.showMessageDialog(loginPage, "Login successful", "Success",
                        JOptionPane.INFORMATION_MESSAGE);
                navigateToDashboard(empCode);
                loginPage.dispose();
            }
        } else {
            JOptionPane.showMessageDialog(loginPage, "Invalid credentials!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    void showChangePasswordPage(String empCode) {
        ChangePasswordPage changePasswordPage = new ChangePasswordPage();
        changePasswordPage.getChangePasswordButton().addActionListener(e -> {
            String newPassword = changePasswordPage.getNewPassword();
            String confirmPassword = changePasswordPage.getConfirmPassword();

            if (newPassword.isEmpty() || confirmPassword.isEmpty()) {
                JOptionPane.showMessageDialog(changePasswordPage, "Fields cannot be empty!", "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (!newPassword.equals(confirmPassword)) {
                JOptionPane.showMessageDialog(changePasswordPage, "Passwords do not match!", "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            boolean res = commonServices.update_password(empCode, newPassword)
                    && commonServices.setFirstLoginStatus(empCode);
            if (res) {
                JOptionPane.showMessageDialog(changePasswordPage, "Password changed successfully!", "Success",
                        JOptionPane.INFORMATION_MESSAGE);
                changePasswordPage.dispose();
                Cache cache = new Cache();
                cache.clearCache();
                cache.setCache(empCode);
                navigateToDashboard(empCode);
            } else {
                JOptionPane.showMessageDialog(changePasswordPage, "Failed to change password!", "Error",
                        JOptionPane.ERROR_MESSAGE);

            }

        });
        changePasswordPage.setVisible(true);
    }

    void navigateToDashboard(String empCode) {
        String role = getRoleFromPrefix(empCode);

        switch (role) {
            case "SUPER_ADMIN":
                new SuperAdminDashboardController().showDashboard();
                break;
            case "MANAGER":
                new BranchManagerDashboardController().showDashboard();
                break;
            case "CASHIER":
                CashierDashboardController cn = new CashierDashboardController();
                break;
            case "DATA_ENTRY_OPERATOR":
                DataEntryOperatorController deo = new DataEntryOperatorController();
                break;
            default:
                JOptionPane.showMessageDialog(loginPage, "Unknown role: " + role, "Error", JOptionPane.ERROR_MESSAGE);
                break;
        }
    }

    String getRoleFromPrefix(String empCode) {
        if (empCode.startsWith("SM-"))
            return "SUPER_ADMIN";
        if (empCode.startsWith("BM-"))
            return "MANAGER";
        if (empCode.startsWith("CM-"))
            return "CASHIER";
        if (empCode.startsWith("DM-"))
            return "DATA_ENTRY_OPERATOR";
        return null;
    }
}
