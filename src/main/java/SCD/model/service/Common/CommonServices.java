package SCD.model.service.Common;

import SCD.model.crud.local.BranchesDAO;
import SCD.model.crud.local.CodesDAO;
import SCD.model.crud.local.EmployeeDAO;
import SCD.model.crud.local.SyncTableDAO;
import SCD.model.models.Employee;
import SCD.model.models.SyncTable;
import SCD.model.service.Json.AddResponseJSON;

public class CommonServices {

  public EmployeeDAO employeeDAO;
  public BranchesDAO branchesDAO;
  public CodesDAO codesDAO;
  public SyncTableDAO syncTable;

  public CommonServices() {
    employeeDAO = EmployeeDAO.getInstance();
    branchesDAO = BranchesDAO.getInstance();
    codesDAO = CodesDAO.getInstance();
    syncTable = SyncTableDAO.getInstance();
  }

  public Employee Login(String emp_code, String password, String role) {

    return employeeDAO.loginWithRole(emp_code, password, role);

  }

  public boolean setFirstLoginStatus(String emp_code) {

    boolean res = employeeDAO.setFirstLoginToFalse(emp_code);

    if (!res) {
      return false;
    }
    SyncTable st = new SyncTable("EMPLOYEES", "UPDATE", emp_code);
    syncTable.addSyncTable(st);

    return employeeDAO.setFirstLoginToFalse(emp_code);

  }

  public boolean update_password(String employeeCode, String newPassword) {

    boolean res = employeeDAO.updatePassword(employeeCode, newPassword);

    if (!res) {
      return false;
    }

    SyncTable st = new SyncTable("EMPLOYEES", "UPDATE", employeeCode);
    syncTable.addSyncTable(st);
    return employeeDAO.updatePassword(employeeCode, newPassword);

  }

  public AddResponseJSON AddEmployee(Employee employee) {

    boolean res = employeeDAO.isEmailExists(employee.getEmail());

    if (res) {
      return new AddResponseJSON("Employee already exists", false, null);
    }

    if (branchesDAO.getBranchByCode(employee.getBranch().getBranchCode()) == null) {
      return new AddResponseJSON("Branch does not exist", false, null);
    }

    String employeeCode = codesDAO.getCodeByTableName("EMPLOYEES");

    employeeCode = incrementCode(employeeCode);
    String temp;

    if (employee.getRole().equals("MANAGER")) {
      temp = "BM-" + employeeCode;
    } else if (employee.getRole().equals("DATA_ENTRY_OPERATOR")) {
      temp = "DM-" + employeeCode;
    } else if (employee.getRole().equals("CASHIER")) {
      temp = "CM-" + employeeCode;
    } else {
      temp = "SM-" + employeeCode;
    }

    employee.setEmployeeCode(temp);

    employee.setEmail(LowerCaseEmail(employee.getEmail()));

    res = employeeDAO.addEmployee(employee);

    if (!res) {
      return new AddResponseJSON("Error Adding Employee", false, null);
    }

    boolean check = codesDAO.updateCodeByTableName("EMPLOYEES", employeeCode);
    SyncTable st = new SyncTable("EMPLOYEES", "INSERT", temp);
    syncTable.addSyncTable(st);
    if (check) {
      SyncTable st1 = new SyncTable("CODES", "UPDATE", "EMPLOYEES");
      syncTable.addSyncTable(st1);
    } else {
      return new AddResponseJSON("Error Adding Employee", false, null);
    }

    return new AddResponseJSON("Employee added successfully", true, temp);

  }

  public String incrementCode(String code) {

    int numericCode = Integer.parseInt(code);

    numericCode++;

    return String.format("%04d", numericCode);
  }

  public AddResponseJSON RemoveEmployee(String emp_code) {
    boolean res = employeeDAO.employeeExistsByEmployeeCode(emp_code);

    if (!res) {
      return new AddResponseJSON("Employee does not exist", false, null);
    }

    res = employeeDAO.deactivateEmployee(emp_code);

    if (!res) {
      return new AddResponseJSON("Employee cannot be removed", false, null);
    }

    SyncTable st = new SyncTable("EMPLOYEES", "UPDATE", emp_code);
    syncTable.addSyncTable(st);

    return new AddResponseJSON("Employee removed successfully", true, emp_code);

  }

  public String LowerCaseEmail(String email) {
    return email.toLowerCase();
  }

  public AddResponseJSON UpdateEmployee(Employee employee) {

    boolean res = employeeDAO.employeeExistsByEmployeeCode(employee.getEmployeeCode());
    if (!res) {
      return new AddResponseJSON("Employee does not exist", false, employee.getEmployeeCode());
    }
    res = employeeDAO.updateEmployee(employee);
    if (!res) {
      return new AddResponseJSON("Employee cannot be updated", false, employee.getEmployeeCode());
    }

    SyncTable st = new SyncTable("EMPLOYEES", "UPDATE", employee.getEmployeeCode());
    syncTable.addSyncTable(st);
    return new AddResponseJSON("Employee updated successfully", true, employee.getEmployeeCode());
  }

}
