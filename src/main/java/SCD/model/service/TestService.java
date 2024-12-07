package SCD.model.service;

import java.util.ArrayList;
import java.util.List;

import SCD.model.models.Branch;
import SCD.model.models.Employee;
import SCD.model.models.Product;
import SCD.model.models.SaleItem;
import SCD.model.models.Vendor;
import SCD.model.service.BranchManagerService.BranchManagerService;
import SCD.model.service.CashierService.CashierService;
import SCD.model.service.Common.CommonServices;
import SCD.model.service.DataEntryOperatorService.DataEntryOperatorService;
import SCD.model.service.SuperAdminService.SuperAdminService;

public class TestService {

        public static void main(String[] args) {

                CommonServices commonServices = new CommonServices();
                SuperAdminService superAdminService = new SuperAdminService();
                BranchManagerService branchManagerService = new BranchManagerService();
                DataEntryOperatorService dataEntryOperatorService = new DataEntryOperatorService();
                CashierService cashierService = new CashierService();

                Branch branch = new Branch("BR-0001", "Main Branch", "Lahore", "Gulberg Main Boulevard", "03219306126");

                // superAdminService.createBranch(branch);

                Employee employee = new Employee("SM-0002", "Haider Abbas Moazzam",
                                "SUPER_ADMIN", branch, "03219306127", 20000,
                                "haider.a.moazzam@gmail.com");

                Employee employee1 = new Employee("CM-0002", "Cashier1",
                                "CASHIER", branch, "03219306128", 20000,
                                "haider.moazzam@gmail.com");

                // commonServices.AddEmployee(employee);
                // commonServices.AddEmployee(employee1);

                Vendor vendor = new Vendor("VM-0001", "Vendor1", "03219306129", "test address");

                Vendor vendor2 = new Vendor("VM-0002", "Vendor2", "03219306130", "test address");

                // dataEntryOperatorService.addVendor(vendor);
                // dataEntryOperatorService.addVendor(vendor2);

                Product product1 = new Product("PM-0001", vendor, "Product1", "Electronics", 100.0, 200.0, 350.0,
                                1000.0, 100);
                Product product2 = new Product("PM-0002", vendor, "Product2", "Groceries", 50.0, 90.0, 150.0, 500.0,
                                200);
                Product product3 = new Product("PM-0003", vendor2, "Product3", "Apparel", 300.0, 450.0, 500.0, 1200.0,
                                50);
                Product product4 = new Product("PM-0004", vendor2, "Product4", "Books", 20.0, 35.0, 40.0, 100.0, 300);
                Product product5 = new Product("PM-0005", vendor, "Product5", "Toys", 75.0, 150.0, 250.0, 800.0, 80);
                Product product6 = new Product("PM-0006", vendor2, "Product6", "Beauty", 40.0, 80.0, 120.0, 400.0, 150);
                Product product7 = new Product("PM-0007", vendor, "Product7", "Home Appliances", 500.0, 750.0, 1000.0,
                                3000.0, 20);
                Product product8 = new Product("PM-0008", vendor2, "Product8", "Furniture", 800.0, 1200.0, 1600.0,
                                5000.0, 10);
                Product product9 = new Product("PM-0009", vendor2, "Product9", "Kitchenware", 25.0, 50.0, 90.0, 300.0,
                                100);
                Product product10 = new Product("PM-0010", vendor, "Product10", "Sports", 150.0, 300.0, 400.0, 1000.0,
                                60);

                // dataEntryOperatorService.addProduct(product1);
                // dataEntryOperatorService.addProduct(product2);
                // dataEntryOperatorService.addProduct(product3);
                // dataEntryOperatorService.addProduct(product4);
                // dataEntryOperatorService.addProduct(product5);
                // dataEntryOperatorService.addProduct(product6);
                // dataEntryOperatorService.addProduct(product7);
                // dataEntryOperatorService.addProduct(product8);
                // dataEntryOperatorService.addProduct(product9);
                // dataEntryOperatorService.addProduct(product10);

                SaleItem saleItem1 = new SaleItem(product1, 5, product1.getSalePrice(), product1.getOriginalPrice());
                SaleItem saleItem2 = new SaleItem(product2, 3, product2.getSalePrice(), product2.getOriginalPrice());
                SaleItem saleItem3 = new SaleItem(product3, 2, product3.getSalePrice(), product3.getOriginalPrice());
                SaleItem saleItem4 = new SaleItem(product4, 4, product4.getSalePrice(), product4.getOriginalPrice());
                SaleItem saleItem5 = new SaleItem(product5, 6, product5.getSalePrice(), product5.getOriginalPrice());
                SaleItem saleItem6 = new SaleItem(product6, 8, product6.getSalePrice(), product6.getOriginalPrice());

                List<SaleItem> saleItems = new ArrayList<>();
                saleItems.add(saleItem1);
                saleItems.add(saleItem2);
                saleItems.add(saleItem3);
                saleItems.add(saleItem4);
                saleItems.add(saleItem5);
                saleItems.add(saleItem6);

                double totalAmount = cashierService.GenerateBill(saleItems);
                System.out.println("Total Amount: " + totalAmount);
                AddResponseClass response = cashierService.proceedPayment(saleItems,
                                employee1, branch, totalAmount);
                System.out.println(response.getMessage());

        }

}
