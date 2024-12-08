package SCD.ui.SuperAdmin.ManageBranches;

import static org.junit.Assert.*;

import SCD.controllers.SuperAdminControllers.ManageBranchesController.AddBranchController;
import SCD.ui.SuperAdmin.ManageBranches.AddBranchPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;

import static org.junit.jupiter.api.Assertions.*;

class AddBranchControllerTest {

    private AddBranchController addBranchController;
    private AddBranchPage addBranchPage;

    @BeforeEach
    void setUp() {
        addBranchPage = new AddBranchPage();
        addBranchController = new AddBranchController(addBranchPage);
    }

    @Test
    void validateInputs_AllFieldsValid_ReturnsTrue() {
        String name = "Branch Name";
        String city = "New York";
        String phone = "0321-1234567";
        String address = "123 Main Street";

        boolean result = addBranchController.validateInputs(name, city, phone, address);

        assertTrue(result, "Expected validation to pass with valid inputs.");
    }

    @Test
    void validateInputs_EmptyName_ReturnsFalse() {
        String name = "";
        String city = "New York";
        String phone = "0321-1234567";
        String address = "123 Main Street";

        boolean result = addBranchController.validateInputs(name, city, phone, address);

        assertFalse(result, "Expected validation to fail when name is empty.");
    }

    @Test
    void validateInputs_InvalidCity_ReturnsFalse() {
        String name = "Branch Name";
        String city = "New York1";
        String phone = "0321-1234567";
        String address = "123 Main Street";

        boolean result = addBranchController.validateInputs(name, city, phone, address);

        assertFalse(result, "Expected validation to fail when city contains invalid characters.");
    }

    @Test
    void validateInputs_InvalidPhoneFormat_ReturnsFalse() {
        String name = "Branch Name";
        String city = "New York";
        String phone = "12345";
        String address = "123 Main Street";

        boolean result = addBranchController.validateInputs(name, city, phone, address);

        assertFalse(result, "Expected validation to fail with an invalid phone format.");
    }

    @Test
    void validateInputs_EmptyAddress_ReturnsFalse() {
        String name = "Branch Name";
        String city = "New York";
        String phone = "0321-1234567";
        String address = "";

        boolean result = addBranchController.validateInputs(name, city, phone, address);

        assertFalse(result, "Expected validation to fail when address is empty.");
    }
}
