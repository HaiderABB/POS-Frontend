package SCD.controllers.SuperAdminControllers.ManagesBranchManagerController;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class DeleteBranchManagerControllerTest {

    @Test
    void testValidateManagerCode() {
        DeleteBranchManagerController controller = new DeleteBranchManagerController();

        // scd- proj initValid manager code
        assertTrue(controller.validateManagerCode("BM-1234"), "Valid manager code should return true.");

        // scd- proj initEmpty manager code
        assertFalse(controller.validateManagerCode(""), "Empty manager code should return false.");

        // scd- proj initInvalid format
        assertFalse(controller.validateManagerCode("INVALID"), "Invalid manager code should return false.");
    }

    @Test
    void testDeleteBranchManager() {
        DeleteBranchManagerController controller = new DeleteBranchManagerController();

        assertDoesNotThrow(() -> {
            controller.deleteBranchManager("BM-1234");
        }, "Deleting a branch manager should not throw any exceptions.");
    }

    @Test
    void testMain() {
        assertDoesNotThrow(() -> DeleteBranchManagerController.main(new String[] {}),
                "Main method should execute without errors.");
    }
}