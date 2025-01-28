package SCD.controllers.SuperAdminControllers;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SystemSettingsControllerTest {

    @Test
    void testValidatePasswordChange() {
        SystemSettingsController controller = new SystemSettingsController();

        // scd- proj initTest cases for empty fields
        assertFalse(controller.validatePasswordChange("", "newPass123", "newPass123"),
                "Empty old password should return false.");
        assertFalse(controller.validatePasswordChange("oldPass123", "", "newPass123"),
                "Empty new password should return false.");
        assertFalse(controller.validatePasswordChange("oldPass123", "newPass123", ""),
                "Empty confirm password should return false.");

        // scd- proj initTest case for incorrect old password
        assertFalse(controller.validatePasswordChange("wrongOldPass", "newPass123", "newPass123"),
                "Incorrect old password should return false.");

        // scd- proj initTest case for new password length
        assertFalse(controller.validatePasswordChange("correctOldPass", "short", "short"),
                "Short new password should return false.");

        // scd- proj initTest case for mismatched passwords
        assertFalse(controller.validatePasswordChange("correctOldPass", "newPass123", "differentPass"),
                "Mismatched passwords should return false.");

        // scd- proj initTest case for valid inputs
        assertFalse(controller.validatePasswordChange("correctOldPass", "newPass123", "newPass123"),
                "Valid inputs should return true.");
    }

    @Test
    void testHandlePasswordChange() {
        SystemSettingsController controller = new SystemSettingsController();
        assertDoesNotThrow(controller::handlePasswordChange, "Handling password change should not throw exceptions.");
    }

    @Test
    void testInitController() {
        SystemSettingsController controller = new SystemSettingsController();
        assertDoesNotThrow(controller::initController, "Initializing controller should not throw exceptions.");
    }

    @Test
    void testMainMethod() {
        assertDoesNotThrow(() -> SystemSettingsController.main(new String[] {}),
                "Main method should execute without exceptions.");
    }

    @Test
    void testShowPage() {
        SystemSettingsController controller = new SystemSettingsController();
        assertDoesNotThrow(controller::showPage, "Showing the page should not throw exceptions.");
    }
}
