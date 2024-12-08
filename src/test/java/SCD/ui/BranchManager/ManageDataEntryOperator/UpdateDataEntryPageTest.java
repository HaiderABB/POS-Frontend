package SCD.ui.BranchManager.ManageDataEntryOperator;

import static org.junit.Assert.*;


import SCD.controllers.BranchManagerControllers.DataEntryOperatorController.UpdateDataEntryController;
import SCD.ui.BranchManager.ManageDataEntryOperator.UpdateDataEntryPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UpdateDataEntryControllerTest {

    private UpdateDataEntryController controller;
    private UpdateDataEntryPage page;

    @BeforeEach
    void setUp() {
        page = new UpdateDataEntryPage();
        controller = new UpdateDataEntryController(page);
    }

    @Test
    void validateDataEntryCode_ValidCode_ReturnsTrue() {
        String dataEntryCode = "DM-1234";

        boolean result = controller.validateDataEntryCode(dataEntryCode);

        assertTrue(result, "Expected validation to pass with a valid data entry code.");
    }

    @Test
    void validateDataEntryCode_InvalidFormat_ReturnsFalse() {
        String dataEntryCode = "1234-DM";

        boolean result = controller.validateDataEntryCode(dataEntryCode);

        assertFalse(result, "Expected validation to fail with an invalid format.");
    }

    @Test
    void validateDataEntryCode_EmptyCode_ReturnsFalse() {
        String dataEntryCode = "";

        boolean result = controller.validateDataEntryCode(dataEntryCode);

        assertFalse(result, "Expected validation to fail when the data entry code is empty.");
    }

    @Test
    void validateDataEntryCode_IncorrectPattern_ReturnsFalse() {
        String dataEntryCode = "DM-12A4";

        boolean result = controller.validateDataEntryCode(dataEntryCode);

        assertFalse(result, "Expected validation to fail with an incorrect pattern.");
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

        assertFalse(result, "Expected validation to fail with an invalid email.");
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

        assertFalse(result, "Expected validation to fail with an invalid branch code.");
    }

    @Test
    void validateField_UnknownField_ReturnsFalse() {
        String field = "Unknown";
        String value = "Test Value";

        boolean result = controller.validateField(field, value);

        assertFalse(result, "Expected validation to fail with an unknown field.");
    }
}
