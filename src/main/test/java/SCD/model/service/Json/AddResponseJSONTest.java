package SCD.model.service.Json;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class AddResponseJSONTest {

    @Test
    void testConstructor() {
        AddResponseJSON response = new AddResponseJSON("Success Message", true, "Code-001");
        assertNotNull(response, "Object should be created successfully");
        assertEquals("Success Message", response.getMessage(), "Message should match");
        assertTrue(response.isSuccess(), "Success flag should match");
        assertEquals("Code-001", response.getCode(), "Code should match");
    }

    @Test
    void testIsSuccess() {
        AddResponseJSON response = new AddResponseJSON("Success Message", true, "Code-001");
        assertTrue(response.isSuccess(), "isSuccess should return true");

        response.setSuccess(false);
        assertFalse(response.isSuccess(), "isSuccess should return false after being set");
    }

    @Test
    void testSetSuccess() {
        AddResponseJSON response = new AddResponseJSON("Success Message", true, "Code-001");
        response.setSuccess(false);
        assertFalse(response.isSuccess(), "Success flag should be updated correctly");

        response.setSuccess(true);
        assertTrue(response.isSuccess(), "Success flag should be updated correctly");
    }

    @Test
    void testGetMessage() {
        AddResponseJSON response = new AddResponseJSON("Initial Message", true, "Code-001");
        assertEquals("Initial Message", response.getMessage(), "getMessage should return the initial message");

        response.setMessage("Updated Message");
        assertEquals("Updated Message", response.getMessage(), "getMessage should return the updated message");
    }

    @Test
    void testSetMessage() {
        AddResponseJSON response = new AddResponseJSON("Initial Message", true, "Code-001");
        response.setMessage("New Message");
        assertEquals("New Message", response.getMessage(), "Message should be updated correctly");
    }

    @Test
    void testGetCode() {
        AddResponseJSON response = new AddResponseJSON("Success Message", true, "Code-001");
        assertEquals("Code-001", response.getCode(), "getCode should return the initial code");

        response.setCode("Code-002");
        assertEquals("Code-002", response.getCode(), "getCode should return the updated code");
    }

    @Test
    void testSetCode() {
        AddResponseJSON response = new AddResponseJSON("Success Message", true, "Code-001");
        response.setCode("New-Code");
        assertEquals("New-Code", response.getCode(), "Code should be updated correctly");
    }
}
