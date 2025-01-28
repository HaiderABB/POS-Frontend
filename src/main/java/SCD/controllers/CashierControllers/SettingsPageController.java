package SCD.controllers.CashierControllers;

import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

import SCD.controllers.cache.Cache;
import SCD.model.service.Common.CommonServices;
import SCD.ui.Cashier.SettingsPage;

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

    private void initController() {
        view.getChangePasswordButton().addActionListener(e -> handleChangePassword());
    }

    private void handleChangePassword() {
        JPanel passwordPanel = new JPanel(new GridLayout(2, 2));
        JPasswordField oldPasswordField = new JPasswordField(15);
        JPasswordField newPasswordField = new JPasswordField(15);

        passwordPanel.add(new JLabel("Old Password:"));
        passwordPanel.add(oldPasswordField);
        passwordPanel.add(new JLabel("New Password:"));
        passwordPanel.add(newPasswordField);

        int option = JOptionPane.showConfirmDialog(
                null,
                passwordPanel,
                "Enter Old and New Password",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE);

        if (option == JOptionPane.OK_OPTION) {
            char[] oldPassword = oldPasswordField.getPassword();
            char[] newPassword = newPasswordField.getPassword();

            if (oldPassword.length > 0 && newPassword.length > 0) {
                boolean res = commonServices.update_password(emp_code, emp_code);
                if (res) {
                    JOptionPane.showMessageDialog(view, "Password changed successfully!", "Success",
                            JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(view, "Failed to change password!", "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(view, "Password fields cannot be empty.", "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public static void main(String[] args) {

    }
}
