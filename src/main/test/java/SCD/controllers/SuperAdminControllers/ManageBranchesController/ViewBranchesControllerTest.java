package SCD.controllers.SuperAdminControllers.ManageBranchesController;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ViewBranchesControllerTest {

    @Test
    void testLoadBranchData() {
        ViewBranchesController controller = new ViewBranchesController();

        assertDoesNotThrow(() -> {
            controller.main(new String[]{});
        }, "Loading branch data should not throw any exceptions.");
    }

    @Test
    void testMain() {
        assertDoesNotThrow(() -> ViewBranchesController.main(new String[]{}),
                "Main method should execute without errors.");
    }
}