package SCD.ui.BranchManager.ManageCashier;

import static org.junit.Assert.*;

import SCD.controllers.BranchManagerControllers.CashierController.DeleteCashierController;
import SCD.ui.BranchManager.ManageCashier.DeleteCashierPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DeleteCashierControllerTest {

    private DeleteCashierController controller;
    private DeleteCashierPage page;

    @BeforeEach
    void setUp() {
        page = new DeleteCashierPage();
        controller = new DeleteCashierController(page);
    }

    @Test
    void validateCashierCode_ValidCashierCode_ReturnsTrue() {
        String cashierCode = "CM-1234";

        boolean result = controller.validateCashierCode(cashierCode);

        assertTrue(result, "Expected validation to pass with a valid cashier code.");
    }

    @Test
    void validateCashierCode_EmptyCashierCode_ReturnsFalse() {
        String cashierCode = "";

        boolean result = controller.validateCashierCode(cashierCode);

        assertFalse(result, "Expected validation to fail when the cashier code is empty.");
    }

    @Test
    void validateCashierCode_InvalidFormat_ReturnsFalse() {
        String cashierCode = "1234-CM";

        boolean result = controller.validateCashierCode(cashierCode);

        assertFalse(result, "Expected validation to fail with an invalid cashier code format.");
    }

    @Test
    void validateCashierCode_IncorrectPattern_ReturnsFalse() {
        String cashierCode = "CM-12A4";

        boolean result = controller.validateCashierCode(cashierCode);

        assertFalse(result, "Expected validation to fail when the cashier code does not follow the 'CM-XXXX' pattern.");
    }
}
