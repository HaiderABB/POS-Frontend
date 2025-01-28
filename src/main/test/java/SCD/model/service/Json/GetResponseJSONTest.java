package SCD.model.service.Json;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class GetResponseJSONTest {

    private GetResponseJSON<String> response;

    @BeforeEach
    void setUp() {
        List<String> data = new ArrayList<>();
        data.add("Item1");
        data.add("Item2");
        response = new GetResponseJSON<>("Operation successful", data);
    }

    @Test
    void testGetMessage() {
        assertEquals("Operation successful", response.getMessage(), "Message should match the initialized value");
    }

    @Test
    void testSetMessage() {
        response.setMessage("New message");
        assertEquals("New message", response.getMessage(), "Message should be updated correctly");
    }

    @Test
    void testGetData() {
        List<String> data = response.getData();
        assertNotNull(data, "Data list should not be null");
        assertEquals(2, data.size(), "Data list should have 2 items");
        assertTrue(data.contains("Item1"), "Data list should contain 'Item1'");
        assertTrue(data.contains("Item2"), "Data list should contain 'Item2'");
    }

    @Test
    void testSetData() {
        List<String> newData = new ArrayList<>();
        newData.add("NewItem1");
        response.setData(newData);

        assertNotNull(response.getData(), "Updated data list should not be null");
        assertEquals(1, response.getData().size(), "Data list should be updated");
        assertTrue(response.getData().contains("NewItem1"), "Data list should contain 'NewItem1'");
    }

    @Test
    void testEmptyData() {
        response.setData(new ArrayList<>());
        assertNotNull(response.getData(), "Data list should not be null even if empty");
        assertTrue(response.getData().isEmpty(), "Data list should be empty");
    }

    @Test
    void testNullData() {
        response.setData(null);
        assertNull(response.getData(), "Data list should be null when explicitly set to null");
    }
}
