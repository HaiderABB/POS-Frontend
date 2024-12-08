package SCD.model.service.SuperAdminService;

import java.time.LocalDate;
import java.util.List;

import SCD.model.crud.local.BranchesDAO;
import SCD.model.crud.local.CodesDAO;
import SCD.model.crud.local.ProductDAO;
import SCD.model.crud.local.SaleDAO;
import SCD.model.crud.local.SyncTableDAO;
import SCD.model.models.Branch;
import SCD.model.models.Product;
import SCD.model.models.Sale;
import SCD.model.models.SyncTable;
import SCD.model.service.Json.AddResponseJSON;
import SCD.model.service.Json.GetReportJSON;
import SCD.model.service.Json.GetResponseJSON;

public class SuperAdminService {

    BranchesDAO branchesDAO;
    ProductDAO productDAO;
    SaleDAO saleDAO;
    CodesDAO codesDAO;
    SyncTableDAO syncTableDAO;

    public SuperAdminService() {
        branchesDAO = BranchesDAO.getInstance();
        productDAO = ProductDAO.getInstance();
        saleDAO = SaleDAO.getInstance();
        codesDAO = CodesDAO.getInstance();
        syncTableDAO = SyncTableDAO.getInstance();

    }

    public String incrementCode(String code) {

        int numericCode = Integer.parseInt(code);

        // Increment the numeric part
        numericCode++;

        return String.format("%04d", numericCode);
    }

    public AddResponseJSON createBranch(Branch branch) {

        boolean br = branchesDAO.doesBranchExistWithPhone(branch.getPhone());

        if (br) {
            return new AddResponseJSON("Branch Exists with phone number", false, null);
        }

        String empcode = codesDAO.getCodeByTableName("BRANCHES");

        empcode = incrementCode(empcode);
        String temp = "BR-" + empcode;
        branch.setBranchCode(temp);
        codesDAO.updateCodeByTableName("BRANCHES", empcode);

        boolean res = branchesDAO.addBranch(branch);

        if (res) {
            SyncTable st = new SyncTable("BRANCHES", "INSERT", temp);
            syncTableDAO.addSyncTable(st);
            SyncTable st1 = new SyncTable("CODES", "UPDATE", "BRANCHES");
            syncTableDAO.addSyncTable(st1);
            return new AddResponseJSON("Branch Creation Successful", true, temp);
        }

        return new AddResponseJSON("Error Creating a branch", false, null);

    }

    public AddResponseJSON deleteBranch(String branch_code) {
        Branch br = branchesDAO.getBranchByCode(branch_code);
        if (br == null) {
            return new AddResponseJSON("Branch does not exist", false, null);
        }
        boolean res;

        res = branchesDAO.getBranchActiveStatus(branch_code);
        if (res == false) {
            return new AddResponseJSON("Branch already deleted", true, null);
        }

        res = branchesDAO.deleteBranch(branch_code);
        if (!res) {
            return new AddResponseJSON("Branch Deletion Failed", false, null);
        }

        SyncTable st = new SyncTable("BRANCHES", "UPDATE", branch_code);
        syncTableDAO.addSyncTable(st);

        return new AddResponseJSON("Branch Deletion Successful", res, null);

    }

    public AddResponseJSON updateBranch(Branch branch) {
        System.out.println(branch.getBranchCode());
        Branch br = branchesDAO.getBranchByCode(branch.getBranchCode());
        if (br == null) {
            return new AddResponseJSON("Branch does not exist", false, null);
        }

        boolean res;
        res = branchesDAO.updateBranch(branch);

        if (!res) {
            return new AddResponseJSON("Branch Update Failed", false, null);
        }

        SyncTable st = new SyncTable("BRANCHES", "UPDATE", branch.getBranchCode());
        syncTableDAO.addSyncTable(st);

        return new AddResponseJSON("Branch Update Successful", res, null);

    }

    public GetResponseJSON<Branch> getBranches() {
        List<Branch> branches = branchesDAO.getAllActiveBranches();
        if (branches.isEmpty()) {
            return new GetResponseJSON<>("No Branches", branches);
        }
        return new GetResponseJSON<>("Found Branches", branches);
    }

