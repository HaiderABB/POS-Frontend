package SCD.controllers.DataEntryOperatorControllers.ProductController;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ViewProductsByVendorControllerTest {

    @Test
    void validateVendorCode() {
        ViewProductsByVendorController controller = new ViewProductsByVendorController();

        assertTrue(controller.validateVendorCode("VM-1234"), "Valid vendor code should return true.");
        assertFalse(controller.validateVendorCode("INVALID"), "Invalid vendor code should return false.");
        assertFalse(controller.validateVendorCode(""), "Empty vendor code should return false.");
    }

    @Test
    void main() {
        assertDoesNotThrow(() -> ViewProductsByVendorController.main(new String[]{}),
                "Main method should execute without errors.");
    }
}
