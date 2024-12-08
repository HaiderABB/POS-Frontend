package SCD.ui.SuperAdmin.ManageBranches;

import static org.junit.Assert.*;


import SCD.controllers.SuperAdminControllers.ManageBranchesController.UpdateBranchController;
import SCD.ui.SuperAdmin.ManageBranches.UpdateBranchPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UpdateBranchControllerTest {

    private UpdateBranchController updateBranchController;
    private UpdateBranchPage updateBranchPage;

    @BeforeEach
    void setUp() {
        updateBranchPage = new UpdateBranchPage();
        updateBranchController = new UpdateBranchController(updateBranchPage);
    }

    @Test
    void validateBranchCode_ValidBranchCode_ReturnsTrue() {
        String branchCode = "BR-1234";

        boolean result = updateBranchController.validateBranchCode(branchCode);

        assertTrue(result, "Expected validation to pass with a valid branch code.");
    }

    @Test
    void validateBranchCode_InvalidBranchCode_ReturnsFalse() {
        String branchCode = "1234-BR";

        boolean result = updateBranchController.validateBranchCode(branchCode);

        assertFalse(result, "Expected validation to fail with an invalid branch code.");
    }

    @Test
    void validateBranchCode_EmptyBranchCode_ReturnsFalse() {
        String branchCode = "";

        boolean result = updateBranchController.validateBranchCode(branchCode);

        assertFalse(result, "Expected validation to fail when branch code is empty.");
    }

    @Test
    void validateField_ValidName_ReturnsTrue() {
        String field = "Name";
        String value = "Main Branch";

        boolean result = updateBranchController.validateField(field, value);

        assertTrue(result, "Expected validation to pass with a valid name.");
    }

    @Test
    void validateField_InvalidName_ReturnsFalse() {
        String field = "Name";
        String value = "Branch123";

        boolean result = updateBranchController.validateField(field, value);

        assertFalse(result, "Expected validation to fail with an invalid name.");
    }

    @Test
    void validateField_ValidCity_ReturnsTrue() {
        String field = "City";
        String value = "New York";

        boolean result = updateBranchController.validateField(field, value);

        assertTrue(result, "Expected validation to pass with a valid city.");
    }

    @Test
    void validateField_InvalidCity_ReturnsFalse() {
        String field = "City";
        String value = "City123";

        boolean result = updateBranchController.validateField(field, value);

        assertFalse(result, "Expected validation to fail with an invalid city.");
    }

    @Test
    void validateField_ValidPhone_ReturnsTrue() {
        String field = "Phone";
        String value = "03211234567";

        boolean result = updateBranchController.validateField(field, value);

        assertTrue(result, "Expected validation to pass with a valid phone.");
    }

    @Test
    void validateField_InvalidPhone_ReturnsFalse() {
        String field = "Phone";
        String value = "123456";

        boolean result = updateBranchController.validateField(field, value);

        assertFalse(result, "Expected validation to fail with an invalid phone.");
    }

    @Test
    void validateField_ValidAddress_ReturnsTrue() {
        String field = "Address";
        String value = "123 Main Street, NY";

        boolean result = updateBranchController.validateField(field, value);

        assertTrue(result, "Expected validation to pass with a valid address.");
    }

    @Test
    void validateField_InvalidAddress_ReturnsFalse() {
        String field = "Address";
        String value = "Short";

        boolean result = updateBranchController.validateField(field, value);

        assertFalse(result, "Expected validation to fail with an invalid address.");
    }

    @Test
    void validateField_UnknownField_ReturnsFalse() {
        String field = "Unknown";
        String value = "Test Value";

        boolean result = updateBranchController.validateField(field, value);

        assertFalse(result, "Expected validation to fail with an unknown field.");
    }
}
