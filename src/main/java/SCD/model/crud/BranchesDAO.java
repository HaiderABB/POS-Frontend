package SCD.model.crud;

import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import SCD.model.entities.Branch;
import SCD.model.entities.Employee;
import SCD.utils.HibernateUtil;

public class BranchesDAO {

    public boolean addBranch(Branch branch) { // returns true if added successfully else false
        boolean result;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            try {
                branch.setCreatedAt(new Date());
                session.persist(branch); // Use persist instead of save to avoid deprecation
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

    public Branch getBranchByCode(String branchCode) { // Return null if not found
        Branch b = null;

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

    // Deactivate a branch and reassign all employees to branch "MB-1234"
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

    // Get the isActive status of a branch
    public Boolean getBranchActiveStatus(String branchCode) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Branch branch = session.get(Branch.class, branchCode);

            return branch != null ? branch.isActive() : null;
        } catch (Exception e) {
            return null;
        }
    }

}
