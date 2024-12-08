package SCD.ui.BranchManager.ManageDataEntryOperator;

import static org.junit.Assert.*;


import SCD.controllers.BranchManagerControllers.DataEntryOperatorController.DeleteDataEntryOperatorController;
import SCD.ui.BranchManager.ManageDataEntryOperator.DeleteDataEntryOperatorPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DeleteDataEntryOperatorControllerTest {

    private DeleteDataEntryOperatorController controller;
    private DeleteDataEntryOperatorPage page;

    @BeforeEach
    void setUp() {
        page = new DeleteDataEntryOperatorPage();
        controller = new DeleteDataEntryOperatorController(page);
    }

    @Test
    void validateOperatorCode_ValidOperatorCode_ReturnsTrue() {
        String operatorCode = "DM-1234";

        boolean result = controller.validateOperatorCode(operatorCode);

        assertTrue(result, "Expected validation to pass with a valid operator code.");
    }

    @Test
    void validateOperatorCode_InvalidFormat_ReturnsFalse() {
        String operatorCode = "1234-DM";

        boolean result = controller.validateOperatorCode(operatorCode);

        assertFalse(result, "Expected validation to fail with an invalid operator code format.");
    }

    @Test
    void validateOperatorCode_EmptyOperatorCode_ReturnsFalse() {
        String operatorCode = "";

        boolean result = controller.validateOperatorCode(operatorCode);

        assertFalse(result, "Expected validation to fail when operator code is empty.");
    }

    @Test
    void validateOperatorCode_IncorrectPattern_ReturnsFalse() {
        String operatorCode = "DM-12A4";

        boolean result = controller.validateOperatorCode(operatorCode);

        assertFalse(result, "Expected validation to fail when operator code does not follow the 'DM-XXXX' pattern.");
    }
}
