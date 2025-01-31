package SCD.model.db.local;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import SCD.model.models.Vendor;
import SCD.utils.HibernateUtil;

public class VendorDAO {

  private static VendorDAO instance;

  private VendorDAO() {
  }

  @SuppressWarnings("DoubleCheckedLocking")
  public static VendorDAO getInstance() {
    if (instance == null) {
      synchronized (VendorDAO.class) {
        if (instance == null) {
          instance = new VendorDAO();
        }
      }
    }
    return instance;
  }

  public Vendor getVendorByCode(String vendorCode) {
    Vendor vendor = null;
    try (Session session = HibernateUtil.getSessionFactory().openSession()) {
      vendor = session.get(Vendor.class, vendorCode);
    } catch (Exception e) {
    }
    return vendor;
  }

  public boolean addVendor(Vendor vendor) {
    boolean result = false;
    Transaction transaction = null;
    try (Session session = HibernateUtil.getSessionFactory().openSession()) {
      transaction = session.beginTransaction();
      session.persist(vendor);
      transaction.commit();
      result = true;
    } catch (Exception e) {
      if (transaction != null) {
        transaction.rollback();
      }
    }
    return result;
  }

  public boolean vendorExistsWithPhoneNumberAndActiveStatus(String phoneNumber) {
    try (Session session = HibernateUtil.getSessionFactory().openSession()) {
      String hql = "FROM Vendor v WHERE v.phoneNumber = :phoneNumber AND v.isActive = true";
      Query<Vendor> query = session.createQuery(hql, Vendor.class);
      query.setParameter("phoneNumber", phoneNumber);
      Vendor vendor = query.uniqueResult();

      return vendor != null;
    } catch (Exception e) {
      return false;
    }
  }

  public boolean deactivateVendor(String vendorCode) {
    boolean result = false;
    Transaction transaction = null;

    try (Session session = HibernateUtil.getSessionFactory().openSession()) {
      transaction = session.beginTransaction();

      Vendor vendor = session.get(Vendor.class, vendorCode);

      vendor.setActive(false);
      session.merge(vendor);
      transaction.commit();
      result = true;

    } catch (Exception e) {
      if (transaction != null && transaction.getStatus().canRollback()) {
        transaction.rollback();
      }
    }

    return result;
  }

  public List<Vendor> getAllActiveVendors() {
    List<Vendor> vendors = null;
    try (Session session = HibernateUtil.getSessionFactory().openSession()) {
      vendors = session.createQuery("FROM Vendor v WHERE v.isActive = true", Vendor.class).list();
    } catch (Exception e) {
    }
    return vendors;
  }

  public boolean updateVendor(Vendor updatedVendor) {
    Transaction transaction = null;
    try (Session session = HibernateUtil.getSessionFactory().openSession()) {
      transaction = session.beginTransaction();

      Vendor existingVendor = getVendorByCode(updatedVendor.getVendorCode());

      if (existingVendor != null) {
        existingVendor.setName(updatedVendor.getName());
        existingVendor.setPhoneNumber(updatedVendor.getPhoneNumber());
        existingVendor.setAddress(updatedVendor.getAddress());
        existingVendor.setUpdatedAt(updatedVendor.getUpdatedAt());

        session.merge(existingVendor);

        transaction.commit();
        return true;
      } else {
        return false;
      }
    } catch (Exception e) {
      if (transaction != null) {
        transaction.rollback();
      }
      return false;
    }
  }

  public boolean vendorExistsByPhoneNumber(String phoneNumber) {
    try (Session session = HibernateUtil.getSessionFactory().openSession()) {
      String hql = "SELECT COUNT(v) FROM Vendor v WHERE v.phoneNumber = :phoneNumber";
      Long count = session.createQuery(hql, Long.class)
          .setParameter("phoneNumber", phoneNumber)
          .uniqueResult();
      return count != null && count > 0;
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }
  }
}
