package SCD.controllers.DataEntryOperatorControllers.VendorController;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class RemoveVendorControllerTest {

    @Test
    void testValidateVendorId() {
        RemoveVendorController controller = new RemoveVendorController();

        // scd- proj initTest valid Vendor ID
        assertTrue(controller.validateVendorId("VM-0001"),
                "Valid Vendor ID should return true.");

        // scd- proj initTest empty Vendor ID
        assertFalse(controller.validateVendorId(""),
                "Empty Vendor ID should return false.");

        // scd- proj initTest invalid Vendor ID format
        assertFalse(controller.validateVendorId("1234"),
                "Invalid Vendor ID format should return false.");
    }

    @Test
    void testCheckVendorExists() {
        RemoveVendorController controller = new RemoveVendorController();

        // scd- proj initTest Vendor exists
        assertTrue(controller.checkVendorExists("VM-0001"),
                "Vendor ID 'VM-0001' should exist.");

        // scd- proj initTest Vendor does not exist
        assertFalse(controller.checkVendorExists("VM-9999"),
                "Vendor ID 'VM-9999' should not exist.");
    }

    @Test
    void testRemoveVendor() {
        RemoveVendorController controller = new RemoveVendorController();

        // scd- proj initTest removal success
        assertDoesNotThrow(() -> controller.removeVendor("VM-0001"),
                "Removing an existing Vendor should not throw an exception.");
    }

    @Test
    void testHandleRemoveVendor() {
        RemoveVendorController controller = new RemoveVendorController();

        assertDoesNotThrow(() -> {
            controller.handleRemoveVendor();
        }, "Handling vendor removal should not throw any exceptions.");
    }

    @Test
    void testMain() {
        assertDoesNotThrow(() -> RemoveVendorController.main(new String[] {}),
                "Main method should execute without errors.");
    }
}