    public GetReportJSON getTodaysReport(String code) {
        Branch branch = branchesDAO.getBranchByCode(code);
        if (branch == null || branch.isActive() == false) {
            System.err.println("Branch not found");
            return new GetReportJSON(null, null, 0.0, "Branch not found");
        }
        List<Product> products = productDAO.getAllActiveProducts();
        if (products.isEmpty()) {
            System.err.println("No Products");
            return new GetReportJSON(null, null, 0.0, "No Products");
        }
        List<Sale> sales = saleDAO.getSalesForDay(LocalDate.now(), code);
        if (sales.isEmpty()) {
            System.err.println("No Sales");
            return new GetReportJSON(null, null, 0.0, "No Sales");
        }
        double profit = 0.0;
        for (Sale sl : sales) {
            profit += sl.getProfit();
        }
        return new GetReportJSON(sales, products, profit, "Report Generated");

    }

    public GetReportJSON getWeeklyReport(String code) {
        Branch branch = branchesDAO.getBranchByCode(code);
        if (branch == null || branch.isActive() == false) {
            System.err.println("Branch not found");
            return new GetReportJSON(null, null, 0.0, "Branch not found");
        }
        List<Product> products = productDAO.getAllActiveProducts();
        if (products.isEmpty()) {
            System.err.println("No Products");
            return new GetReportJSON(null, null, 0.0, "No Products");
        }

        List<Sale> sales = saleDAO.getSalesForWeek(LocalDate.now(), code);
        if (sales.isEmpty()) {
            System.err.println("No Sales");
            return new GetReportJSON(null, null, 0.0, "No Sales");
        }
        double profit = 0.0;
        for (Sale sl : sales) {
            profit += sl.getProfit();
        }

        return new GetReportJSON(sales, products, profit, "Report Generated");

    }

    public GetReportJSON getMonthlyReport(String code) {
        Branch branch = branchesDAO.getBranchByCode(code);
        if (branch == null || branch.isActive() == false) {
            System.err.println("Branch not found");
            return new GetReportJSON(null, null, 0, "Branch not found");
        }
        List<Product> products = productDAO.getAllActiveProducts();
        if (products.isEmpty()) {
            System.err.println("No Products");
            return new GetReportJSON(null, null, 0, "No Products");
        }

        List<Sale> sales = saleDAO.getSalesForMonth(LocalDate.now(), code);
        if (sales.isEmpty()) {
            System.err.println("No Sales");
            return new GetReportJSON(null, null, 0, "No Sales");
        }
        double profit = 0.0;
        for (Sale sl : sales) {
            profit += sl.getProfit();
        }
        return new GetReportJSON(sales, products, profit, "Report Generated");

    }

    public GetReportJSON getYearlyReport(String code) {
        Branch branch = branchesDAO.getBranchByCode(code);
        if (branch == null || branch.isActive() == false) {
            System.err.println("Branch not found");
            return new GetReportJSON(null, null, 0, "Branch not found");
        }
        List<Product> products = productDAO.getAllActiveProducts();
        if (products.isEmpty()) {
            System.err.println("No Products");
            return new GetReportJSON(null, null, 0, "No Products");
        }
        int year = LocalDate.now().getYear();
        List<Sale> sales = saleDAO.getSalesForYear(year, code);
        if (sales.isEmpty()) {
            System.err.println("No Sales");
            return new GetReportJSON(null, null, 0, "No Sales");
        }
        double profit = 0.0;
        for (Sale sl : sales) {
            profit += sl.getProfit();
        }
        return new GetReportJSON(sales, products, profit, "Report Generated");

    }

    public GetReportJSON getRangeReport(String code, LocalDate startDate, LocalDate endDate) {
        Branch branch = branchesDAO.getBranchByCode(code);
        if (branch == null || branch.isActive() == false) {
            System.err.println("Branch not found");
            return new GetReportJSON(null, null, 0, "Branch not found");
        }
        List<Product> products = productDAO.getAllActiveProducts();
        if (products.isEmpty()) {
            System.err.println("No Products");
            return new GetReportJSON(null, null, 0, "No Products");
        }
        List<Sale> sales = saleDAO.getSalesForDateRange(startDate, endDate, code);
        if (sales.isEmpty()) {
            System.err.println("No Sales");
            return new GetReportJSON(null, null, 0, "No Sales");
        }
        double profit = 0.0;
        for (Sale sl : sales) {
            profit += sl.getProfit();
        }
        return new GetReportJSON(sales, products, profit, "Report Generated");
    }

    public Branch getBranchByCode(String code) {
        return branchesDAO.getBranchByCode(code);
    }

}
