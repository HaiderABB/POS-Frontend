package SCD.controllers.DataEntryOperatorControllers.VendorController;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class UpdateVendorControllerTest {

    @Test
    void testValidateVendorCode() {
        UpdateVendorController controller = new UpdateVendorController();

        // scd- proj initTest valid vendor code
        assertTrue(controller.validateVendorCode("VM-0001"), "Valid vendor code should return true.");

        // scd- proj initTest invalid vendor code
        assertFalse(controller.validateVendorCode("1234"), "Invalid vendor code should return false.");

        // scd- proj initTest empty vendor code
        assertFalse(controller.validateVendorCode(""), "Empty vendor code should return false.");
    }

    @Test
    void testValidateField() {
        UpdateVendorController controller = new UpdateVendorController();

        // scd- proj initTest valid name
        assertTrue(controller.validateField("Name", "Vendor Name"), "Valid name should return true.");

        // scd- proj initTest empty name
        assertFalse(controller.validateField("Name", ""), "Empty name should return false.");

        // scd- proj initTest valid address
        assertTrue(controller.validateField("Address", "123 Address"), "Valid address should return true.");

        // scd- proj initTest empty address
        assertFalse(controller.validateField("Address", ""), "Empty address should return false.");

        // scd- proj initTest valid phone
        assertTrue(controller.validateField("Phone", "1234567890"), "Valid phone number should return true.");

        // scd- proj initTest invalid phone
        assertFalse(controller.validateField("Phone", "123"), "Invalid phone number should return false.");
    }

    @Test
    void testValidateName() {
        UpdateVendorController controller = new UpdateVendorController();

        // scd- proj initTest valid name
        assertTrue(controller.validateName("Vendor Name"), "Valid name should return true.");

        // scd- proj initTest invalid name
        assertFalse(controller.validateName("123"), "Invalid name should return false.");

        // scd- proj initTest empty name
        assertFalse(controller.validateName(""), "Empty name should return false.");
    }

    @Test
    void testValidateAddress() {
        UpdateVendorController controller = new UpdateVendorController();

        // scd- proj initTest valid address
        assertTrue(controller.validateAddress("123 Address"), "Valid address should return true.");

        // scd- proj initTest empty address
        assertFalse(controller.validateAddress(""), "Empty address should return false.");
    }

    @Test
    void testValidatePhone() {
        UpdateVendorController controller = new UpdateVendorController();

        // scd- proj initTest valid phone number
        assertTrue(controller.validatePhone("1234567890"), "Valid phone number should return true.");

        // scd- proj initTest invalid phone number
        assertFalse(controller.validatePhone("123"), "Invalid phone number should return false.");

        // scd- proj initTest empty phone number
        assertFalse(controller.validatePhone(""), "Empty phone number should return false.");
    }

    @Test
    void testUpdateVendor() {
        UpdateVendorController controller = new UpdateVendorController();

        assertDoesNotThrow(() -> {

            controller.updateVendor("VM-0001", "Name", "Updated Name");
        }, "Updating a vendor should not throw any exceptions.");
    }

    @Test
    void testHandleValidation() {
        UpdateVendorController controller = new UpdateVendorController();

        assertDoesNotThrow(controller::handleValidation,
                "Handling validation should not throw any exceptions.");
    }

    @Test
    void testHandleUpdate() {
        UpdateVendorController controller = new UpdateVendorController();

        assertDoesNotThrow(controller::handleUpdate,
                "Handling update should not throw any exceptions.");
    }

    @Test
    void testMain() {
        assertDoesNotThrow(() -> UpdateVendorController.main(new String[] {}),
                "Main method should execute without errors.");
    }
}
