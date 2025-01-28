package SCD.controllers.DataEntryOperatorControllers.VendorController;

import java.util.List;

import javax.swing.table.DefaultTableModel;

import SCD.model.models.Vendor;
import SCD.model.service.DataEntryOperatorService.DataEntryOperatorService;
import SCD.model.service.Json.GetResponseJSON;
import SCD.ui.DataEntryOperator.Vendor.ViewVendorsPage;

public class ViewVendorsController {

    private ViewVendorsPage view;
    DataEntryOperatorService dataEntryOperatorServices = new DataEntryOperatorService();

    public ViewVendorsController() {

        GetResponseJSON<Vendor> json = dataEntryOperatorServices.getVendors();
        view = new ViewVendorsPage();
        initController(json);
        view.setVisible(true);

    }

    private void initController(GetResponseJSON<Vendor> json) {
        loadVendorData(json);
    }

    private void loadVendorData(GetResponseJSON<Vendor> json) {
        List<Vendor> vendorData = json.getData();
        DefaultTableModel model = view.getTableModel();
        model.setRowCount(0);

        if (vendorData != null) {
            for (Vendor vendor : vendorData) {
                model.addRow(new Object[] {
                        vendor.getVendorCode(),
                        vendor.getName(),
                        vendor.getPhoneNumber(),
                        vendor.getAddress()
                });
            }
        } else {
            model.addRow(new Object[] { "-", "-", "-", "-", "-" });
        }

    }

    public static void main(String[] args) {
        new ViewVendorsController();
    }
}
