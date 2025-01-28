package SCD.controllers.BranchManagerControllers.CashierController;

import SCD.model.models.Employee;
import SCD.model.service.Json.GetResponseJSON;
import SCD.ui.BranchManager.ManageCashier.ViewCashiersPage;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.List;

class ViewCashiersControllerTest {

    @Test
    void populateTable() {
        ViewCashiersPage mockView = new ViewCashiersPage();
        ViewCashiersController controller = new ViewCashiersController(mockView);

        List<Employee> employeeList = new ArrayList<>();
        Employee emp1 = new Employee();
        emp1.setEmployeeCode("CM-0001");
        emp1.setName("John Doe");
        emp1.setEmail("john.doe@example.com");
        employeeList.add(emp1);

        GetResponseJSON<Employee> json = new GetResponseJSON<>("Employees found", employeeList);



        assertNotNull(mockView);
    }

    @Test
    void main() {
        assertDoesNotThrow(() -> ViewCashiersController.main(new String[]{}));
    }
}
