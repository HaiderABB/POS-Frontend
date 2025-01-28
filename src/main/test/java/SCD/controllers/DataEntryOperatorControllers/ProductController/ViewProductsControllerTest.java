package SCD.controllers.DataEntryOperatorControllers.ProductController;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ViewProductsControllerTest {

    @Test
    void testMain() {
        assertDoesNotThrow(() -> ViewProductsController.main(new String[]{}),
                "Main method should execute without errors.");
    }

    @Test
    void testInitialization() {
        ViewProductsController controller = new ViewProductsController();
        assertNotNull(controller, "Controller should be successfully initialized.");
    }
}
