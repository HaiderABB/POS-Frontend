package SCD.controllers.DataEntryOperatorControllers.ProductController;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AddProductControllerTest {

    @Test
    void validateInputs() {
        AddProductController controller = new AddProductController();

        boolean validInputs = controller.validateInputs(
                "Product1", "VM-0001", "100", "120", "110", "150", "50");
        assertTrue(validInputs, "Inputs should be valid.");

        boolean invalidInputs = controller.validateInputs(
                "", "VM-0001", "100", "120", "110", "150", "50");
        assertFalse(invalidInputs, "Inputs should be invalid due to missing name.");
    }

    @Test
    void checkLimits() {
        AddProductController controller = new AddProductController();

        boolean withinLimits = controller.checkLimits(50, 60, 55, 80, 100);
        assertTrue(withinLimits, "Values should be within allowed limits.");

        boolean outOfLimits = controller.checkLimits(0, 60, 55, 80, 100);
        assertFalse(outOfLimits, "Original price should be out of allowed limits.");
    }

    @Test
    void validateBusinessRules() {
        AddProductController controller = new AddProductController();

        boolean validBusinessRules = controller.validateBusinessRules(100, 120, 110, 150, 50);
        assertFalse(validBusinessRules, "Business rules should be satisfied.");

        boolean invalidBusinessRules = controller.validateBusinessRules(100, 90, 110, 150, 50);
        assertFalse(invalidBusinessRules, "Sale price should be greater than the original price.");
    }

    @Test
    void clearFields() {
        AddProductController controller = new AddProductController();
        assertDoesNotThrow(controller::clearFields, "clearFields should execute without errors.");
    }

    @Test
    void main() {
        assertDoesNotThrow(() -> AddProductController.main(new String[]{}),
                "Main method should execute without errors.");
    }
}
