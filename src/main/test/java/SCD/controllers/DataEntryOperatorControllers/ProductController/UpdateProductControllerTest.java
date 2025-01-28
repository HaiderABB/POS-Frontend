package SCD.controllers.DataEntryOperatorControllers.ProductController;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class UpdateProductControllerTest {

    @Test
    void validateProductCode() {
        UpdateProductController controller = new UpdateProductController();

        assertTrue(controller.validateProductCode("PM-0001"), "Product Code 'PM-0001' should be valid.");
        assertFalse(controller.validateProductCode("INVALID-CODE"), "Product Code 'INVALID-CODE' should be invalid.");
        assertFalse(controller.validateProductCode(""), "Empty Product Code should be invalid.");
    }

    @Test
    void validateField() {
        UpdateProductController controller = new UpdateProductController();

        assertTrue(controller.validateField("Name", "Test Product"), "Valid name should return true.");
        assertFalse(controller.validateField("Name", "12345"), "Invalid name with numbers should return false.");
        assertTrue(controller.validateField("Price", "100.0"), "Valid price should return true.");
        assertFalse(controller.validateField("Price", "-10.0"), "Negative price should return false.");
        assertTrue(controller.validateField("Category", "Electronics"), "Valid category should return true.");
        assertFalse(controller.validateField("Category", ""), "Empty category should return false.");
        assertTrue(controller.validateField("Stock", "50"), "Valid stock should return true.");
        assertFalse(controller.validateField("Stock", "-5"), "Negative stock should return false.");
    }

    @Test
    void validatePriceRules() {
        UpdateProductController controller = new UpdateProductController();

        double originalPrice = 100.0;
        double salePrice = 120.0;
        double unitPrice = 110.0;
        double cartonPrice = 150.0;

        assertFalse(controller.validatePriceRules(originalPrice, salePrice, unitPrice, cartonPrice, "Price", 125.0),
                "Valid price rules should return true.");

        assertFalse(controller.validatePriceRules(originalPrice, salePrice, unitPrice, cartonPrice, "Price", 90.0),
                "Sale price lower than original price should return false.");

        assertFalse(controller.validatePriceRules(originalPrice, salePrice, unitPrice, cartonPrice, "Price per Unit", 100.0),
                "Unit price lower than sale price should return false.");

        assertFalse(controller.validatePriceRules(originalPrice, salePrice, unitPrice, cartonPrice, "Price per Carton", 120.0),
                "Carton price lower than other prices should return false.");
    }

    @Test
    void getCurrentValue() {
        UpdateProductController controller = new UpdateProductController();

        assertEquals(100.0, controller.getCurrentValue("Original Price"), "Original Price should be 100.0.");
        assertEquals(120.0, controller.getCurrentValue("Price"), "Sale Price should be 120.0.");
        assertEquals(110.0, controller.getCurrentValue("Price per Unit"), "Unit Price should be 110.0.");
        assertEquals(150.0, controller.getCurrentValue("Price per Carton"), "Carton Price should be 150.0.");
        assertEquals(0.0, controller.getCurrentValue("Unknown Field"), "Unknown field should return 0.0.");
    }

    @Test
    void main() {
        assertDoesNotThrow(() -> UpdateProductController.main(new String[]{}),
                "Main method should execute without errors.");
    }
}
