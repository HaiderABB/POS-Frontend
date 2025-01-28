package SCD.controllers.DataEntryOperatorControllers.VendorController;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AddNewVendorControllerTest {

    @Test
    void testValidateInputs() {
        AddNewVendorController controller = new AddNewVendorController();

        // scd- proj initTest valid inputs
        assertTrue(controller.validateInputs("Vendor Name", "123 Address", "03123456789"),
                "Valid inputs should return true.");

        // scd- proj initTest empty vendor name
        assertFalse(controller.validateInputs("", "123 Address", "03123456789"),
                "Empty vendor name should return false.");

        // scd- proj initTest invalid phone number
        assertFalse(controller.validateInputs("Vendor Name", "123 Address", "123456"),
                "Invalid phone number should return false.");
    }

    @Test
    void testGenerateVendorCode() {
        AddNewVendorController controller = new AddNewVendorController();

        String vendorCode = controller.generateVendorCode();
        assertNotNull(vendorCode, "Generated vendor code should not be null.");
        assertTrue(vendorCode.matches("VM-\\d{4}"), "Generated vendor code should follow the format 'VM-XXXX'.");
    }

    @Test
    void testSaveVendor() {
        AddNewVendorController controller = new AddNewVendorController();

        assertDoesNotThrow(() -> {
            controller.saveVendor("Vendor Name", "123 Address", "03123456789", "VM-0001");
        }, "Saving a vendor should not throw any exceptions.");
    }

    @Test
    void testClearFields() {
        AddNewVendorController controller = new AddNewVendorController();

        assertDoesNotThrow(controller::clearFields,
                "Clearing fields should not throw any exceptions.");
    }

    @Test
    void testHandleAddVendor() {
        AddNewVendorController controller = new AddNewVendorController();

        assertDoesNotThrow(controller::handleAddVendor,
                "Handling vendor addition should not throw any exceptions.");
    }

    @Test
    void testMain() {
        assertDoesNotThrow(() -> AddNewVendorController.main(new String[] {}),
                "Main method should execute without errors.");
    }
}
