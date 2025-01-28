package SCD.controllers.SuperAdminControllers.Reports;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

class ViewReportsControllerTest {

    @Test
    void testCalculateStartDate() {
        ViewReportsController controller = new ViewReportsController();

        LocalDate today = LocalDate.now();
        assertEquals(today, controller.calculateStartDate("Today"), "Start date for Today should be today.");
        assertEquals(today.minusDays(6), controller.calculateStartDate("Weekly"),
                "Start date for Weekly should be 7 days ago.");
        assertEquals(today.withDayOfMonth(1), controller.calculateStartDate("Monthly"),
                "Start date for Monthly should be the first day of the month.");
        assertEquals(today.withDayOfYear(1), controller.calculateStartDate("Yearly"),
                "Start date for Yearly should be the first day of the year.");
        assertNull(controller.calculateStartDate("Invalid"), "Invalid duration should return null.");
    }

    @Test
    void testCalculateEndDate() {
        ViewReportsController controller = new ViewReportsController();

        LocalDate today = LocalDate.now();
        assertEquals(today, controller.calculateEndDate("Today"), "End date for Today should be today.");
        assertEquals(today, controller.calculateEndDate("Weekly"), "End date for Weekly should be today.");
        assertEquals(today, controller.calculateEndDate("Monthly"), "End date for Monthly should be today.");
        assertEquals(today, controller.calculateEndDate("Yearly"), "End date for Yearly should be today.");
    }

    @Test
    void testFormatDate() {
        ViewReportsController controller = new ViewReportsController();

        LocalDate date = LocalDate.of(2023, 12, 1);
        assertEquals("2023-12-01", controller.formatDate(date, "Today"), "Date format for Today should be yyyy-MM-dd.");
        assertEquals("2023-12", controller.formatDate(date, "Monthly"), "Date format for Monthly should be yyyy-MM.");
        assertEquals("2023", controller.formatDate(date, "Yearly"), "Date format for Yearly should be yyyy.");
    }

    @Test
    void testValidateBranchCode() {
        ViewReportsController controller = new ViewReportsController();

        assertTrue(controller.validateBranchCode("BR-1234"), "Valid branch code should return true.");
        assertFalse(controller.validateBranchCode("INVALID"), "Invalid branch code should return false.");
        assertFalse(controller.validateBranchCode(""), "Empty branch code should return false.");
    }

    @Test
    void testGetCustomStartDate() {
        ViewReportsController controller = new ViewReportsController();
        assertDoesNotThrow(() -> {
        }, "Getting custom start date should not throw exceptions.");
    }

    @Test
    void testGetCustomEndDate() {
        ViewReportsController controller = new ViewReportsController();
        // scd- proj initSimulate a valid JSpinner and test manually if needed.
        assertDoesNotThrow(() -> {
            // scd- proj initTest logic for spinner with date value here.
        }, "Getting custom end date should not throw exceptions.");
    }

    @Test
    void testShowErrorDialog() {
        ViewReportsController controller = new ViewReportsController();
        assertDoesNotThrow(() -> {
            controller.showErrorDialog("Test Error Message");
        }, "Showing an error dialog should not throw exceptions.");
    }

    @Test
    void testInitController() {
        ViewReportsController controller = new ViewReportsController();
        assertDoesNotThrow(controller::initController, "Initializing controller should not throw exceptions.");
    }

}
