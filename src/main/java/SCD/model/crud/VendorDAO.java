package SCD.model.crud;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import SCD.model.models.Vendor;
import SCD.utils.HibernateUtil;

public class VendorDAO {

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

  public boolean deleteVendor(String vendorCode) {
    boolean result = false;
    Transaction transaction = null;
    try (Session session = HibernateUtil.getSessionFactory().openSession()) {
      transaction = session.beginTransaction();
      Vendor vendor = session.get(Vendor.class, vendorCode);

      if (vendor != null) {
        session.remove(vendor);
        transaction.commit();
        result = true;
      } else {
        System.out.println("Vendor not found with code: " + vendorCode);
      }
    } catch (Exception e) {
      if (transaction != null) {
        transaction.rollback();
      }
    }
    return result;
  }

  public List<Vendor> getAllVendors() {
    List<Vendor> vendors = null;
    try (Session session = HibernateUtil.getSessionFactory().openSession()) {
      vendors = session.createQuery("FROM Vendor", Vendor.class).list(); // Fetch all vendors
    } catch (Exception e) {
    }
    return vendors;
  }
}
