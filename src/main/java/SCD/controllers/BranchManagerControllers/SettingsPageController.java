package SCD.controllers.BranchManagerControllers;

import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.SwingUtilities;

import SCD.controllers.cache.Cache;
import SCD.model.service.Common.CommonServices;
import SCD.ui.BranchManager.SettingsPage;

public class SettingsPageController {
    private SettingsPage view;
    String emp_code;
    CommonServices commonServices = new CommonServices();

    public SettingsPageController() {
        Cache cache = new Cache();
        emp_code = cache.getCache();
        view = new SettingsPage();
        initController();
        view.setVisible(true);

    }

    public SettingsPageController(SettingsPage view) {
        this.view = view;
        initController();
    }

    private void initController() {
        view.getChangePasswordButton().addActionListener(e -> handleChangePassword());
    }

    void handleChangePassword() {
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

            JOptionPane.showMessageDialog(view, "All fields are required.", "Error", JOptionPane.ERROR_MESSAGE);

            return false;
        }

        if (!oldPassword.equals(commonServices.getCurrentPassword(emp_code))) {

            JOptionPane.showMessageDialog(view, "Old password is incorrect.", "Error", JOptionPane.ERROR_MESSAGE);

            return false;
        }

        if (newPassword.length() < 8) {

            JOptionPane.showMessageDialog(view, "New password must be at least 8 characters long.", "Error",
                    JOptionPane.ERROR_MESSAGE);

            return false;
        }

        if (!newPassword.equals(confirmPassword)) {

            JOptionPane.showMessageDialog(view, "New password and confirm password do not match.", "Error",
                    JOptionPane.ERROR_MESSAGE);

            return false;
        }

        return true;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            SettingsPage page = new SettingsPage();
            new SettingsPageController(page);
            page.setVisible(true);
        });
    }
}
