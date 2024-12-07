package SCD.model.service.Common;

import SCD.model.crud.BranchesDAO;
import SCD.model.crud.EmployeeDAO;
import SCD.model.models.Employee;
import SCD.model.service.AddResponseJSON;

public class CommonServices {

  EmployeeDAO employeeDAO;
  BranchesDAO branchesDAO;

  public CommonServices() {
    employeeDAO = EmployeeDAO.getInstance();
    branchesDAO = BranchesDAO.getInstance();
  }

  public Employee Login(String emp_code, String password, String role) {

    return employeeDAO.loginWithRole(emp_code, password, role);

  }

  public boolean setFirstLoginStatus(String emp_code) {

    return employeeDAO.setFirstLoginToFalse(emp_code);

  }

  public boolean update_password(String employeeCode, String newPassword) {

    return employeeDAO.updatePassword(employeeCode, newPassword);

  }

  public AddResponseJSON AddEmployee(Employee employee) {

    boolean res = employeeDAO.isEmailExists(employee.getEmail());

    if (res) {
      return new AddResponseJSON("Employee already exists", false);
    }

    if (branchesDAO.getBranchByCode(employee.getBranch().getBranchCode()) == null) {
      return new AddResponseJSON("Branch does not exist", false);
    }

    employee.setEmail(LowerCaseEmail(employee.getEmail()));
    employeeDAO.addEmployee(employee);

    return new AddResponseJSON("Employee added successfully", true);

  }

  public AddResponseJSON RemoveEmployee(String emp_code) {
    boolean res = employeeDAO.employeeExistsByEmployeeCode(emp_code);

    if (!res) {
      return new AddResponseJSON("Employee does not exist", false);
    }

    res = employeeDAO.deactivateEmployee(emp_code);

    if (!res) {
      return new AddResponseJSON("Employee cannot be removed", false);
    }

    return new AddResponseJSON("Employee removed successfully", true);

  }

  public String LowerCaseEmail(String email) {
    return email.toLowerCase();
  }

  public AddResponseJSON UpdateEmployee(Employee employee) {

    boolean res = employeeDAO.employeeExistsByEmployeeCode(employee.getEmployeeCode());
    if (!res) {
      return new AddResponseJSON("Employee does not exist", false);
    }
    res = employeeDAO.updateEmployee(employee);
    if (!res) {
      return new AddResponseJSON("Employee cannot be updated", false);
    }
    return new AddResponseJSON("Employee updated successfully", true);
  }

}
