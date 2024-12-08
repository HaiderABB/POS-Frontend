package SCD.ui.SuperAdmin.ManageBranches;

import static org.junit.Assert.*;

import SCD.controllers.SuperAdminControllers.ManageBranchesController.DeleteBranchController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;

import static org.junit.jupiter.api.Assertions.*;

class DeleteBranchControllerTest {

    private DeleteBranchController deleteBranchController;
    private DeleteBranchPage deleteBranchPage;

    @BeforeEach
    void setUp() {
        deleteBranchPage = new DeleteBranchPage();
        deleteBranchController = new DeleteBranchController(deleteBranchPage);
    }

    @Test
    void validateBranchCode_ValidBranchCode_ReturnsTrue() {
        String branchCode = "BR-1234";

        boolean result = deleteBranchController.validateBranchCode(branchCode);

        assertTrue(result, "Expected validation to pass with a valid branch code.");
    }

    @Test
    void validateBranchCode_EmptyBranchCode_ReturnsFalse() {
        String branchCode = "";

        boolean result = deleteBranchController.validateBranchCode(branchCode);

        assertFalse(result, "Expected validation to fail when branch code is empty.");
    }

    @Test
    void validateBranchCode_InvalidFormat_ReturnsFalse() {
        String branchCode = "1234-BR";

        boolean result = deleteBranchController.validateBranchCode(branchCode);

        assertFalse(result, "Expected validation to fail with an invalid branch code format.");
    }

    @Test
    void validateBranchCode_IncorrectPattern_ReturnsFalse() {
        String branchCode = "BR-12A4";

        boolean result = deleteBranchController.validateBranchCode(branchCode);

        assertFalse(result, "Expected validation to fail when branch code does not follow the 'BR-XXXX' pattern.");
    }
}
