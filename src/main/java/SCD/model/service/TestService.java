package SCD.model.service;

import SCD.model.crud.CodesDAO;
import SCD.model.models.Branch;
import SCD.model.models.Codes;
import SCD.model.models.Employee;
import SCD.model.service.Common.CommonServices;
import SCD.model.service.SuperAdminService.SuperAdminService;

public class TestService {

        public static void main(String[] args) {

                CommonServices commonServices = new CommonServices();
                SuperAdminService superAdminService = new SuperAdminService();
                CodesDAO codesDAO = CodesDAO.getInstance();

                Codes codes1 = new Codes("0000", "EMPLOYEES");
                Codes codes2 = new Codes("0000", "BRANCHES");
                Codes codes3 = new Codes("0000", "VENDORS");
                Codes codes4 = new Codes("0000", "PRODUCTS");

                codesDAO.addCode(codes1);
                codesDAO.addCode(codes2);
                codesDAO.addCode(codes3);
                codesDAO.addCode(codes4);

                Branch branch = new Branch("Main Branch", "Lahore", "Gulberg Main Boulevard", "03219306126");

                superAdminService.createBranch(branch);

                Employee employee = new Employee("Haider Abbas Moazzam",
                                "SUPER_ADMIN", branch, "03219306127", 20000,
                                "haider.a.moazzam@gmail.com");

                commonServices.AddEmployee(employee);

        }

}
