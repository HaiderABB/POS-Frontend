package SCD.controllers.SuperAdminControllers.ManagesBranchManagerController;

import SCD.ui.SuperAdmin.ManageBranchManager.AddBranchManagerPage;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AddBranchManagerControllerTest {

    @Test
    void testValidateInputs() {
        AddBranchManagerController controller = new AddBranchManagerController();

        // scd- proj initValid inputs
        assertTrue(controller.validateInputs("John Doe", "john.doe@example.com", "BR-1234", "03001234567"),
                "Valid inputs should return true.");

        // scd- proj initInvalid email
        assertFalse(controller.validateInputs("John Doe", "invalid-email", "BR-1234", "03001234567"),
                "Invalid email should return false.");

        // scd- proj initInvalid branch code
        assertFalse(controller.validateInputs("John Doe", "john.doe@example.com", "INVALID", "03001234567"),
                "Invalid branch code should return false.");

        // scd- proj initInvalid phone number
        assertFalse(controller.validateInputs("John Doe", "john.doe@example.com", "BR-1234", "123456"),
                "Invalid phone number should return false.");
    }

    @Test
    void testClearFields() {
        AddBranchManagerPage mockPage = new AddBranchManagerPage();
        AddBranchManagerController controller = new AddBranchManagerController(mockPage);

        // scd- proj initSimulate setting text fields
        mockPage.getNameField().setText("Test Name");
        mockPage.getEmailField().setText("test@example.com");
        mockPage.getBranchCodeField().setText("BR-1234");
        mockPage.getPhoneField().setText("03001234567");

        // scd- proj initClear fields
        controller.clearFields();

        // scd- proj initVerify fields are empty
        assertEquals("", mockPage.getNameField().getText(), "Name field should be cleared.");
        assertEquals("", mockPage.getEmailField().getText(), "Email field should be cleared.");
        assertEquals("", mockPage.getBranchCodeField().getText(), "Branch code field should be cleared.");
        assertEquals("", mockPage.getPhoneField().getText(), "Phone field should be cleared.");
    }

    @Test
    void testSaveBranchManager() {
        AddBranchManagerController controller = new AddBranchManagerController();

        assertDoesNotThrow(() -> {
            controller.saveBranchManager("John Doe", "john.doe@example.com", "BR-1234", "03001234567", "BM-5678");
        }, "Saving branch manager should not throw any exceptions.");
    }

    @Test
    void testMain() {
        assertDoesNotThrow(() -> AddBranchManagerController.main(new String[] {}),
                "Main method should execute without errors.");
    }
}