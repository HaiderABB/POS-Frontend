package SCD.model.crud;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import SCD.model.models.Product;
import SCD.model.models.Vendor;
import SCD.utils.HibernateUtil;
import jakarta.persistence.TypedQuery;

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

      if (vendor != null) {
        vendor.setActive(false);
        session.merge(vendor);

        String hql = "UPDATE Product p SET p.isActive = false WHERE p.vendor.vendorCode = :vendorCode";
        TypedQuery<Product> query = session.createQuery(hql, Product.class);
        query.setParameter("vendorCode", vendorCode);
        int updatedEntities = query.executeUpdate();

        transaction.commit();
        result = true;

        System.out.println("Deactivated " + updatedEntities + " products for vendor " + vendorCode);
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

  public List<Vendor> getAllActiveVendors() {
    List<Vendor> vendors = null;
    try (Session session = HibernateUtil.getSessionFactory().openSession()) {
      vendors = session.createQuery("FROM Vendor v WHERE v.isActive = true", Vendor.class).list();
    } catch (Exception e) {
    }
    return vendors;
  }

}
