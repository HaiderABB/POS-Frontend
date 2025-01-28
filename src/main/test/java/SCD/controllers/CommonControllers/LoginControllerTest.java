package SCD.controllers.CommonControllers;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import javax.swing.JOptionPane;

class LoginControllerTest {

    @Test
    void LoginController() {
        LoginController controller = new LoginController("SM-");
        assertNotNull(controller, "LoginController should be initialized.");
    }

    @Test
    void validateAndNavigate() {
        LoginController controller = new LoginController("BM-");

        assertDoesNotThrow(() -> controller.validateAndNavigate(),
                "validateAndNavigate should execute without errors.");
    }

    @Test
    void showChangePasswordPage() {
        LoginController controller = new LoginController("DM-");
        assertDoesNotThrow(() -> controller.showChangePasswordPage("DM-0002"),
                "showChangePasswordPage should execute without errors.");
    }

    @Test
    void navigateToDashboard() {
        LoginController controller = new LoginController("CM-");
        assertDoesNotThrow(() -> controller.navigateToDashboard("CM-0003"),
                "navigateToDashboard should execute without errors.");
    }

    @Test
    void getRoleFromPrefix() {
        LoginController controller = new LoginController("SM-");
        String role = controller.getRoleFromPrefix("BM-0004");
        assertEquals("MANAGER", role,
                "getRoleFromPrefix should return the correct role for the prefix.");
    }
}
