package SCD.model.service.SuperAdminService;

import java.time.LocalDate;
import java.util.List;

import SCD.model.crud.BranchesDAO;
import SCD.model.crud.ProductDAO;
import SCD.model.crud.SaleDAO;
import SCD.model.crud.SaleItemDAO;
import SCD.model.models.Branch;
import SCD.model.models.Product;
import SCD.model.models.Sale;
import SCD.model.service.AddResponseJSON;
import SCD.model.service.GetReportJSON;
import SCD.model.service.GetResponseJSON;

public class SuperAdminService {

    BranchesDAO branchesDAO;
    ProductDAO productDAO;
    SaleDAO saleDAO;
    SaleItemDAO saleItemDAO;

    public SuperAdminService() {
        branchesDAO = BranchesDAO.getInstance();
        productDAO = ProductDAO.getInstance();
        saleDAO = SaleDAO.getInstance();
        saleItemDAO = SaleItemDAO.getInstance();

    }

    public AddResponseJSON createBranch(Branch branch) {

        boolean br = branchesDAO.doesBranchExistWithPhone(branch.getPhone());

        if (br) {
            return new AddResponseJSON("Branch Exists with phone number", false);
        }
        boolean res = branchesDAO.addBranch(branch);

        if (res) {
            return new AddResponseJSON("Branch Creation Successful", true);
        }
        return new AddResponseJSON(null, false);

    }

    public AddResponseJSON deleteBranch(String branch_code) {
        Branch br = branchesDAO.getBranchByCode(branch_code);
        if (br == null) {
            return new AddResponseJSON("Branch does not exist", false);
        }
        boolean res;

        res = branchesDAO.getBranchActiveStatus(branch_code);
        if (res == false) {
            return new AddResponseJSON("Branch already deleted", true);
        }

        res = branchesDAO.deleteBranch(branch_code);

        return new AddResponseJSON("Branch Deletion Successful", res);

    }

    public AddResponseJSON updateBranch(Branch branch) {
        Branch br = branchesDAO.getBranchByCode(branch.getBranchCode());
        if (br == null) {
            return new AddResponseJSON("Branch does not exist", false);
        }

        boolean res;
        res = branchesDAO.updateBranch(branch);

        if (!res) {
            return new AddResponseJSON("Branch Update Failed", false);
        }

        return new AddResponseJSON("Branch Update Successful", res);

    }

    public GetResponseJSON<Branch> getBranches() {
        List<Branch> branches = branchesDAO.getAllActiveBranches();
        if (branches.isEmpty()) {
            return new GetResponseJSON<>("No Branches", branches);
        }
        return new GetResponseJSON<>("Found Branches", branches);
    }

    public GetReportJSON getDayReport(String code, LocalDate day) {

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

        List<Sale> sales = saleDAO.getSalesForDay(day, code);
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

    public GetReportJSON getWeeklyReport(String code, LocalDate date) {
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

        List<Sale> sales = saleDAO.getSalesForWeek(date, code);
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

    public GetReportJSON getMonthlyReport(String code, LocalDate date) {
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

        List<Sale> sales = saleDAO.getSalesForMonth(date, code);
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

    public GetReportJSON getYearlyReport(String code, int year) {
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

}
