package SCD.model.service.BranchManagerService;

import java.util.List;

import SCD.model.crud.EmployeeDAO;
import SCD.model.models.Employee;
import SCD.model.service.GetResponseClass;

public class BranchManagerService {

  EmployeeDAO employeeDAO;

  public BranchManagerService() {
    employeeDAO = EmployeeDAO.getInstance();
  }

  public GetResponseClass<Employee> getDataEntryOperators() {

    List<Employee> emps = employeeDAO.getEmployeesByRoleDataEntryOperator();

    if (emps.isEmpty()) {
      return new GetResponseClass<>("Employees not found", emps);

    }

    return new GetResponseClass<>("Employees found", emps);

  }

  public GetResponseClass<Employee> getCashiers() {

    List<Employee> emps = employeeDAO.getEmployeesByRoleCashier();

    if (emps.isEmpty()) {
      return new GetResponseClass<>("Employees not found", emps);

    }

    return new GetResponseClass<>("Employees found", emps);

  }

}
