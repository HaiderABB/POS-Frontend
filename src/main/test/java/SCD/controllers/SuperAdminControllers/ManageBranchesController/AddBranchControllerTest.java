package SCD.controllers.SuperAdminControllers.ManageBranchesController;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AddBranchControllerTest {

        @Test
        void testValidateInputs() {
                AddBranchController controller = new AddBranchController();

                // scd- proj initTest valid inputs
                assertTrue(controller.validateInputs("Branch Name", "City", "03001234567", "123 Main St"),
                                "Valid inputs should return true.");

                // scd- proj initTest empty name
                assertFalse(controller.validateInputs("", "City", "03001234567", "123 Main St"),
                                "Empty name should return false.");

                // scd- proj initTest empty city
                assertFalse(controller.validateInputs("Branch Name", "", "03001234567", "123 Main St"),
                                "Empty city should return false.");

                // scd- proj initTest empty phone
                assertFalse(controller.validateInputs("Branch Name", "City", "", "123 Main St"),
                                "Empty phone should return false.");

                // scd- proj initTest empty address
                assertFalse(controller.validateInputs("Branch Name", "City", "03001234567", ""),
                                "Empty address should return false.");

                // scd- proj initTest invalid city
                assertFalse(controller.validateInputs("Branch Name", "City123", "03001234567", "123 Main St"),
                                "City with numbers should return false.");

                // scd- proj initTest invalid phone
                assertFalse(controller.validateInputs("Branch Name", "City", "12345", "123 Main St"),
                                "Invalid phone number should return false.");
        }

        @Test
        void testSaveBranch() {
                AddBranchController controller = new AddBranchController();

                assertDoesNotThrow(() -> {
                        controller.saveBranch("Branch Name", "City", "03001234567", "123 Main St", "BR-001");
                }, "Saving a branch should not throw any exceptions.");
        }

        @Test
        void testMain() {
                assertDoesNotThrow(() -> AddBranchController.main(new String[] {}),
                                "Main method should execute without errors.");
        }
}
