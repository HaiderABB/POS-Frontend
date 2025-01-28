package SCD.controllers.BranchManagerControllers.DataEntryOperatorController;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import SCD.model.models.Employee;
import SCD.model.service.Json.GetResponseJSON;
import SCD.ui.BranchManager.ManageDataEntryOperator.ViewDataEntryOperatorsPage;

import java.util.ArrayList;
import java.util.List;

class ViewDataEntryOperatorsControllerTest {

    @Test
    void testPopulateTableWithData() {
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee("John Doe", "DATA_ENTRY_OPERATOR", null, "03001234567", "john.doe@example.com"));
        employees.add(new Employee("Jane Smith", "DATA_ENTRY_OPERATOR", null, "03007654321", "jane.smith@example.com"));
        GetResponseJSON<Employee> jsonResponse = new GetResponseJSON<>("Success", employees);

        ViewDataEntryOperatorsPage mockView = mock(ViewDataEntryOperatorsPage.class);

        ViewDataEntryOperatorsController controller = new ViewDataEntryOperatorsController(mockView);

    }

    @Test
    void testPopulateTableWithoutData() {

        GetResponseJSON<Employee> jsonResponse = new GetResponseJSON<>("No data", null);

        // scd- proj initMock View
        ViewDataEntryOperatorsPage mockView = mock(ViewDataEntryOperatorsPage.class);

        // scd- proj initInstantiate Controller
        ViewDataEntryOperatorsController controller = new ViewDataEntryOperatorsController(mockView);

        // scd- proj initCall populateTable
        controller.populateTable(jsonResponse);

        // scd- proj initVerify interactions
        verify(mockView).clearTable();
        verify(mockView).addRow(new Object[] { "-", "-", "-", "-" });
    }

    @Test
    void testMain() {
        assertDoesNotThrow(() -> ViewDataEntryOperatorsController.main(new String[] {}));
    }
}
