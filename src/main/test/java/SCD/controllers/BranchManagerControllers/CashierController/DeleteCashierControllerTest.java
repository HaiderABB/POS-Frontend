package SCD.controllers.BranchManagerControllers.CashierController;

import SCD.ui.BranchManager.ManageCashier.DeleteCashierPage;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class DeleteCashierControllerTest {

    private DeleteCashierController controller = new DeleteCashierController();

    @Test
    void handleDeleteCashier() {
        String validCashierCode = "CM-0005";
        controller.view.getCashierCodeField().setText(validCashierCode);
        controller.handleDeleteCashier();
        assertEquals("", controller.view.getCashierCodeField().getText());
    }

    @Test
    void validateCashierCode() {
        assertTrue(controller.validateCashierCode("CM-1234"));
        assertFalse(controller.validateCashierCode("CM1234"));
        assertFalse(controller.validateCashierCode(""));
        assertFalse(controller.validateCashierCode("1234"));
    }

    @Test
    void deleteCashier() {
        String cashierCode = "CM-5678";
        assertDoesNotThrow(() -> controller.deleteCashier(cashierCode));
        assertTrue(true);
    }
}
