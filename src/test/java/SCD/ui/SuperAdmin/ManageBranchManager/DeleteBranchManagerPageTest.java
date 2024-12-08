package SCD.ui.SuperAdmin.ManageBranchManager;

import static org.junit.Assert.*;


import SCD.controllers.SuperAdminControllers.ManagesBranchManagerController.DeleteBranchManagerController;
import SCD.ui.SuperAdmin.ManageBranchManager.DeleteBranchManagerPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DeleteBranchManagerControllerTest {

    private DeleteBranchManagerController controller;
    private DeleteBranchManagerPage page;

    @BeforeEach
    void setUp() {
        page = new DeleteBranchManagerPage();
        controller = new DeleteBranchManagerController(page);
    }

    @Test
    void validateManagerCode_ValidManagerCode_ReturnsTrue() {
        String managerCode = "BM-1234";

        boolean result = controller.validateManagerCode(managerCode);

        assertTrue(result, "Expected validation to pass with a valid manager code.");
    }

    @Test
    void validateManagerCode_EmptyManagerCode_ReturnsFalse() {
        String managerCode = "";

        boolean result = controller.validateManagerCode(managerCode);

        assertFalse(result, "Expected validation to fail when manager code is empty.");
    }

    @Test
    void validateManagerCode_InvalidFormat_ReturnsFalse() {
        String managerCode = "1234-BM";

        boolean result = controller.validateManagerCode(managerCode);

        assertFalse(result, "Expected validation to fail with an invalid manager code format.");
    }

    @Test
    void validateManagerCode_IncorrectPattern_ReturnsFalse() {
        String managerCode = "BM-12A4";

        boolean result = controller.validateManagerCode(managerCode);

        assertFalse(result, "Expected validation to fail when manager code does not follow the 'BM-XXXX' pattern.");
    }
}
