package SCD.model.service.Common;

import SCD.model.db.local.BranchesDAO;
import SCD.model.db.local.CodesDAO;
import SCD.model.db.local.EmployeeDAO;
import SCD.model.db.local.SyncTableDAO;
import SCD.model.models.Branch;
import SCD.model.models.Employee;
import SCD.model.models.SyncTable;
import SCD.model.service.Json.AddResponseJSON;

public class CommonServices {

  public EmployeeDAO employeeDAO;
  public BranchesDAO branchesDAO;
  public CodesDAO codesDAO;
  public SyncTableDAO syncTableDAO;

  public CommonServices() {
    employeeDAO = EmployeeDAO.getInstance();
    branchesDAO = BranchesDAO.getInstance();
    codesDAO = CodesDAO.getInstance();
    syncTableDAO = SyncTableDAO.getInstance();
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
    syncTableDAO.addSyncTable(st);

    return true;

  }

  public String getCurrentPassword(String emp_code) {

    Employee employee = employeeDAO.getEmployeeByEmployeeCode(emp_code);

    if (employee == null) {
      return "";
    }

    return employee.getPassword();

  }

  public boolean update_password(String employeeCode, String newPassword) {

    boolean res = employeeDAO.updatePassword(employeeCode, newPassword);

    if (!res) {
      return false;
    }

    SyncTable st = new SyncTable("EMPLOYEES", "UPDATE", employeeCode);
    syncTableDAO.addSyncTable(st);
    return res;

  }

  public boolean checkEmailExists(String email) {
    boolean res = employeeDAO.isEmailExists(email);
    return res;
  }

  public boolean checkBranchExists(String branchCode) {
    Branch br = branchesDAO.getBranchByCode(branchCode);

    if (br == null) {
      return false;
    }
    return true;
  }

  public boolean checkPhoneNumberExists(String phoneNumber) {
    boolean res = employeeDAO.isPhoneNumberExists(phoneNumber);
    return res;
  }

  public AddResponseJSON AddEmployee(Employee employee) {

    String employeeCode = codesDAO.getCodeByTableName("EMPLOYEES");
    System.out.println(employeeCode);

    employeeCode = incrementCode(employeeCode);
    System.out.println(employeeCode);
    String temp;

    if (employee.getRole().equals("MANAGER")) {
      temp = "BM-" + employeeCode;
      employee.setSalary(50000);
    } else if (employee.getRole().equals("DATA_ENTRY_OPERATOR")) {
      temp = "DM-" + employeeCode;
      employee.setSalary(30000);
    } else if (employee.getRole().equals("CASHIER")) {
      temp = "CM-" + employeeCode;
      employee.setSalary(20000);
    } else {
      temp = "SM-" + employeeCode;
      employee.setSalary(100000);
    }
    System.out.println(temp);

    employee.setEmployeeCode(temp);
    employee.setPassword("first1234");

    employee.setEmail(LowerCaseEmail(employee.getEmail()));

    boolean res = employeeDAO.addEmployee(employee);
    System.out.println(res);

    if (!res) {
      return new AddResponseJSON("Error Adding Employee", false, null);
    }

    boolean check = codesDAO.updateCodeByTableName("EMPLOYEES", employeeCode);

    if (check && res) {
      SyncTable st1 = new SyncTable("CODES", "UPDATE", "EMPLOYEES");
      syncTableDAO.addSyncTable(st1);
      SyncTable st = new SyncTable("EMPLOYEES", "INSERT", temp);
      syncTableDAO.addSyncTable(st);
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

  public boolean checkEmployeeExists(String emp_code) {

    Employee employee = employeeDAO.getEmployeeByEmployeeCode(emp_code);
    if (employee == null) {
      return false;
    }
    return true;

  }

  public AddResponseJSON RemoveEmployee(String emp_code) {

    boolean res = employeeDAO.deactivateEmployee(emp_code);

    if (!res) {
      return new AddResponseJSON("Employee cannot be removed", false, null);
    }

    SyncTable st = new SyncTable("EMPLOYEES", "UPDATE", emp_code);
    syncTableDAO.addSyncTable(st);

    return new AddResponseJSON("Employee removed successfully", true, emp_code);

  }

  public String LowerCaseEmail(String email) {
    return email.toLowerCase();
  }

  public AddResponseJSON UpdateEmployee(Employee employee) {

    boolean res = employeeDAO.updateEmployee(employee);
    if (!res) {
      return new AddResponseJSON("Employee cannot be updated", false, employee.getEmployeeCode());
    }

    SyncTable st = new SyncTable("EMPLOYEES", "UPDATE", employee.getEmployeeCode());
    syncTableDAO.addSyncTable(st);
    return new AddResponseJSON("Employee updated successfully", true, employee.getEmployeeCode());
  }

  public Employee getEmployeeByEmployeeCode(String emp_code) {

    return employeeDAO.getEmployeeByEmployeeCode(emp_code);

  }

  public static void main(String[] args) {
    CommonServices cs = new CommonServices();
    System.out.println(cs.checkPhoneNumberExists("03219306127"));
  }

}
