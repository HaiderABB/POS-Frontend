package SCD.ui.BranchManager.ManageDataEntryOperator;

import static org.junit.Assert.*;


import SCD.controllers.BranchManagerControllers.DataEntryOperatorController.AddDataEntryOperatorController;
import SCD.ui.BranchManager.ManageDataEntryOperator.AddDataEntryOperatorPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AddDataEntryOperatorControllerTest {

    private AddDataEntryOperatorController controller;
    private AddDataEntryOperatorPage page;

    @BeforeEach
    void setUp() {
        page = new AddDataEntryOperatorPage();
        controller = new AddDataEntryOperatorController(page);
    }

    @Test
    void validateInputs_AllFieldsValid_ReturnsTrue() {
        String name = "Jane Doe";
        String email = "jane.doe@example.com";
        String branchCode = "BR-1234";

        boolean result = controller.validateInputs(name, email, branchCode);

        assertTrue(result, "Expected validation to pass with valid inputs.");
    }

    @Test
    void validateInputs_EmptyName_ReturnsFalse() {
        String name = "";
        String email = "jane.doe@example.com";
        String branchCode = "BR-1234";

        boolean result = controller.validateInputs(name, email, branchCode);

        assertFalse(result, "Expected validation to fail when the name is empty.");
    }

    @Test
    void validateInputs_InvalidEmail_ReturnsFalse() {
        String name = "Jane Doe";
        String email = "jane.doe@com";
        String branchCode = "BR-1234";

        boolean result = controller.validateInputs(name, email, branchCode);

        assertFalse(result, "Expected validation to fail with an invalid email address.");
    }

    @Test
    void validateInputs_EmptyEmail_ReturnsFalse() {
        String name = "Jane Doe";
        String email = "";
        String branchCode = "BR-1234";

        boolean result = controller.validateInputs(name, email, branchCode);

        assertFalse(result, "Expected validation to fail when the email is empty.");
    }

    @Test
    void validateInputs_InvalidBranchCode_ReturnsFalse() {
        String name = "Jane Doe";
        String email = "jane.doe@example.com";
        String branchCode = "1234-BR";

        boolean result = controller.validateInputs(name, email, branchCode);

        assertFalse(result, "Expected validation to fail with an invalid branch code format.");
    }

    @Test
    void validateInputs_EmptyBranchCode_ReturnsFalse() {
        String name = "Jane Doe";
        String email = "jane.doe@example.com";
        String branchCode = "";

        boolean result = controller.validateInputs(name, email, branchCode);

        assertFalse(result, "Expected validation to fail when the branch code is empty.");
    }
}
