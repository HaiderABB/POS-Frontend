package SCD.model.crud;

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

      // Fetch the vendor
      Vendor vendor = session.get(Vendor.class, vendorCode);

      // Deactivate the vendor
      vendor.setActive(false);
      session.merge(vendor);
      transaction.commit();
      result = true;

    } catch (Exception e) {
      if (transaction != null && transaction.getStatus().canRollback()) {
        transaction.rollback(); // Rollback only if the transaction is active
      }
      e.printStackTrace();
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

      // Retrieve the existing vendor from the database
      Vendor existingVendor = getVendorByCode(updatedVendor.getVendorCode());

      if (existingVendor != null) {
        // Update the properties of the existing vendor using the setters
        existingVendor.setName(updatedVendor.getName());
        existingVendor.setPhoneNumber(updatedVendor.getPhoneNumber());
        existingVendor.setAddress(updatedVendor.getAddress());
        existingVendor.setUpdatedAt(updatedVendor.getUpdatedAt());

        session.merge(existingVendor);

        // Commit the transaction
        transaction.commit();
        return true;
      } else {
        // If the vendor is not found, return false
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
