package SCD.model.service.Common;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import SCD.model.models.Branch;
import SCD.model.models.Employee;
import SCD.model.service.Json.AddResponseJSON;

class CommonServicesTest {

    private CommonServices commonServices;
    private Branch branch;

    @BeforeEach
    void setUp() {
        commonServices = new CommonServices();
    }

    @Test
    void testLogin_ValidCredentials() {


    }

    @Test
    void testLogin_InvalidCredentials() {
        Employee employee = commonServices.Login("BM-9999", "wrongpassword", "MANAGER");
        assertNull(employee, "Employee should be null for invalid credentials");
    }

    @Test
    void testSetFirstLoginStatus() {

    }

    @Test
    void testGetCurrentPassword_ValidEmployee() {
        String password = commonServices.getCurrentPassword("CM-0003");
        assertEquals("first1234", password, "Expected correct password");
    }

    @Test
    void testGetCurrentPassword_InvalidEmployee() {
        String password = commonServices.getCurrentPassword("BM-9999");
        assertEquals("", password, "Expected empty string for invalid employee");
    }

    @Test
    void testUpdatePassword() {
        boolean result = commonServices.update_password("BM-0002", "newpassword123");
        assertTrue(result, "Password should be updated successfully");
    }

    @Test
    void testCheckEmailExists_ValidEmail() {
        boolean exists = commonServices.checkEmailExists("john.doe@example.com");
        assertTrue(exists, "Email should exist");
    }

    @Test
    void testCheckEmailExists_InvalidEmail() {
        boolean exists = commonServices.checkEmailExists("invalid@example.com");
        assertFalse(exists, "Email should not exist");
    }

    @Test
    void testCheckBranchExists_ValidBranch() {
        boolean exists = commonServices.checkBranchExists("BR-0001");
        assertTrue(exists, "Branch should exist");
    }

    @Test
    void testCheckBranchExists_InvalidBranch() {
        boolean exists = commonServices.checkBranchExists("BR-9999");
        assertFalse(exists, "Branch should not exist");
    }

    @Test
    void testCheckPhoneNumberExists_ValidPhoneNumber() {
        boolean exists = commonServices.checkPhoneNumberExists("03219306127");
        assertTrue(exists, "Phone number should exist");
    }

    @Test
    void testCheckPhoneNumberExists_InvalidPhoneNumber() {
        boolean exists = commonServices.checkPhoneNumberExists("00000000000");
        assertFalse(exists, "Phone number should not exist");
    }

    @Test
    void testAddEmployee() {

    }

    @Test
    void testRemoveEmployee_ValidEmployee() {

    }

    @Test
    void testRemoveEmployee_InvalidEmployee() {
        AddResponseJSON response = commonServices.RemoveEmployee("CM-9999");
        assertFalse(response.isSuccess(), "Removing non-existing employee should fail");
    }

    @Test
    void testLowerCaseEmail() {
        String result = commonServices.LowerCaseEmail("John.Doe@Example.COM");
        assertEquals("john.doe@example.com", result, "Email should be converted to lowercase");
    }

    @Test
    void testUpdateEmployee() {

    }

    @Test
    void testIncrementCode() {
        String incrementedCode = commonServices.incrementCode("0005");
        assertEquals("0006", incrementedCode, "Code should increment correctly");
    }

    @Test
    void testCheckEmployeeExists_ValidEmployee() {
        boolean exists = commonServices.checkEmployeeExists("CM-0003");
        assertTrue(exists, "Employee should exist");
    }

    @Test
    void testCheckEmployeeExists_InvalidEmployee() {
        boolean exists = commonServices.checkEmployeeExists("CM-9999");
        assertFalse(exists, "Employee should not exist");
    }
}
