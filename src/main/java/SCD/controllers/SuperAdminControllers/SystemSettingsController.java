package SCD.controllers.SuperAdminControllers;

import SCD.ui.SuperAdmin.SystemSettingsPage;

import javax.swing.*;
import java.awt.*;

public class SystemSettingsController {
    private  SystemSettingsPage view;
    private final String existingPassword = "existingPassword123";

    public SystemSettingsController() {
        SwingUtilities.invokeLater(() -> {
            SystemSettingsPage page = new SystemSettingsPage();

            page.setVisible(true);
        });
    }
    public SystemSettingsController(SystemSettingsPage view) {
        this.view = view;
        initController();
    }

    private void initController() {
        view.getChangePasswordButton().addActionListener(e -> handlePasswordChange());
    }

    private void handlePasswordChange() {
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
                JOptionPane.PLAIN_MESSAGE
        );

        if (option == JOptionPane.OK_OPTION) {
            String oldPassword = new String(oldPasswordField.getPassword());
            String newPassword = new String(newPasswordField.getPassword());
            String confirmPassword = new String(confirmPasswordField.getPassword());

            if (validatePasswordChange(oldPassword, newPassword, confirmPassword)) {
                view.showSuccessMessage("Password changed successfully!");
            }
        }
    }

    public boolean validatePasswordChange(String oldPassword, String newPassword, String confirmPassword) {
        if (oldPassword.isEmpty() || newPassword.isEmpty() || confirmPassword.isEmpty()) {
            view.showErrorMessage("All fields are required.");
            return false;
        }

        if (!oldPassword.equals(existingPassword)) {
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
