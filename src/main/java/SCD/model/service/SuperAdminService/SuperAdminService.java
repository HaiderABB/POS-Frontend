package SCD.model.service.SuperAdminService;

import SCD.model.crud.BranchesDAO;
import SCD.model.crud.EmployeeDAO;
import SCD.model.models.Branch;
import SCD.model.service.AddResponseClass;

public class SuperAdminService {

    BranchesDAO branchesDAO;
    EmployeeDAO employeeDAO;

    public SuperAdminService() {
        branchesDAO = new BranchesDAO();
        employeeDAO = new EmployeeDAO();
    }

    public AddResponseClass createBranch(Branch branch) {

        Branch br = branchesDAO.getBranchByCode(branch.getBranchCode());

        if (br != null) {
            return new AddResponseClass("Branch Exists", false);
        }
        boolean res = branchesDAO.addBranch(branch);

        if (res) {
            return new AddResponseClass("Branch Creation Successful", true);
        }
        return new AddResponseClass(null, false);

    }

    public AddResponseClass deleteBranch(String branch_code) {
        Branch br = branchesDAO.getBranchByCode(branch_code);
        if (br == null) {
            return new AddResponseClass("Branch does not exist", false);
        }
        boolean res;

        res = branchesDAO.getBranchActiveStatus(branch_code);
        if (res == false) {
            return new AddResponseClass("Branch already deleted", true);
        }

        res = branchesDAO.deleteBranch(branch_code);

        return new AddResponseClass("Branch Deletion Successful", res);

    }

}
