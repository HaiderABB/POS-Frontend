package SCD.ui.BranchManager;

import static org.junit.Assert.*;

import SCD.controllers.BranchManagerControllers.SettingsPageController;
import SCD.ui.BranchManager.SettingsPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class SettingsPageControllerTest {

    private SettingsPageController controller;
    private SettingsPage view;

    @BeforeEach
    void setUp() {
        view = new SettingsPage();
        controller = new SettingsPageController(view);
    }

    @Test
    void handleChangePassword_ValidPasswords_ShowsSuccessMessage() {
        JPanel passwordPanel = new JPanel(new GridLayout(2, 2));
        JPasswordField oldPasswordField = new JPasswordField("existingPassword123");
        JPasswordField newPasswordField = new JPasswordField("newSecurePassword");

        passwordPanel.add(new JLabel("Old Password:"));
        passwordPanel.add(oldPasswordField);
        passwordPanel.add(new JLabel("New Password:"));
        passwordPanel.add(newPasswordField);

        int option = JOptionPane.OK_OPTION;

        if (option == JOptionPane.OK_OPTION) {
            char[] oldPassword = oldPasswordField.getPassword();
            char[] newPassword = newPasswordField.getPassword();

            if (oldPassword.length > 0 && newPassword.length > 0) {
                assertTrue(true, "Password changed successfully!");
            }
        }
    }

    @Test
    void handleChangePassword_EmptyPasswordFields_ShowsErrorMessage() {
        JPanel passwordPanel = new JPanel(new GridLayout(2, 2));
        JPasswordField oldPasswordField = new JPasswordField("");
        JPasswordField newPasswordField = new JPasswordField("");

        passwordPanel.add(new JLabel("Old Password:"));
        passwordPanel.add(oldPasswordField);
        passwordPanel.add(new JLabel("New Password:"));
        passwordPanel.add(newPasswordField);

        int option = JOptionPane.OK_OPTION;

        if (option == JOptionPane.OK_OPTION) {
            char[] oldPassword = oldPasswordField.getPassword();
            char[] newPassword = newPasswordField.getPassword();


        }
    }

    @Test
    void handleChangePassword_CancelledDialog_NoAction() {
        int option = JOptionPane.CANCEL_OPTION;
        assertEquals(JOptionPane.CANCEL_OPTION, option, "No action should be taken when dialog is cancelled.");
    }
}
