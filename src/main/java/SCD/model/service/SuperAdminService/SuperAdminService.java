package SCD.model.service.SuperAdminService;

import SCD.model.crud.BranchesDAO;
import SCD.model.entities.Branch;

public class SuperAdminService {

    public static void main(String[] args) {
        // SuperAdminService service = new SuperAdminService();
        // Branch branch = new Branch();
        // branch.setBranchCode("MB-1234");
        // branch.setName("Main Branch");
        // branch.setCity("Lahore");
        // branch.setAddress("Askari 10");
        // branch.setPhone("0321-9306126");
        // branch.setActive(true);

        // service.createBranch(branch);
    }

    public boolean createBranch(Branch branch) {

        boolean result;

        BranchesDAO branchesDAO = new BranchesDAO();

        result = branchesDAO.addBranch(branch);

        System.out.println("Branch created successfully: " + branch.getName());

        return result;
    }
}
