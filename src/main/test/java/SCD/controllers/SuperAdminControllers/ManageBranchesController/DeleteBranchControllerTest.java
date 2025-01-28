package SCD.controllers.SuperAdminControllers.ManageBranchesController;

import SCD.ui.SuperAdmin.ManageBranches.DeleteBranchPage;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class DeleteBranchControllerTest {

    @Test
    void testValidateBranchCode() {
        DeleteBranchController controller = new DeleteBranchController(new DeleteBranchPage());

        assertTrue(controller.validateBranchCode("BR-0001"));

        assertFalse(controller.validateBranchCode(""));

        assertFalse(controller.validateBranchCode("BR1234"));

        assertFalse(controller.validateBranchCode("1234-BR"));
    }

    @Test
    void testDeleteBranch() {
        DeleteBranchController controller = new DeleteBranchController(new DeleteBranchPage());

        assertDoesNotThrow(() -> {
            controller.deleteBranch("BR-0002");
        });
    }

    @Test
    void testMain() {
        assertDoesNotThrow(() -> DeleteBranchController.main(new String[]{}));
    }
}
