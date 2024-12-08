package SCD.ui.SuperAdmin;

import static org.junit.Assert.*;


import SCD.controllers.SuperAdminControllers.SystemSettingsController;
import SCD.ui.SuperAdmin.SystemSettingsPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SystemSettingsControllerTest {

    private SystemSettingsController controller;
    private SystemSettingsPage page;

    @BeforeEach
    void setUp() {
        page = new SystemSettingsPage();
        controller = new SystemSettingsController(page);
    }

    @Test
    void validatePasswordChange_AllFieldsValid_ReturnsTrue() {
        String oldPassword = "existingPassword123";
        String newPassword = "newSecurePassword";
        String confirmPassword = "newSecurePassword";

        boolean result = controller.validatePasswordChange(oldPassword, newPassword, confirmPassword);

        assertTrue(result, "Expected validation to pass with valid inputs.");
    }

    @Test
    void validatePasswordChange_EmptyFields_ReturnsFalse() {
        String oldPassword = "";
        String newPassword = "newSecurePassword";
        String confirmPassword = "newSecurePassword";

        boolean result = controller.validatePasswordChange(oldPassword, newPassword, confirmPassword);

        assertFalse(result, "Expected validation to fail when the old password field is empty.");
    }

    @Test
    void validatePasswordChange_WrongOldPassword_ReturnsFalse() {
        String oldPassword = "wrongPassword";
        String newPassword = "newSecurePassword";
        String confirmPassword = "newSecurePassword";

        boolean result = controller.validatePasswordChange(oldPassword, newPassword, confirmPassword);

        assertFalse(result, "Expected validation to fail when the old password is incorrect.");
    }

    @Test
    void validatePasswordChange_ShortNewPassword_ReturnsFalse() {
        String oldPassword = "existingPassword123";
        String newPassword = "short";
        String confirmPassword = "short";

        boolean result = controller.validatePasswordChange(oldPassword, newPassword, confirmPassword);

        assertFalse(result, "Expected validation to fail when the new password is shorter than 8 characters.");
    }

    @Test
    void validatePasswordChange_PasswordsDoNotMatch_ReturnsFalse() {
        String oldPassword = "existingPassword123";
        String newPassword = "newSecurePassword";
        String confirmPassword = "differentPassword";

        boolean result = controller.validatePasswordChange(oldPassword, newPassword, confirmPassword);

        assertFalse(result, "Expected validation to fail when the new password and confirm password do not match.");
    }

    @Test
    void validatePasswordChange_EmptyNewPassword_ReturnsFalse() {
        String oldPassword = "existingPassword123";
        String newPassword = "";
        String confirmPassword = "";

        boolean result = controller.validatePasswordChange(oldPassword, newPassword, confirmPassword);

        assertFalse(result, "Expected validation to fail when the new password and confirm password fields are empty.");
    }
}
