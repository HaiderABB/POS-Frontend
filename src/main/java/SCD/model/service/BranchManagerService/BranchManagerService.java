package SCD.model.service.BranchManagerService;

import java.util.List;

import SCD.model.crud.EmployeeDAO;
import SCD.model.models.Employee;

public class BranchManagerService {

  EmployeeDAO employeeDAO;

  public BranchManagerService() {
    employeeDAO = new EmployeeDAO();
  }

  public List<Employee> getDataEntryOperators() {

    List<Employee> emps = employeeDAO.getEmployeesByRoleDataEntryOperator();

    return emps;

  }

  public List<Employee> getCashiers() {

    List<Employee> emps = employeeDAO.getEmployeesByRoleCashier();

    return emps;

  }

}
