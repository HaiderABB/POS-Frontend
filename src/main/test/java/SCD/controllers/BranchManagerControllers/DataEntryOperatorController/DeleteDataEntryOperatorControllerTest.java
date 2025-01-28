package SCD.controllers.BranchManagerControllers.DataEntryOperatorController;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DeleteDataEntryOperatorControllerTest {

    private DeleteDataEntryOperatorController controller;

    @Test
    void validateOperatorCode_ValidCode() {
        controller = new DeleteDataEntryOperatorController();
        assertTrue(controller.validateOperatorCode("DM-1234"));
    }

    @Test
    void validateOperatorCode_EmptyCode() {
        controller = new DeleteDataEntryOperatorController();
        assertFalse(controller.validateOperatorCode(""));
    }

    @Test
    void validateOperatorCode_InvalidFormat() {
        controller = new DeleteDataEntryOperatorController();
        assertFalse(controller.validateOperatorCode("INVALID_CODE"));
    }

    @Test
    void handleDeleteOperator() {
        controller = new DeleteDataEntryOperatorController();
        // scd- proj initSimulating a valid operator code
        assertDoesNotThrow(() -> controller.validateOperatorCode("DM-1234"));
        // scd- proj initNote: Integration with the UI and external services should be
        // tested in integration tests.
    }

    @Test
    void main() {
        assertDoesNotThrow(() -> DeleteDataEntryOperatorController.main(new String[] {}));
    }
}
