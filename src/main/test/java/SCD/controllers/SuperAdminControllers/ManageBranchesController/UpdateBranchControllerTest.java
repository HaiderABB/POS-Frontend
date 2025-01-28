package SCD.controllers.SuperAdminControllers.ManageBranchesController;

import SCD.ui.SuperAdmin.ManageBranches.UpdateBranchPage;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class UpdateBranchControllerTest {

        @Test
        void testValidateBranchCode() {
                UpdateBranchController controller = new UpdateBranchController(new UpdateBranchPage());

                // scd- proj initTest valid branch code
                assertTrue(controller.validateBranchCode("BR-0001"),
                                "Valid branch code should return true.");

                // scd- proj initTest empty branch code
                assertFalse(controller.validateBranchCode(""),
                                "Empty branch code should return false.");

                // scd- proj initTest invalid branch code format
                assertFalse(controller.validateBranchCode("BR1234"),
                                "Branch code without dash should return false.");

                assertFalse(controller.validateBranchCode("1234-BR"),
                                "Branch code in wrong format should return false.");
        }

        @Test
        void testValidateField() {
                UpdateBranchController controller = new UpdateBranchController(new UpdateBranchPage());

                // scd- proj initTest valid name
                assertTrue(controller.validateField("Name", "Head Office"),
                                "Valid name should return true.");

                // scd- proj initTest invalid name
                assertFalse(controller.validateField("Name", "1234"),
                                "Invalid name with numbers should return false.");

                // scd- proj initTest valid city
                assertTrue(controller.validateField("City", "Karachi"),
                                "Valid city name should return true.");

                // scd- proj initTest invalid city
                assertFalse(controller.validateField("City", "1234"),
                                "City with numbers should return false.");

                // scd- proj initTest valid phone
                assertTrue(controller.validateField("Phone", "03001234567"),
                                "Valid phone number should return true.");

                // scd- proj initTest invalid phone
                assertFalse(controller.validateField("Phone", "123456"),
                                "Invalid phone number should return false.");

                // scd- proj initTest valid address
                assertTrue(controller.validateField("Address", "123 Main Street, Karachi"),
                                "Valid address should return true.");

                // scd- proj initTest invalid address
                assertFalse(controller.validateField("Address", "Short"),
                                "Address with fewer than 10 characters should return false.");
        }

        @Test
        void testValidateName() {
                UpdateBranchController controller = new UpdateBranchController(new UpdateBranchPage());

                assertTrue(controller.validateField("Name", "Branch Name"),
                                "Valid name should return true.");

                assertFalse(controller.validateField("Name", "123Name"),
                                "Name with numbers should return false.");
        }

        @Test
        void testValidateCity() {
                UpdateBranchController controller = new UpdateBranchController(new UpdateBranchPage());

                assertTrue(controller.validateField("City", "Karachi"),
                                "Valid city should return true.");

                assertFalse(controller.validateField("City", "Karachi123"),
                                "City with numbers should return false.");
        }

        @Test
        void testValidatePhone() {
                UpdateBranchController controller = new UpdateBranchController(new UpdateBranchPage());

                assertTrue(controller.validateField("Phone", "03001234567"),
                                "Valid phone number should return true.");

                assertFalse(controller.validateField("Phone", "0300"),
                                "Phone number with fewer digits should return false.");
        }

        @Test
        void testValidateAddress() {
                UpdateBranchController controller = new UpdateBranchController(new UpdateBranchPage());

                assertTrue(controller.validateField("Address", "123 Main Street"),
                                "Valid address should return true.");

                assertFalse(controller.validateField("Address", "Short"),
                                "Address with fewer than 10 characters should return false.");
        }

        @Test
        void testUpdateBranch() {
                UpdateBranchController controller = new UpdateBranchController(new UpdateBranchPage());

                assertDoesNotThrow(() -> {
                        controller.updateBranch("BR-0001", "Name", "Updated Branch Name");
                }, "Updating a branch should not throw any exceptions.");
        }

        @Test
        void testMain() {
                assertDoesNotThrow(() -> UpdateBranchController.main(new String[] {}),
                                "Main method should execute without errors.");
        }
}
