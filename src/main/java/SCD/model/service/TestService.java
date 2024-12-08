package SCD.model.service;

import java.util.ArrayList;
import java.util.List;

import SCD.model.crud.local.CodesDAO;
import SCD.model.models.Branch;
import SCD.model.models.Codes;
import SCD.model.models.Employee;
import SCD.model.models.Product;
import SCD.model.models.SaleItem;
import SCD.model.models.Vendor;
import SCD.model.service.BranchManagerService.BranchManagerService;
import SCD.model.service.CashierService.CashierService;
import SCD.model.service.Common.CommonServices;
import SCD.model.service.DataEntryOperatorService.DataEntryOperatorService;
import SCD.model.service.SuperAdminService.SuperAdminService;
import SCD.model.service.SyncService.DataSync;

public class TestService {

        public static void main(String[] args) {

                CommonServices commonServices = new CommonServices();
                SuperAdminService superAdminService = new SuperAdminService();
                CodesDAO codesDAO = CodesDAO.getInstance();
                DataSync dataSync = new DataSync();
                DataEntryOperatorService dataEntryOperatorService = new DataEntryOperatorService();
                BranchManagerService branchManagerService = new BranchManagerService();

                CashierService cashierService = new CashierService();

                Codes codes1 = new Codes("0000", "EMPLOYEES");
                Codes codes2 = new Codes("0000", "BRANCHES");
                Codes codes3 = new Codes("0000", "VENDORS");
                Codes codes4 = new Codes("0000", "PRODUCTS");

                // codesDAO.addCode(codes1);
                // codesDAO.addCode(codes2);
                // codesDAO.addCode(codes3);
                // codesDAO.addCode(codes4);

                Branch branch = new Branch("Main Branch", "Lahore", "CHAKRI", "03219306126");

                // superAdminService.createBranch(branch);

                Employee employee = new Employee("TEST1",
                                "SUPER_ADMIN", branch, "03219306127", 20000,
                                "haider.a.moazzam@gmail.com");

                // commonServices.AddEmployee(employee);

                // Creating another Branch object
                Branch branch2 = new Branch("THIRD Branch", "Karachi", "Clifton Block 2", "03111234567");

                // Creating another Employee object associated with branch2
                Employee employee2 = new Employee("Ayesha Khan",
                                "CASHIER",
                                branch2,
                                "03121234567",
                                50000,
                                "ayesha.khan@gmail.com");

                // superAdminService.createBranch(branch2);
                // commonServices.AddEmployee(employee2);
                branch.setBranchCode("BR-0001");
                // AddResponseJSON json = superAdminService.updateBranch(branch);
                // System.out.println(json.isSuccess());
                // System.out.println(json.getMessage());

                Vendor vendor = new Vendor("ABC Electronics", "03121234567", "123 Main Street");
                Vendor vendor2 = new Vendor("XYZ Electronics", "03121234789", "456 Elm Street");

                // dataEntryOperatorService.addVendor(vendor);
                // dataEntryOperatorService.addVendor(vendor2);

                employee.setEmployeeCode("SM-0001");
                // commonServices.UpdateEmployee(employee);

                branch2.setBranchCode("BR-0002");
                // superAdminService.updateBranch(branch2);

                vendor.setVendorCode("VM-0001");
                vendor2.setVendorCode("VM-0002");

                Product product1 = new Product(vendor, "Product One", "Category A", 100.0, 90.0, 10.0, 90.0,
                                50);
                Product product2 = new Product(vendor, "Product Two", "Category A", 120.0, 110.0, 12.0, 108.0,
                                40);
                Product product3 = new Product(vendor, "Product Three", "Category B", 200.0, 180.0, 20.0,
                                180.0, 30);
                Product product4 = new Product(vendor, "Product Four", "Category B", 80.0, 70.0, 8.0, 72.0,
                                25);
                Product product5 = new Product(vendor, "Product Five", "Category C", 150.0, 140.0, 15.0, 135.0,
                                60);

                Product product6 = new Product(vendor2, "Product Six", "Category A", 100.0, 95.0, 9.5, 90.0,
                                80);
                Product product7 = new Product(vendor2, "Product Seven", "Category A", 110.0, 100.0, 11.0, 99.0,
                                70);
                Product product8 = new Product(vendor2, "Product Eight", "Category B", 90.0, 85.0, 8.5, 88.0,
                                45);
                Product product9 = new Product(vendor2, "Product Nine", "Category B", 140.0, 130.0, 13.0, 126.0,
                                35);
                Product product10 = new Product(vendor2, "Product Ten", "Category C", 160.0, 150.0, 16.0, 144.0,
                                55);

                // dataEntryOperatorService.addProduct(product10);
                // dataEntryOperatorService.addProduct(product9);
                // dataEntryOperatorService.addProduct(product8);
                // dataEntryOperatorService.addProduct(product7);
                // dataEntryOperatorService.addProduct(product6);
                // dataEntryOperatorService.addProduct(product5);
                // dataEntryOperatorService.addProduct(product4);
                // dataEntryOperatorService.addProduct(product3);
                // dataEntryOperatorService.addProduct(product2);
                // dataEntryOperatorService.addProduct(product1);
                product1.setProductCode("PM-0001");
                product2.setProductCode("PM-0002");
                product3.setProductCode("PM-0003");
                product4.setProductCode("PM-0004");
                product5.setProductCode("PM-0005");
                product6.setProductCode("PM-0006");
                product7.setProductCode("PM-0007");
                product8.setProductCode("PM-0008");
                product9.setProductCode("PM-0009");
                product10.setProductCode("PM-0010");

                // Create SaleItem objects
                SaleItem saleItem1 = new SaleItem(product1, 2, product1.getSalePrice(), product1.getOriginalPrice());
                SaleItem saleItem2 = new SaleItem(product2, 3, product2.getSalePrice(), product2.getOriginalPrice());
                SaleItem saleItem3 = new SaleItem(product3, 1, product3.getSalePrice(), product3.getOriginalPrice());
                SaleItem saleItem4 = new SaleItem(product4, 5, product4.getSalePrice(), product4.getOriginalPrice());
                SaleItem saleItem5 = new SaleItem(product5, 4, product5.getSalePrice(), product5.getOriginalPrice());

                // Add to a list
                List<SaleItem> saleItems = new ArrayList<>();
                saleItems.add(saleItem1);
                saleItems.add(saleItem2);
                saleItems.add(saleItem3);
                saleItems.add(saleItem4);
                saleItems.add(saleItem5);

                Employee cashier = new Employee("Meesum",
                                "CASHIER", branch2, "03334710943", 20000,
                                "cashier@gmail.com");

                // commonServices.AddEmployee(cashier);

                // double totalAmount = cashierService.GenerateBill(saleItems);
                // System.out.println(totalAmount);
                // cashier.setEmployeeCode("CM-0003");
                // cashierService.proceedPayment(saleItems, cashier, branch, totalAmount);

                dataSync.syncData();

        }

}
