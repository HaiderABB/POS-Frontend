package SCD.controllers.BranchManagerControllers.DataEntryOperatorController;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UpdateDataEntryControllerTest {

    private final UpdateDataEntryController controller = new UpdateDataEntryController();

    @Test
    void validateDataEntryCode_ValidCode() {
        assertTrue(controller.validateDataEntryCode("DM-1234"));
    }

    @Test
    void validateDataEntryCode_InvalidFormat() {
        assertFalse(controller.validateDataEntryCode("INVALID_CODE"));
    }

    @Test
    void validateDataEntryCode_EmptyCode() {
        assertFalse(controller.validateDataEntryCode(""));
    }

    @Test
    void validateName_ValidName() {
        assertTrue(controller.validateField("Name", "John Doe"));
    }

    @Test
    void validateName_InvalidName() {
        assertFalse(controller.validateField("Name", "John123"));
    }

    @Test
    void validateEmail_ValidEmail() {
        assertTrue(controller.validateField("Email", "example@example.com"));
    }

    @Test
    void validateEmail_InvalidEmail() {
        assertFalse(controller.validateField("Email", "example@.com"));
    }

    @Test
    void validatePhoneNumber_ValidPhone() {
        assertTrue(controller.validateField("Phone Number", "03123456789"));
    }

    @Test
    void validatePhoneNumber_InvalidPhone() {
        assertFalse(controller.validateField("Phone Number", "123456789"));
    }

    @Test
    void validateBranchCode_ValidCode() {
        assertTrue(controller.validateField("Branch Code", "BR-1234"));
    }

    @Test
    void validateBranchCode_InvalidCode() {
        assertFalse(controller.validateField("Branch Code", "BR-12"));
    }

    @Test
    void handleValidation_ValidFields() {
        assertDoesNotThrow(() -> controller.validateField("Name", "John Doe"));
        assertDoesNotThrow(() -> controller.validateField("Email", "example@example.com"));
    }

    @Test
    void handleUpdate_ValidInputs() {
        assertDoesNotThrow(() -> {
            controller.validateDataEntryCode("DM-1234");
            controller.validateField("Name", "John Doe");
        });
    }

    @Test
    void main() {
        assertDoesNotThrow(() -> UpdateDataEntryController.main(new String[]{}));
    }
}

