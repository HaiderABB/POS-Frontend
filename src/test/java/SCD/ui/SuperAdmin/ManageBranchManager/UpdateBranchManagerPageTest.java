package SCD.ui.SuperAdmin.ManageBranchManager;

import static org.junit.Assert.*;


import SCD.controllers.SuperAdminControllers.ManagesBranchManagerController.UpdateBranchManagerController;
import SCD.ui.SuperAdmin.ManageBranchManager.UpdateBranchManagerPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UpdateBranchManagerControllerTest {

    private UpdateBranchManagerController controller;
    private UpdateBranchManagerPage page;

    @BeforeEach
    void setUp() {
        page = new UpdateBranchManagerPage();
        controller = new UpdateBranchManagerController(page);
    }

    @Test
    void validateManagerCode_ValidManagerCode_ReturnsTrue() {
        String managerCode = "BM-1234";

        boolean result = controller.validateManagerCode(managerCode);

        assertTrue(result, "Expected validation to pass with a valid manager code.");
    }

    @Test
    void validateManagerCode_InvalidManagerCode_ReturnsFalse() {
        String managerCode = "1234-BM";

        boolean result = controller.validateManagerCode(managerCode);

        assertFalse(result, "Expected validation to fail with an invalid manager code format.");
    }

    @Test
    void validateManagerCode_EmptyManagerCode_ReturnsFalse() {
        String managerCode = "";

        boolean result = controller.validateManagerCode(managerCode);

        assertFalse(result, "Expected validation to fail when manager code is empty.");
    }

    @Test
    void validateField_ValidName_ReturnsTrue() {
        String field = "Name";
        String value = "John Doe";

        boolean result = controller.validateField(field, value);

        assertTrue(result, "Expected validation to pass with a valid name.");
    }

    @Test
    void validateField_InvalidName_ReturnsFalse() {
        String field = "Name";
        String value = "John123";

        boolean result = controller.validateField(field, value);

        assertFalse(result, "Expected validation to fail with an invalid name.");
    }

    @Test
    void validateField_ValidEmail_ReturnsTrue() {
        String field = "Email";
        String value = "john.doe@example.com";

        boolean result = controller.validateField(field, value);

        assertTrue(result, "Expected validation to pass with a valid email.");
    }

    @Test
    void validateField_InvalidEmail_ReturnsFalse() {
        String field = "Email";
        String value = "john.doe@com";

        boolean result = controller.validateField(field, value);

        assertFalse(result, "Expected validation to fail with an invalid email format.");
    }

    @Test
    void validateField_ValidBranchCode_ReturnsTrue() {
        String field = "Branch Code";
        String value = "BR-1234";

        boolean result = controller.validateField(field, value);

        assertTrue(result, "Expected validation to pass with a valid branch code.");
    }

    @Test
    void validateField_InvalidBranchCode_ReturnsFalse() {
        String field = "Branch Code";
        String value = "1234-BR";

        boolean result = controller.validateField(field, value);

        assertFalse(result, "Expected validation to fail with an invalid branch code format.");
    }

    @Test
    void validateField_UnknownField_ReturnsFalse() {
        String field = "Unknown";
        String value = "Test Value";

        boolean result = controller.validateField(field, value);

        assertFalse(result, "Expected validation to fail with an unknown field.");
    }
}
