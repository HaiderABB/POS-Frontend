package SCD.controllers.DataEntryOperatorControllers.VendorController;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ViewVendorsControllerTest {

    @Test
    void testLoadVendorData() {
        ViewVendorsController controller = new ViewVendorsController();

        assertDoesNotThrow(() -> {
            controller.main(new String[]{});
        }, "Loading vendor data should not throw any exceptions.");
    }

    @Test
    void testMain() {
        assertDoesNotThrow(() -> ViewVendorsController.main(new String[]{}),
                "Main method should execute without errors.");
    }
}
