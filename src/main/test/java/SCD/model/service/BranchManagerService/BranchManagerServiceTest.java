package SCD.model.service.BranchManagerService;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import SCD.model.models.Employee;
import SCD.model.models.Sale;
import SCD.model.service.Json.GetResponseJSON;

class BranchManagerServiceTest {

    private BranchManagerService service = new BranchManagerService();

    @Test
    void getDataEntryOperators() {
        GetResponseJSON<Employee> response = service.getDataEntryOperators("BR-0001");
        assertNotNull(response);
        assertEquals("Employees found", response.getMessage());
    }

    @Test
    void getCashiers() {
        GetResponseJSON<Employee> response = service.getCashiers("BR-0002");
        assertNotNull(response);
        assertEquals("Employees found", response.getMessage());
    }

    @Test
    void getSalesByBranch() {
        GetResponseJSON<Sale> response = service.getSalesByBranch("BR-0002");
        assertNotNull(response);

    }

    @Test
    void main() {
        assertDoesNotThrow(() -> BranchManagerService.main(new String[]{}));
    }
}