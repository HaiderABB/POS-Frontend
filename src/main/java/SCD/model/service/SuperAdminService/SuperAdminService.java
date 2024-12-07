package SCD.model.service.SuperAdminService;

import SCD.model.crud.BranchesDAO;
import SCD.model.models.Branch;
import SCD.model.service.AddResponseClass;

public class SuperAdminService {

    BranchesDAO branchesDAO;

    public SuperAdminService() {
        branchesDAO = BranchesDAO.getInstance();
    }

    public AddResponseClass createBranch(Branch branch) {

        boolean br = branchesDAO.doesBranchExistWithPhone(branch.getPhone());

        if (br) {
            return new AddResponseClass("Branch Exists with phone number", false);
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

    public AddResponseClass updateBranch(Branch branch) {
        Branch br = branchesDAO.getBranchByCode(branch.getBranchCode());
        if (br == null) {
            return new AddResponseClass("Branch does not exist", false);
        }

        boolean res;
        res = branchesDAO.updateBranch(branch);

        if (!res) {
            return new AddResponseClass("Branch Update Failed", false);
        }

        return new AddResponseClass("Branch Update Successful", res);

    }

}
