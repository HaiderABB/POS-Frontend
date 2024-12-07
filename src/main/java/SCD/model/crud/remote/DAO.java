package SCD.model.crud.remote;

import org.hibernate.Session;
import org.hibernate.Transaction;

import SCD.model.models.Branch;
import SCD.model.models.Codes;
import SCD.model.models.Employee;
import SCD.model.models.Product;
import SCD.model.models.Sale;
import SCD.model.models.SaleItem;
import SCD.model.models.Vendor;
import SCD.utils.HibernateUtil;

public class DAO {
  private static DAO instance;

  private DAO() {
  }

  @SuppressWarnings("DoubleCheckedLocking")
  public static DAO getInstance() {
    if (instance == null) {
      synchronized (DAO.class) {
        if (instance == null) {
          instance = new DAO();
        }
      }
    }
    return instance;
  }

  public boolean addEmployee(Employee employee) {
    try (Session session = HibernateUtil.getRemoteSessionFactory().openSession()) {
      Transaction transaction = session.beginTransaction();

      session.persist(employee);

      Branch branch = employee.getBranch();
      if (branch != null) {
        branch.setTotalEmployees(branch.getTotalEmployees() + 1);
        session.merge(branch);
      }

      transaction.commit();
      return true;
    } catch (Exception e) {
      return false;
    }
  }

  public boolean updateEmployeeIfExists(Employee employee) {
    Transaction transaction = null;
    try (Session session = HibernateUtil.getRemoteSessionFactory().openSession()) {
      transaction = session.beginTransaction();

      Employee existingEmployee = session.get(Employee.class, employee.getEmployeeCode());

      if (existingEmployee != null) {
        existingEmployee.setName(employee.getName());
        existingEmployee.setPhoneNumber(employee.getPhoneNumber());
        existingEmployee.setSalary(employee.getSalary());
        existingEmployee.setUpdatedAt(employee.getUpdatedAt());

        session.merge(existingEmployee);
        transaction.commit();
        return true;
      }
      return false;
    } catch (Exception e) {
      if (transaction != null) {
        transaction.rollback();
      }
      return false;
    }
  }

  public boolean updateCodeByTableName(String tableName, String newCode) {
    Transaction transaction = null;
    try (Session session = HibernateUtil.getRemoteSessionFactory().openSession()) {
      transaction = session.beginTransaction();

      Codes codeRecord = session
          .createQuery("SELECT c FROM Codes c WHERE c.tableName = :tableName", Codes.class)
          .setParameter("tableName", tableName)
          .uniqueResult();

      if (codeRecord != null) {
        codeRecord.setCode(newCode); // Update the code
        session.merge(codeRecord); // Save changes
        transaction.commit(); // Commit transaction
        return true;
      } else {
        System.out.println("No record found for tableName: " + tableName);
        return false;
      }
    } catch (Exception e) {
      if (transaction != null) {
        transaction.rollback();
      }
      return false;
    }
  }

  public Codes test(String tableName, String newCode) {
    Transaction transaction = null;
    Codes c = null;
    try (Session session = HibernateUtil.getRemoteSessionFactory().openSession()) {
      transaction = session.beginTransaction();

      c = session
          .createQuery("SELECT c FROM Codes c WHERE c.tableName = :tableName", Codes.class)
          .setParameter("tableName", tableName)
          .uniqueResult();

      transaction.commit();

    } catch (Exception e) {
      if (transaction != null) {
        transaction.rollback();
      }
      return null;
    }
    return c;
  }

  public boolean addBranch(Branch branch) {
    boolean result;
    try (Session session = HibernateUtil.getRemoteSessionFactory().openSession()) {
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

  public boolean updateBranch(Branch updatedBranch) {
    Transaction transaction = null;
    try (Session session = HibernateUtil.getRemoteSessionFactory().openSession()) {
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

        session.merge(existingBranch);

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
      return false;
    }
  }

  public boolean addSaleItem(SaleItem saleItem) {
    Transaction transaction = null;
    try (Session session = HibernateUtil.getRemoteSessionFactory().openSession()) {
      transaction = session.beginTransaction();

      session.persist(saleItem);

      transaction.commit();
      return true;
    } catch (Exception e) {
      if (transaction != null) {
        transaction.rollback();
      }
      return false;
    }
  }

  public Sale addSale(Sale sale) {
    Transaction transaction = null;

    try (Session session = HibernateUtil.getRemoteSessionFactory().openSession()) {
      transaction = session.beginTransaction();

      session.persist(sale);

      transaction.commit();

    } catch (Exception e) {
      if (transaction != null) {
        transaction.rollback();
      }
      System.err.println("Error adding sale: " + e.getMessage());
      sale = null;
    }

    return sale;
  }

  public boolean addVendor(Vendor vendor) {
    boolean result = false;
    Transaction transaction = null;
    try (Session session = HibernateUtil.getRemoteSessionFactory().openSession()) {
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

  private Vendor getVendorByCode(String vendorCode) {
    Vendor vendor = null;
    try (Session session = HibernateUtil.getRemoteSessionFactory().openSession()) {
      vendor = session.get(Vendor.class, vendorCode);
    } catch (Exception e) {
    }
    return vendor;
  }

  public boolean updateVendor(Vendor updatedVendor) {
    Transaction transaction = null;
    try (Session session = HibernateUtil.getRemoteSessionFactory().openSession()) {
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

  public boolean addProduct(Product product) {

    System.out.println("IN THE QUERY");

    boolean result = false;
    Transaction transaction = null;
    try (Session session = HibernateUtil.getRemoteSessionFactory().openSession()) {
      transaction = session.beginTransaction();
      session.persist(product);
      transaction.commit();
      result = true;
    } catch (Exception e) {
      if (transaction != null) {
        System.out.println(e.getMessage());
        transaction.rollback();
      }
    }
    return result;
  }

  public boolean updateProduct(Product updatedProduct) {
    boolean result = false;
    Transaction transaction = null;
    try (Session session = HibernateUtil.getRemoteSessionFactory().openSession()) {
      transaction = session.beginTransaction();

      Product existingProduct = session.get(Product.class, updatedProduct.getProductCode());

      if (existingProduct != null) {
        existingProduct.setName(updatedProduct.getName());
        existingProduct.setStockQuantity(updatedProduct.getStockQuantity());
        existingProduct.setCategory(updatedProduct.getCategory());
        existingProduct.setOriginalPrice(updatedProduct.getOriginalPrice());
        existingProduct.setPriceByCarton(updatedProduct.getPriceByCarton());
        existingProduct.setUpdatedAt(updatedProduct.getUpdatedAt());
        existingProduct.setPriceByUnit(updatedProduct.getPriceByUnit());
        existingProduct.setSalePrice(updatedProduct.getSalePrice());

        session.merge(existingProduct);

        transaction.commit();
        result = true;
        System.out.println("Product " + updatedProduct.getProductCode() + " updated successfully!");
      } else {
        System.out.println("Product not found with code: " + updatedProduct.getProductCode());
      }
    } catch (Exception e) {
      if (transaction != null && transaction.getStatus().canRollback()) {
        transaction.rollback();
      }
    }

    return result;
  }

}