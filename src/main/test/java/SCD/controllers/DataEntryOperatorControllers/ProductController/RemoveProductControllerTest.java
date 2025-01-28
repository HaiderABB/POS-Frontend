package SCD.controllers.DataEntryOperatorControllers.ProductController;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class RemoveProductControllerTest {

    @Test
    void validateProductId() {
        RemoveProductController controller = new RemoveProductController();

        boolean validId = controller.validateProductId("PM-0001");
        assertTrue(validId, "Product ID 'PM-0001' should be valid.");

        boolean invalidId = controller.validateProductId("INVALID-ID");
        assertFalse(invalidId, "Product ID 'INVALID-ID' should be invalid.");

        boolean emptyId = controller.validateProductId("");
        assertFalse(emptyId, "Empty Product ID should be invalid.");
    }

    @Test
    void checkProductExists() {
        RemoveProductController controller = new RemoveProductController();

        boolean productExists = controller.checkProductExists("PM-0001");
        assertTrue(productExists, "Product ID 'PM-0001' should exist.");

        boolean productDoesNotExist = controller.checkProductExists("PM-9999");
        assertFalse(productDoesNotExist, "Product ID 'PM-9999' should not exist.");
    }

    @Test
    void main() {
        assertDoesNotThrow(() -> RemoveProductController.main(new String[]{}),
                "Main method should execute without errors.");
    }
}
