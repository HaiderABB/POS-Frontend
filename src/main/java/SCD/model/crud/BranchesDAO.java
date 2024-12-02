package SCD.model.crud;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.Transaction;

import SCD.model.entities.Branch;
import SCD.utils.HibernateUtil;

public class BranchesDAO {

    public boolean addBranch(Branch branch) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        boolean result;
        try {
            transaction = session.beginTransaction();
            branch.setCreatedAt(new Date());
            session.persist(branch); // Use persist instead of save to avoid deprecation
            transaction.commit();
            result = true;
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            result = false;

        } finally {
            session.close();
        }
        return result;

    }

    public Branch getBranchByCode(String branchCode) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            return session
                    .createQuery("FROM Branch b WHERE b.branchCode = :branchCode AND b.isActive = true", Branch.class)
                    .setParameter("branchCode", branchCode)
                    .uniqueResult();
        } catch (Exception e) {
            throw e;
        } finally {
            session.close();
        }
    }

    public boolean deleteBranch(String branchCode) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Branch branch = session.createQuery("FROM Branch b WHERE b.branchCode = :branchCode", Branch.class)
                    .setParameter("branchCode", branchCode)
                    .uniqueResult();

            if (branch != null) {
                branch.setActive(false);
                session.merge(branch); // Use merge instead of update to avoid deprecation
                transaction.commit();
                return true;
            } else {
                transaction.rollback();
                return false;
            }
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            throw e;
        } finally {
            session.close();
        }
    }
}
