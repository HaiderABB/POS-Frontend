package SCD.model.service.SuperAdminService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import SCD.model.models.Branch;
import SCD.model.models.Employee;
import SCD.model.service.Json.AddResponseJSON;
import SCD.model.service.Json.GetResponseJSON;
import SCD.model.service.Json.GetReportJSON;

import java.time.LocalDate;

class SuperAdminServiceTest {
    private SuperAdminService superAdminService;

    @BeforeEach
    void setUp() {
        superAdminService = new SuperAdminService();
    }

    @Test
    void incrementCode() {
        assertEquals("0005", superAdminService.incrementCode("0004"));
        assertEquals("0010", superAdminService.incrementCode("0009"));
        assertEquals("0001", superAdminService.incrementCode("0000"));
    }

    @Test
    void checkPhoneExists() {
        Branch branch = new Branch();
        branch.setPhone("03234567890");
        superAdminService.createBranch(branch);

        assertFalse(superAdminService.checkPhoneExists("03234567890"));
        assertFalse(superAdminService.checkPhoneExists("9999999999"));
    }

    @Test
    void createBranch() {
        Branch branch = new Branch();
        branch.setName("Test Branch");
        branch.setPhone("03111111111");
        branch.setAddress("Test Address");

        AddResponseJSON response = superAdminService.createBranch(branch);

        assertFalse(response.isSuccess());
        assertNull(response.getCode());

    }

    @Test
    void deleteBranch() {
        Branch branch = new Branch();
        branch.setName("Branch to Delete");
        branch.setPhone("03222222222");
        AddResponseJSON createResponse = superAdminService.createBranch(branch);

        AddResponseJSON deleteResponse = superAdminService.deleteBranch(createResponse.getCode());

        assertFalse(deleteResponse.isSuccess());

    }

    @Test
    void getBranchManagers() {
        GetResponseJSON<Employee> managersResponse = superAdminService.getBranchManagers();

        assertNotNull(managersResponse);
    }

    @Test
    void updateBranch() {
        Branch branch = new Branch();
        branch.setName("Original Branch");
        branch.setPhone("03333333333");
        AddResponseJSON createResponse = superAdminService.createBranch(branch);

        Branch updatedBranch = new Branch();
        updatedBranch.setBranchCode(createResponse.getCode());
        updatedBranch.setName("Updated Branch");
        updatedBranch.setPhone("03444444444");


    }

    @Test
    void getBranches() {
        GetResponseJSON<Branch> branchesResponse = superAdminService.getBranches();

        assertNotNull(branchesResponse);
    }

    @Test
    void getTodaysReport() {
        Branch branch = new Branch();
        branch.setName("Report Branch");
        branch.setPhone("5555555555");
        AddResponseJSON createResponse = superAdminService.createBranch(branch);

        GetReportJSON report = superAdminService.getTodaysReport(createResponse.getCode());

        assertNotNull(report);
    }

    @Test
    void getWeeklyReport() {
        Branch branch = new Branch();
        branch.setName("Weekly Report Branch");
        branch.setPhone("6666666666");
        AddResponseJSON createResponse = superAdminService.createBranch(branch);

        GetReportJSON report = superAdminService.getWeeklyReport(createResponse.getCode());

        assertNotNull(report);
    }

    @Test
    void getMonthlyReport() {
        Branch branch = new Branch();
        branch.setName("Monthly Report Branch");
        branch.setPhone("7777777777");
        AddResponseJSON createResponse = superAdminService.createBranch(branch);

        GetReportJSON report = superAdminService.getMonthlyReport(createResponse.getCode());

        assertNotNull(report);
    }

    @Test
    void getYearlyReport() {
        Branch branch = new Branch();
        branch.setName("Yearly Report Branch");
        branch.setPhone("8888888888");
        AddResponseJSON createResponse = superAdminService.createBranch(branch);

        GetReportJSON report = superAdminService.getYearlyReport(createResponse.getCode());

        assertNotNull(report);
    }

    @Test
    void getRangeReport() {
        Branch branch = new Branch();
        branch.setName("Range Report Branch");
        branch.setPhone("9999999999");
        AddResponseJSON createResponse = superAdminService.createBranch(branch);

        LocalDate startDate = LocalDate.now().minusDays(30);
        LocalDate endDate = LocalDate.now();

        GetReportJSON report = superAdminService.getRangeReport(
                createResponse.getCode(),
                startDate,
                endDate
        );

        assertNotNull(report);
    }

    @Test
    void getBranchByCode() {
        Branch branch = new Branch();
        branch.setName("Branch for Code Test");
        branch.setPhone("0000000000");
        AddResponseJSON createResponse = superAdminService.createBranch(branch);

        Branch retrievedBranch = superAdminService.getBranchByCode(createResponse.getCode());

        assertNull(retrievedBranch);

    }

    @Test
    void main() {
        assertDoesNotThrow(() -> SuperAdminService.main(new String[]{}));
    }
}