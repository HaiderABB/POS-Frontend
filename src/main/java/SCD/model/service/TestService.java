package SCD.model.service;

import SCD.model.db.local.CodesDAO;
import SCD.model.models.Branch;
import SCD.model.models.Codes;
import SCD.model.models.Employee;
import SCD.model.models.Product;
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

                // scd- proj initcodesDAO.addCode(codes1);
                // scd- proj initcodesDAO.addCode(codes2);
                // scd- proj initcodesDAO.addCode(codes3);
                // scd- proj initcodesDAO.addCode(codes4);

                Branch branch = new Branch("Main Branch", "Lahore", "Gulberg", "03219306126");

                // scd- proj initsuperAdminService.createBranch(branch);
                branch.setBranchCode("BR-0001");
                Employee employee = new Employee("Haider Abbas Moazzam",
                                "SUPER_ADMIN", branch, "03219306127",
                                "haider.a.moazzam@gmail.com");

                // scd- proj initAddResponseJSON json = commonServices.AddEmployee(employee);
                // scd- proj initSystem.out.println(json.isSuccess());

                Vendor ven = new Vendor("Mahnoor Beauty Parlour", "03219632515", "Askari 10");

                dataEntryOperatorService.addVendor(ven);

                Product prod = new Product(ven, "Hands", "Cooking", 20.0, 25.0, 26.0, 50.0, 40);

                dataEntryOperatorService.addProduct(prod);

                // scd- proj init// scd- proj initCreating another Branch object
                // scd- proj initBranch branch2 = new Branch("THIRD Branch", "Karachi", "Clifton
                // Block 2",
                // scd- proj init"03111234567");

                // scd- proj init// scd- proj initCreating another Employee object associated
                // with branch2
                // scd- proj init// scd- proj initEmployee employee2 = new Employee("Ayesha
                // Khan",
                // scd- proj init// scd- proj init"CASHIER",
                // scd- proj init// scd- proj initbranch2,
                // scd- proj init// scd- proj init"03121234567",
                // scd- proj init// scd- proj init50000,
                // scd- proj init// scd- proj init"ayesha.khan@gmail.com");

                // scd- proj init// scd- proj initsuperAdminService.createBranch(branch2);
                // scd- proj init// scd- proj initcommonServices.AddEmployee(employee2);
                // scd- proj initbranch.setBranchCode("BR-0001");
                // scd- proj init// scd- proj initAddResponseJSON json =
                // superAdminService.updateBranch(branch);
                // scd- proj init// scd- proj initSystem.out.println(json.isSuccess());
                // scd- proj init// scd- proj initSystem.out.println(json.getMessage());

                // scd- proj initVendor vendor = new Vendor("ABC Electronics", "03121234567",
                // "123 Main
                // scd- proj initStreet");
                // scd- proj initVendor vendor2 = new Vendor("XYZ Electronics", "03121234789",
                // "456 Elm
                // scd- proj initStreet");

                // scd- proj init// scd- proj initdataEntryOperatorService.addVendor(vendor);
                // scd- proj init// scd- proj initdataEntryOperatorService.addVendor(vendor2);

                // scd- proj initemployee.setEmployeeCode("SM-0001");
                // scd- proj init// scd- proj initcommonServices.UpdateEmployee(employee);

                // scd- proj initbranch2.setBranchCode("BR-0002");
                // scd- proj init// scd- proj initsuperAdminService.updateBranch(branch2);

                // scd- proj initvendor.setVendorCode("VM-0001");
                // scd- proj initvendor2.setVendorCode("VM-0002");

                // scd- proj initProduct product1 = new Product(vendor, "Product One", "Category
                // A", 100.0,
                // scd- proj init90.0, 10.0, 90.0,
                // scd- proj init50);
                // scd- proj initProduct product2 = new Product(vendor, "Product Two", "Category
                // A", 120.0,
                // scd- proj init110.0, 12.0, 108.0,
                // scd- proj init40);
                // scd- proj initProduct product3 = new Product(vendor, "Product Three",
                // "Category B", 200.0,
                // scd- proj init180.0, 20.0,
                // scd- proj init180.0, 30);
                // scd- proj initProduct product4 = new Product(vendor, "Product Four",
                // "Category B", 80.0,
                // scd- proj init70.0, 8.0, 72.0,
                // scd- proj init25);
                // scd- proj initProduct product5 = new Product(vendor, "Product Five",
                // "Category C", 150.0,
                // scd- proj init140.0, 15.0, 135.0,
                // scd- proj init60);

                // scd- proj initProduct product6 = new Product(vendor2, "Product Six",
                // "Category A", 100.0,
                // scd- proj init95.0, 9.5, 90.0,
                // scd- proj init80);
                // scd- proj initProduct product7 = new Product(vendor2, "Product Seven",
                // "Category A", 110.0,
                // scd- proj init100.0, 11.0, 99.0,
                // scd- proj init70);
                // scd- proj initProduct product8 = new Product(vendor2, "Product Eight",
                // "Category B", 90.0,
                // scd- proj init85.0, 8.5, 88.0,
                // scd- proj init45);
                // scd- proj initProduct product9 = new Product(vendor2, "Product Nine",
                // "Category B", 140.0,
                // scd- proj init130.0, 13.0, 126.0,
                // scd- proj init35);
                // scd- proj initProduct product10 = new Product(vendor2, "Product Ten",
                // "Category C", 160.0,
                // scd- proj init150.0, 16.0, 144.0,
                // scd- proj init55);

                // scd- proj init// scd- proj
                // initdataEntryOperatorService.addProduct(product10);
                // scd- proj init// scd- proj initdataEntryOperatorService.addProduct(product9);
                // scd- proj init// scd- proj initdataEntryOperatorService.addProduct(product8);
                // scd- proj init// scd- proj initdataEntryOperatorService.addProduct(product7);
                // scd- proj init// scd- proj initdataEntryOperatorService.addProduct(product6);
                // scd- proj init// scd- proj initdataEntryOperatorService.addProduct(product5);
                // scd- proj init// scd- proj initdataEntryOperatorService.addProduct(product4);
                // scd- proj init// scd- proj initdataEntryOperatorService.addProduct(product3);
                // scd- proj init// scd- proj initdataEntryOperatorService.addProduct(product2);
                // scd- proj init// scd- proj initdataEntryOperatorService.addProduct(product1);
                // scd- proj initproduct1.setProductCode("PM-0001");
                // scd- proj initproduct2.setProductCode("PM-0002");
                // scd- proj initproduct3.setProductCode("PM-0003");
                // scd- proj initproduct4.setProductCode("PM-0004");
                // scd- proj initproduct5.setProductCode("PM-0005");
                // scd- proj initproduct6.setProductCode("PM-0006");
                // scd- proj initproduct7.setProductCode("PM-0007");
                // scd- proj initproduct8.setProductCode("PM-0008");
                // scd- proj initproduct9.setProductCode("PM-0009");
                // scd- proj initproduct10.setProductCode("PM-0010");

                // scd- proj init// scd- proj initCreate SaleItem objects
                // scd- proj initSaleItem saleItem1 = new SaleItem(product1, 2,
                // product1.getSalePrice(),
                // scd- proj initproduct1.getOriginalPrice());
                // scd- proj initSaleItem saleItem2 = new SaleItem(product2, 3,
                // product2.getSalePrice(),
                // scd- proj initproduct2.getOriginalPrice());
                // scd- proj initSaleItem saleItem3 = new SaleItem(product3, 1,
                // product3.getSalePrice(),
                // scd- proj initproduct3.getOriginalPrice());
                // scd- proj initSaleItem saleItem4 = new SaleItem(product4, 5,
                // product4.getSalePrice(),
                // scd- proj initproduct4.getOriginalPrice());
                // scd- proj initSaleItem saleItem5 = new SaleItem(product5, 4,
                // product5.getSalePrice(),
                // scd- proj initproduct5.getOriginalPrice());

                // scd- proj init// scd- proj initAdd to a list
                // scd- proj initList<SaleItem> saleItems = new ArrayList<>();
                // scd- proj initsaleItems.add(saleItem1);
                // scd- proj initsaleItems.add(saleItem2);
                // scd- proj initsaleItems.add(saleItem3);
                // scd- proj initsaleItems.add(saleItem4);
                // scd- proj initsaleItems.add(saleItem5);

                // scd- proj init// scd- proj initEmployee cashier = new Employee("Meesum",
                // scd- proj init// scd- proj init"CASHIER", branch2, "03334710943", 20000,
                // scd- proj init// scd- proj init"cashier@gmail.com");

                // scd- proj init// scd- proj initcommonServices.AddEmployee(cashier);

                // scd- proj init// scd- proj initdouble totalAmount =
                // cashierService.GenerateBill(saleItems);
                // scd- proj init// scd- proj initSystem.out.println(totalAmount);
                // scd- proj init// scd- proj initcashier.setEmployeeCode("CM-0003");
                // scd- proj init// scd- proj initcashierService.proceedPayment(saleItems,
                // cashier, branch, totalAmount);

                // scd- proj init// scd- proj initdataSync.syncData();

        }

}
