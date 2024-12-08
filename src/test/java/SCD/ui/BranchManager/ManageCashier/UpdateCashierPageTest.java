package SCD.ui.BranchManager.ManageCashier;

import static org.junit.Assert.*;

import SCD.controllers.BranchManagerControllers.CashierController.UpdateCashierController;
import SCD.ui.BranchManager.ManageCashier.UpdateCashierPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UpdateCashierControllerTest {

    private UpdateCashierController controller;
    private UpdateCashierPage page;

    @BeforeEach
    void setUp() {
        page = new UpdateCashierPage();
        controller = new UpdateCashierController(page);
    }

    // Test cases for validateCashierCode
    @Test
    void validateCashierCode_ValidCashierCode_ReturnsTrue() {
        String cashierCode = "CM-1234";

        boolean result = controller.validateCashierCode(cashierCode);

        assertTrue(result, "Expected validation to pass with a valid cashier code.");
    }

    @Test
    void validateCashierCode_InvalidFormat_ReturnsFalse() {
        String cashierCode = "1234-CM";

        boolean result = controller.validateCashierCode(cashierCode);

        assertFalse(result, "Expected validation to fail with an invalid cashier code format.");
    }

    @Test
    void validateCashierCode_EmptyCashierCode_ReturnsFalse() {
        String cashierCode = "";

        boolean result = controller.validateCashierCode(cashierCode);

        assertFalse(result, "Expected validation to fail when cashier code is empty.");
    }

    @Test
    void validateCashierCode_IncorrectPattern_ReturnsFalse() {
        String cashierCode = "CM-12A4";

        boolean result = controller.validateCashierCode(cashierCode);

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
