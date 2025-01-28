package SCD.controllers.SuperAdminControllers.ManagesBranchManagerController;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ViewBranchManagersControllerTest {

    @Test
    void testPopulateTable() {
        ViewBranchManagersController controller = new ViewBranchManagersController();

        assertDoesNotThrow(() -> {
            // scd- proj initSimulating the populateTable method and ensuring no exceptions
            // occur
            controller.main(new String[] {});
        }, "Populating the table should not throw any exceptions.");
    }

    @Test
    void testMain() {
        assertDoesNotThrow(() -> ViewBranchManagersController.main(new String[] {}),
                "Main method should execute without errors.");
    }
}
