package SCD.controllers.BranchManagerControllers.DataEntryOperatorController;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AddDataEntryOperatorControllerTest {

    private AddDataEntryOperatorController controller;

    @BeforeEach
    void setUp() {
        controller = new AddDataEntryOperatorController();
    }

    @Test
    void validateInputs() {
        assertTrue(controller.validateInputs("John Doe", "john.doe@example.com", "03123456789"));
        assertFalse(controller.validateInputs("", "john.doe@example.com", "03123456789"));
        assertFalse(controller.validateInputs("John Doe", "invalid-email", "03123456789"));
        assertFalse(controller.validateInputs("John Doe", "john.doe@example.com", "01234567890"));
        assertFalse(controller.validateInputs("", "", ""));
    }

    @Test
    void handleAddOperator() {
        // scd- proj initSimulate valid input
        assertDoesNotThrow(() -> {
            controller.validateInputs("John Doe", "john.doe@example.com", "03123456789");
            controller.handleAddOperator();
        });

        // scd- proj initSimulate invalid input
        assertDoesNotThrow(() -> {
            controller.validateInputs("", "invalid-email", "01234567890");
            controller.handleAddOperator();
        });
    }

    @Test
    void clearFields() {
        // scd- proj initSimulate clearing fields
        controller.clearFields();
        assertEquals("", controller.view.getNameField().getText());
        assertEquals("", controller.view.getEmailField().getText());
        assertEquals("", controller.view.getPhoneField().getText());
    }

    @Test
    void saveOperator() {
        assertDoesNotThrow(() -> {
            controller.saveOperator("John Doe", "john.doe@example.com", "BR-0001", "03123456789", "DM-1234");
        });
    }

    @Test
    void main() {
        assertDoesNotThrow(() -> AddDataEntryOperatorController.main(new String[] {}));
    }
}
