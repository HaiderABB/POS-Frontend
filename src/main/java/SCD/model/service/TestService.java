package SCD.model.service;

import SCD.model.models.Branch;
import SCD.model.models.Employee;
import SCD.model.service.Common.CommonServices;
import SCD.model.service.SuperAdminService.SuperAdminService;

public class TestService {

        public static void main(String[] args) {

                CommonServices commonServices = new CommonServices();
                SuperAdminService superAdminService = new SuperAdminService();
                ;

                Branch branch = new Branch("BR-0001", "Main Branch", "Lahore", "Gulberg Main Boulevard", "03219306126");

                // superAdminService.createBranch(branch);

                Employee employee = new Employee("SM-0002", "Haider Abbas Moazzam",
                                "SUPER_ADMIN", branch, "03219306127", 20000,
                                "haider.a.moazzam@gmail.com");

                // commonServices.AddEmployee(employee);

        }

}
