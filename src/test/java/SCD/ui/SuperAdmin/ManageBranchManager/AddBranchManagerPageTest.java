package SCD.ui.SuperAdmin.ManageBranchManager;

import static org.junit.Assert.*;


import SCD.controllers.SuperAdminControllers.ManagesBranchManagerController.AddBranchManagerController;
import SCD.ui.SuperAdmin.ManageBranchManager.AddBranchManagerPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AddBranchManagerControllerTest {

    private AddBranchManagerController controller;
    private AddBranchManagerPage page;

    @BeforeEach
    void setUp() {
        page = new AddBranchManagerPage();
        controller = new AddBranchManagerController(page);
    }

    @Test
    void validateInputs_AllFieldsValid_ReturnsTrue() {
        String name = "John Doe";
        String email = "john.doe@example.com";
        String branchCode = "BR-1234";

        boolean result = controller.validateInputs(name, email, branchCode);

        assertTrue(result, "Expected validation to pass with all valid inputs.");
    }

    @Test
    void validateInputs_EmptyName_ReturnsFalse() {
        String name = "";
        String email = "john.doe@example.com";
        String branchCode = "BR-1234";

        boolean result = controller.validateInputs(name, email, branchCode);

        assertFalse(result, "Expected validation to fail when name is empty.");
    }

    @Test
    void validateInputs_InvalidEmailFormat_ReturnsFalse() {
        String name = "John Doe";
        String email = "john.doe@com";
        String branchCode = "BR-1234";

        boolean result = controller.validateInputs(name, email, branchCode);

        assertFalse(result, "Expected validation to fail with an invalid email format.");
    }

    @Test
    void validateInputs_EmptyEmail_ReturnsFalse() {
        String name = "John Doe";
        String email = "";
        String branchCode = "BR-1234";

        boolean result = controller.validateInputs(name, email, branchCode);

        assertFalse(result, "Expected validation to fail when email is empty.");
    }

    @Test
    void validateInputs_InvalidBranchCode_ReturnsFalse() {
        String name = "John Doe";
        String email = "john.doe@example.com";
        String branchCode = "1234-BR";

        boolean result = controller.validateInputs(name, email, branchCode);

        assertFalse(result, "Expected validation to fail with an invalid branch code format.");
    }

    @Test
    void validateInputs_EmptyBranchCode_ReturnsFalse() {
        String name = "John Doe";
        String email = "john.doe@example.com";
        String branchCode = "";

        boolean result = controller.validateInputs(name, email, branchCode);

        assertFalse(result, "Expected validation to fail when branch code is empty.");
    }
}
