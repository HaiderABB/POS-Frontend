package SCD.controllers.SuperAdminControllers.ManagesBranchManagerController;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class UpdateBranchManagerControllerTest {

    @Test
    void testValidateManagerCode() {
        UpdateBranchManagerController controller = new UpdateBranchManagerController();

        assertTrue(controller.validateManagerCode("BM-1234"), "Valid manager code should return true.");
        assertFalse(controller.validateManagerCode("INVALID"), "Invalid manager code should return false.");
        assertFalse(controller.validateManagerCode(""), "Empty manager code should return false.");
    }

    @Test
    void testValidateField() {
        UpdateBranchManagerController controller = new UpdateBranchManagerController();

        assertTrue(controller.validateField("Name", "John Doe"), "Valid name should return true.");
        assertFalse(controller.validateField("Name", ""), "Empty name should return false.");

        assertTrue(controller.validateField("Email", "test@example.com"), "Valid email should return true.");
        assertFalse(controller.validateField("Email", "invalid-email"), "Invalid email should return false.");

        assertTrue(controller.validateField("Branch Code", "BR-1234"), "Valid branch code should return true.");
        assertFalse(controller.validateField("Branch Code", ""), "Empty branch code should return false.");

        assertTrue(controller.validateField("Phone Number", "03001234567"), "Valid phone number should return true.");
        assertFalse(controller.validateField("Phone Number", "12345"), "Invalid phone number should return false.");
    }

    @Test
    void testValidateName() {
        UpdateBranchManagerController controller = new UpdateBranchManagerController();

        assertTrue(controller.validateName("John Doe"), "Valid name should return true.");
        assertFalse(controller.validateName(""), "Empty name should return false.");
        assertFalse(controller.validateName("12345"), "Name with numbers should return false.");
    }

    @Test
    void testValidateEmail() {
        UpdateBranchManagerController controller = new UpdateBranchManagerController();

        assertTrue(controller.validateEmail("test@example.com"), "Valid email should return true.");
        assertFalse(controller.validateEmail("invalid-email"), "Invalid email should return false.");
        assertFalse(controller.validateEmail(""), "Empty email should return false.");
    }

    @Test
    void testValidateBranchCode() {
        UpdateBranchManagerController controller = new UpdateBranchManagerController();

        assertTrue(controller.validateBranchCode("BR-1234"), "Valid branch code should return true.");
        assertFalse(controller.validateBranchCode("INVALID"), "Invalid branch code should return false.");
        assertFalse(controller.validateBranchCode(""), "Empty branch code should return false.");
    }

    @Test
    void testValidatePhoneNumber() {
        UpdateBranchManagerController controller = new UpdateBranchManagerController();

        assertTrue(controller.validatePhoneNumber("03001234567"), "Valid phone number should return true.");
        assertFalse(controller.validatePhoneNumber("12345"), "Invalid phone number should return false.");
        assertFalse(controller.validatePhoneNumber(""), "Empty phone number should return false.");
    }

    @Test
    void testHandleValidation() {
        UpdateBranchManagerController controller = new UpdateBranchManagerController();
        assertDoesNotThrow(controller::handleValidation, "Handling validation should not throw exceptions.");
    }

    @Test
    void testHandleUpdate() {
        UpdateBranchManagerController controller = new UpdateBranchManagerController();
        assertDoesNotThrow(controller::handleUpdate, "Handling update should not throw exceptions.");
    }

    @Test
    void testUpdateManager() {
        UpdateBranchManagerController controller = new UpdateBranchManagerController();
        assertDoesNotThrow(() -> controller.updateManager("BM-1234", "Name", "New Name"),
                "Updating manager should not throw exceptions.");
    }
}
