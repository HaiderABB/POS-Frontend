package SCD.controllers.SuperAdminControllers;

import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.SwingUtilities;

import SCD.controllers.cache.Cache;
import SCD.model.service.Common.CommonServices;
import SCD.ui.SuperAdmin.SystemSettingsPage;

public class SystemSettingsController {

    private SystemSettingsPage view;

    private String existingPassword;
    String emp_code;
    CommonServices commonServices = new CommonServices();

    public SystemSettingsController() {
        Cache cache = new Cache();
        emp_code = cache.getCache();
        view = new SystemSettingsPage();
        initController();
        view.setVisible(true);

    }

    public SystemSettingsController(SystemSettingsPage view) {
        Cache cache = new Cache();
        emp_code = cache.getCache();
        this.view = view;
        initController();
    }

    void initController() {
        view.getChangePasswordButton().addActionListener(e -> handlePasswordChange());
    }

    void handlePasswordChange() {
        JPanel passwordPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        passwordPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel oldPasswordLabel = new JLabel("Old Password:");
        JPasswordField oldPasswordField = new JPasswordField(15);
        JLabel newPasswordLabel = new JLabel("New Password:");
        JPasswordField newPasswordField = new JPasswordField(15);
        JLabel confirmPasswordLabel = new JLabel("Confirm Password:");
        JPasswordField confirmPasswordField = new JPasswordField(15);

        passwordPanel.add(oldPasswordLabel);
        passwordPanel.add(oldPasswordField);
        passwordPanel.add(newPasswordLabel);
        passwordPanel.add(newPasswordField);
        passwordPanel.add(confirmPasswordLabel);
        passwordPanel.add(confirmPasswordField);

        int option = JOptionPane.showConfirmDialog(
                view,
                passwordPanel,
                "Change Password",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE);

        if (option == JOptionPane.OK_OPTION) {
            String oldPassword = new String(oldPasswordField.getPassword());
            String newPassword = new String(newPasswordField.getPassword());
            String confirmPassword = new String(confirmPasswordField.getPassword());

            if (validatePasswordChange(oldPassword, newPassword, confirmPassword)) {
                System.out.println("in validation");

                if (commonServices.update_password(emp_code, newPassword)) {
                    JOptionPane.showMessageDialog(view, "Password changed successfully!", "Success",
                            JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(view, "Password not changed!", "Error",
                            JOptionPane.ERROR_MESSAGE);
                }

            }
        }
    }

    public boolean validatePasswordChange(String oldPassword, String newPassword, String confirmPassword) {
        if (oldPassword.isEmpty() || newPassword.isEmpty() || confirmPassword.isEmpty()) {
            view.showErrorMessage("All fields are required.");
            return false;
        }

        if (!oldPassword.equals(commonServices.getCurrentPassword(emp_code))) {
            view.showErrorMessage("Old password is incorrect.");
            return false;
        }

        if (newPassword.length() < 8) {
            view.showErrorMessage("New password must be at least 8 characters long.");
            return false;
        }

        if (!newPassword.equals(confirmPassword)) {
            view.showErrorMessage("New password and confirm password do not match.");
            return false;
        }

        return true;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            SystemSettingsPage page = new SystemSettingsPage();
            new SystemSettingsController(page);
            page.setVisible(true);
        });
    }

    public void showPage() {
        new SystemSettingsPage().setVisible(true);
    }
}
