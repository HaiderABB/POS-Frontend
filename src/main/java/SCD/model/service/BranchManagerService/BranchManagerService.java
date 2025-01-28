package SCD.model.service.BranchManagerService;

import java.util.List;

import SCD.model.db.local.EmployeeDAO;
import SCD.model.db.local.SaleDAO;
import SCD.model.models.Employee;
import SCD.model.models.Sale;
import SCD.model.service.Json.GetResponseJSON;

public class BranchManagerService {

  EmployeeDAO employeeDAO;

  public BranchManagerService() {
    employeeDAO = EmployeeDAO.getInstance();
  }

  public GetResponseJSON<Employee> getDataEntryOperators(String code) {

    List<Employee> emps = employeeDAO.getActiveDEOByBranch(code);

    if (emps == null) {
      return new GetResponseJSON<>("Employees not found", emps);

    }

    return new GetResponseJSON<>("Employees found", emps);

  }

  public GetResponseJSON<Employee> getCashiers(String branchCode) {

    List<Employee> emps = employeeDAO.getActiveCashiersByBranch(branchCode);

    if (emps == null) {
      return new GetResponseJSON<>("Employees not found", emps);

    }

    return new GetResponseJSON<>("Employees found", emps);

  }

  public GetResponseJSON<Sale> getSalesByBranch(String branchCode) {

    SaleDAO saleDAO = SaleDAO.getInstance();

    List<Sale> sales = saleDAO.getSalesByBranch(branchCode);

    if (sales == null || sales.size() == 0) {
      return new GetResponseJSON<>("Sales not found", null);
    }
    return new GetResponseJSON<>("Sales found", sales);

  }

  public static void main(String[] args) {
    BranchManagerService service = new BranchManagerService();
    GetResponseJSON<Employee> res = service.getCashiers("BR-0002");
    System.out.println(res.getData().size());
  }

}
