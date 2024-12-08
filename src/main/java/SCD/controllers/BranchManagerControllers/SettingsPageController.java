package SCD.controllers.BranchManagerControllers;

import SCD.ui.BranchManager.SettingsPage;

import javax.swing.*;
import java.awt.*;

public class SettingsPageController {
    private final SettingsPage view;

    public SettingsPageController(SettingsPage view) {
        this.view = view;
        initController();
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
                JOptionPane.PLAIN_MESSAGE
        );

        if (option == JOptionPane.OK_OPTION) {
            char[] oldPassword = oldPasswordField.getPassword();
            char[] newPassword = newPasswordField.getPassword();

            if (oldPassword.length > 0 && newPassword.length > 0) {
                JOptionPane.showMessageDialog(view, "Password changed successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(view, "Password fields cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            SettingsPage page = new SettingsPage();
            new SettingsPageController(page);
            page.setVisible(true);
        });
    }
}
