package SCD.model.crud;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import SCD.model.models.Branch;
import SCD.model.models.Employee;
import SCD.utils.HibernateUtil;

public class BranchesDAO {

    private static BranchesDAO instance;

    private BranchesDAO() {
    }

    public static BranchesDAO getInstance() {
        if (instance == null) {
            synchronized (BranchesDAO.class) {
                if (instance == null) {
                    instance = new BranchesDAO();
                }
            }
        }
        return instance;
    }

    public boolean addBranch(Branch branch) {
        boolean result;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            try {
                session.persist(branch);
                transaction.commit();
                result = true;
            } catch (Exception e) {
                if (transaction != null && transaction.isActive()) {
                    transaction.rollback();
                }
                result = false;
            }
        }
        return result;
    }

    public Branch getBranchByCode(String branchCode) {
        Branch b;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            b = session
                    .createQuery("FROM Branch b WHERE b.branchCode = :branchCode AND b.isActive = true", Branch.class)
                    .setParameter("branchCode", branchCode)
                    .uniqueResult();
        } catch (Exception e) {
            return null;
        }
        return b;
    }

    public boolean deleteBranch(String branchCode) {
        boolean result = false;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            try {

                Branch branchToDeactivate = session.get(Branch.class, branchCode);

                if (branchToDeactivate != null) {
                    branchToDeactivate.setActive(false);

                    Branch targetBranch = session.get(Branch.class, "MB-1234");

                    if (targetBranch != null) {
                        String hql = "FROM Employee WHERE branch.branchCode = :branchCode";
                        List<Employee> employees = session.createQuery(hql, Employee.class)
                                .setParameter("branchCode", branchCode)
                                .getResultList();

                        for (Employee employee : employees) {
                            employee.setBranch(targetBranch);
                            session.merge(employee);
                        }

                        session.merge(branchToDeactivate);
                        transaction.commit();
                        result = true;
                    } else {
                        System.out.println("Branch MB-1234 does not exist!");
                    }
                } else {
                    System.out.println("Branch not found!");
                }
            } catch (Exception e) {
                if (transaction != null && transaction.isActive()) {
                    transaction.rollback();
                }
            }
        }
        return result;
    }

    public Boolean getBranchActiveStatus(String branchCode) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Branch branch = session.get(Branch.class, branchCode);

            return branch != null ? branch.isActive() : null;
        } catch (Exception e) {
            return null;
        }
    }

    public boolean doesBranchExistWithPhone(String phone) {
        boolean exists = false;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "FROM Branch b WHERE b.phone = :phone";
            Branch branch = session.createQuery(hql, Branch.class)
                    .setParameter("phone", phone)
                    .uniqueResult();

            if (branch != null) {
                exists = true;
            }
        } catch (Exception e) {
        }
        return exists;
    }

    public boolean updateBranch(Branch updatedBranch) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            // Retrieve the existing branch from the database
            Branch existingBranch = session.get(Branch.class, updatedBranch.getBranchCode());

            if (existingBranch != null) {
                // Update the properties of the existing branch using the setters
                existingBranch.setName(updatedBranch.getName());
                existingBranch.setPhone(updatedBranch.getPhone());
                existingBranch.setAddress(updatedBranch.getAddress());
                existingBranch.setCity(updatedBranch.getCity());
                existingBranch.setUpdatedAt(updatedBranch.getUpdatedAt());

                // You can update any other fields that might have been changed
                // For example: existingBranch.setManager(updatedBranch.getManager());

                // Merge the updated branch back into the session
                session.merge(existingBranch);

                // Commit the transaction
                transaction.commit();
                return true;
            } else {
                // If the branch is not found, return false
                return false;
            }
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            return false;
        }
    }

}
