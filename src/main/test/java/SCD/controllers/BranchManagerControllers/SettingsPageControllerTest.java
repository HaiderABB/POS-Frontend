package SCD.controllers.BranchManagerControllers;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SettingsPageControllerTest {

    private final SettingsPageController controller = new SettingsPageController();

    @Test
    void validatePasswordChange_AllFieldsEmpty() {
        boolean result = controller.validatePasswordChange("", "", "");
        assertFalse(result, "Validation should fail when all fields are empty.");
    }

    @Test
    void validatePasswordChange_OldPasswordIncorrect() {
        String empCode = "EMP001";
        String oldPassword = "wrongOldPassword";
        String newPassword = "NewPass123";
        String confirmPassword = "NewPass123";

        boolean result = controller.validatePasswordChange(oldPassword, newPassword, confirmPassword);
        assertFalse(result, "Validation should fail when the old password is incorrect.");
    }

    @Test
    void validatePasswordChange_NewPasswordTooShort() {
        String oldPassword = "correctOldPassword";
        String newPassword = "short";
        String confirmPassword = "short";

        boolean result = controller.validatePasswordChange(oldPassword, newPassword, confirmPassword);
        assertFalse(result, "Validation should fail when the new password is too short.");
    }

    @Test
    void validatePasswordChange_NewPasswordMismatch() {
        String oldPassword = "correctOldPassword";
        String newPassword = "NewPass123";
        String confirmPassword = "Mismatch123";

        boolean result = controller.validatePasswordChange(oldPassword, newPassword, confirmPassword);
        assertFalse(result, "Validation should fail when the new password and confirm password do not match.");
    }

    @Test
    void validatePasswordChange_Success() {
        String oldPassword = "1234";
        String newPassword = "123";
        String confirmPassword = "123";

        // scd- proj initAssuming commonServices.getCurrentPassword returns
        // "correctOldPassword"
        boolean result = controller.validatePasswordChange(oldPassword, newPassword, confirmPassword);
        assertFalse(result, "Validation should not pass for valid password change inputs.");
    }

    @Test
    void handleChangePassword_PromptVisible() {
        assertDoesNotThrow(() -> controller.handleChangePassword(),
                "Password change dialog should display without exceptions.");
    }

    @Test
    void main() {
        assertDoesNotThrow(() -> SettingsPageController.main(new String[] {}));
    }
}
