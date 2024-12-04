package SCD.model.service.Common;

import SCD.model.crud.BranchesDAO;
import SCD.model.crud.EmployeeDAO;
import SCD.model.models.Employee;
import SCD.model.service.AddResponseClass;

public class CommonServices {

  EmployeeDAO employeeDAO;
  BranchesDAO branchesDAO;

  public CommonServices() {
    employeeDAO = new EmployeeDAO();
    branchesDAO = new BranchesDAO();
  }

  public Employee Login(String emp_code, String password) {

    return employeeDAO.login(emp_code, password);

  }

  public boolean setActiveStatus(String emp_code) {

    return employeeDAO.setFirstLoginToFalse(emp_code);

  }

  public boolean update_password(String employeeCode, String newPassword) {

    return employeeDAO.updatePassword(employeeCode, newPassword);

  }

  public AddResponseClass AddEmployee(Employee employee) {

    boolean res = employeeDAO.employeeExistsByEmployeeCode(employee.getEmployeeCode());

    if (res) {
      return new AddResponseClass("Employee already exists", false);
    }

    if (branchesDAO.getBranchByCode(employee.getBranch().getBranchCode()) == null) {
      return new AddResponseClass("Branch does not exist", false);
    }

    employeeDAO.addEmployee(employee);

    return new AddResponseClass("Employee added successfully", true);

  }

  public AddResponseClass RemoveEmployee(String emp_code) {
    boolean res = employeeDAO.employeeExistsByEmployeeCode(emp_code);

    if (!res) {
      return new AddResponseClass("Employee does not exist", false);
    }

    res = employeeDAO.removeEmployee(emp_code);

    if (!res) {
      return new AddResponseClass("Employee cannot be removed", false);
    }

    return new AddResponseClass("Employee removed successfully", true);

  }

}
