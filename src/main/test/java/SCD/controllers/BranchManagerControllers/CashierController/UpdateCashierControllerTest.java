package SCD.controllers.BranchManagerControllers.CashierController;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class UpdateCashierControllerTest {

    private UpdateCashierController controller = new UpdateCashierController();

    @Test
    void handleValidation() {
        String validField = "Name";
        String validValue = "John Doe";
        String invalidField = "Email";
        String invalidValue = "notAnEmail";

        controller.view.getFieldComboBox().setSelectedItem(validField);
        controller.view.getNewValueField().setText(validValue);
        assertDoesNotThrow(() -> controller.handleValidation());

        controller.view.getFieldComboBox().setSelectedItem(invalidField);
        controller.view.getNewValueField().setText(invalidValue);
        assertDoesNotThrow(() -> controller.handleValidation());
    }

    @Test
    void validateCashierCode() {
        assertTrue(controller.validateCashierCode("CM-1234"));
        assertFalse(controller.validateCashierCode("CM1234"));
        assertFalse(controller.validateCashierCode(""));
    }

    @Test
    void validateField() {
        assertTrue(controller.validateField("Name", "John Doe"));
        assertFalse(controller.validateField("Email", "notAnEmail"));
        assertTrue(controller.validateField("Branch Code", "BR-1234"));
        assertFalse(controller.validateField("Phone Number", "123456"));
    }

    @Test
    void main() {
        assertDoesNotThrow(() -> UpdateCashierController.main(new String[]{}));
    }
}
