package SCD.controllers.CommonControllers;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import javax.swing.JFrame;

class MainMenuControllerTest {

    @Test
    void testHandleRoleButtonClick_ValidRole() {
        JFrame mockMainMenu = new JFrame();
        String prefix = "BM-";
        String role = "MANAGER";

        assertDoesNotThrow(() -> MainMenuController.handleRoleButtonClick(mockMainMenu, prefix, role));
    }

    @Test
    void testHandleRoleButtonClick_InvalidRole() {
        JFrame mockMainMenu = new JFrame();
        String prefix = "XX-";
        String role = "UNKNOWN";

        assertDoesNotThrow(() -> MainMenuController.handleRoleButtonClick(mockMainMenu, prefix, role));
    }

    @Test
    void testMainMenuVisibility() {
        MainMenuController controller = new MainMenuController();
        assertNotNull(controller, "MainMenuController instance should be created successfully.");
    }

    @Test
    void testMainMethod() {
        assertDoesNotThrow(() -> MainMenuController.main(new String[]{}));
    }
}
