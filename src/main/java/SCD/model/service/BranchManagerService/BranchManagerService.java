package SCD.model.service.BranchManagerService;

import java.util.List;

import SCD.model.crud.EmployeeDAO;
import SCD.model.models.Employee;
import SCD.model.service.GetResponseJSON;

public class BranchManagerService {

  EmployeeDAO employeeDAO;

  public BranchManagerService() {
    employeeDAO = EmployeeDAO.getInstance();
  }

  public GetResponseJSON<Employee> getDataEntryOperators() {

    List<Employee> emps = employeeDAO.getEmployeesByRoleDataEntryOperator();

    if (emps.isEmpty()) {
      return new GetResponseJSON<>("Employees not found", emps);

    }

    return new GetResponseJSON<>("Employees found", emps);

  }

  public GetResponseJSON<Employee> getCashiers() {

    List<Employee> emps = employeeDAO.getEmployeesByRoleCashier();

    if (emps.isEmpty()) {
      return new GetResponseJSON<>("Employees not found", emps);

    }

    return new GetResponseJSON<>("Employees found", emps);

  }

}
