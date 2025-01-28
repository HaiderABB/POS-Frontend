package SCD.model.service;

import SCD.model.db.local.CodesDAO;
import SCD.model.models.Branch;
import SCD.model.models.Codes;
import SCD.model.models.Employee;
import SCD.model.service.BranchManagerService.BranchManagerService;
import SCD.model.service.CashierService.CashierService;
import SCD.model.service.Common.CommonServices;
import SCD.model.service.DataEntryOperatorService.DataEntryOperatorService;
import SCD.model.service.SuperAdminService.SuperAdminService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TestServiceTest {

    @Test
    void main() {
        CommonServices commonServices = new CommonServices();
        SuperAdminService superAdminService = new SuperAdminService();
        CodesDAO codesDAO = CodesDAO.getInstance();
        DataEntryOperatorService dataEntryOperatorService = new DataEntryOperatorService();
        BranchManagerService branchManagerService = new BranchManagerService();
        CashierService cashierService = new CashierService();

        // scd- proj initAdding initial codes
        Codes codes1 = new Codes("0000", "EMPLOYEES");
        Codes codes2 = new Codes("0000", "BRANCHES");
        Codes codes3 = new Codes("0000", "VENDORS");
        Codes codes4 = new Codes("0000", "PRODUCTS");

        assertDoesNotThrow(() -> codesDAO.addCode(codes1));
        assertDoesNotThrow(() -> codesDAO.addCode(codes2));
        assertDoesNotThrow(() -> codesDAO.addCode(codes3));
        assertDoesNotThrow(() -> codesDAO.addCode(codes4));

        // scd- proj initCreating and adding a branch
        Branch branch = new Branch("Main Branch", "Lahore", "Gulberg", "03219306126");

        // scd- proj initAdding an employee
        Employee employee = new Employee(
                "Haider Abbas Moazzam",
                "SUPER_ADMIN",
                branch,
                "03219306127",
                "haider.a.moazzam@gmail.com");

        // scd- proj initVerifying the branch and employee creation
        Branch retrievedBranch = superAdminService.getBranchByCode(branch.getBranchCode());
        assertNull(retrievedBranch);

        Employee retrievedEmployee = commonServices.getEmployeeByEmployeeCode(employee.getEmployeeCode());
        assertNull(retrievedEmployee);

    }
}
