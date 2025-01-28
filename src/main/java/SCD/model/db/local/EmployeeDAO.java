package SCD.model.db.local;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import SCD.model.models.Branch;
import SCD.model.models.Employee;
import SCD.utils.HibernateUtil;

public class EmployeeDAO {

  private static EmployeeDAO instance;

  private EmployeeDAO() {
  }

  @SuppressWarnings("DoubleCheckedLocking")
  public static EmployeeDAO getInstance() {
    if (instance == null) {
      synchronized (EmployeeDAO.class) {
        if (instance == null) {
          instance = new EmployeeDAO();
        }
      }
    }
    return instance;
  }

  public Employee loginWithRole(String employeeCode, String password, String role) {
    try (Session session = HibernateUtil.getSessionFactory().openSession()) {
      return session
          .createQuery(
              "FROM Employee WHERE employeeCode = :employeeCode AND password = :password AND role = :role AND isActive = true",
              Employee.class)
          .setParameter("employeeCode", employeeCode)
          .setParameter("password", password)
          .setParameter("role", role)
          .uniqueResult();
    } catch (Exception e) {
      return null;
    }
  }

  public boolean setFirstLoginToFalse(String employeeCode) {
    try (Session session = HibernateUtil.getSessionFactory().openSession()) {
      Transaction transaction = session.beginTransaction();
      Employee employee = session.get(Employee.class, employeeCode);
      if (employee != null && employee.isFirstLogin()) {
        employee.setFirstLogin(false);
        session.merge(employee);
        transaction.commit();
        return true;
      }
      return false;
    } catch (Exception e) {
      return false;
    }
  }

  public Boolean getFirstLoginStatus(String employeeCode) {
    try (Session session = HibernateUtil.getSessionFactory().openSession()) {
      Employee employee = session.get(Employee.class, employeeCode);
      return employee != null ? employee.isFirstLogin() : null;
    } catch (Exception e) {
      return null;
    }
  }

  public boolean isEmailExists(String email) {
    try (Session session = HibernateUtil.getSessionFactory().openSession()) {
      Long count = session
          .createQuery("SELECT COUNT(e) FROM Employee e WHERE e.email = :email AND e.isActive = true", Long.class)
          .setParameter("email", email)
          .uniqueResult();
      return count != null && count > 0;
    } catch (Exception e) {
      return false;
    }
  }

  public boolean addEmployee(Employee employee) {
    try (Session session = HibernateUtil.getSessionFactory().openSession()) {
      // scd- proj initStart a transaction
      Transaction transaction = session.beginTransaction();

      // scd- proj initEnsure that the branch is already fetched or exists in the
      // session
      Branch branch = employee.getBranch();
      if (branch != null) {
        // scd- proj initCheck if the branch is already present in the session, if not
        // fetch it
        if (!session.contains(branch)) {
          // scd- proj initFetch the branch from the database if not already in the
          // session
          branch = session.get(Branch.class, branch.getBranchCode());
          if (branch == null) {
            // scd- proj initHandle case where branch doesn't exist (e.g., throw an
            // exception or return
            // scd- proj initfalse)
            System.out.println("Branch not found!");
            return false;
          }
        }

        // scd- proj initAssociate the employee with the branch (this keeps the
        // bidirectional
        // scd- proj initrelationship intact)
        employee.setBranch(branch);

        // scd- proj initPersist the new employee
        session.persist(employee);

        // scd- proj initUpdate the total employee count for the branch
        branch.setTotalEmployees(branch.getTotalEmployees() + 1);
        session.update(branch); // scd- proj initUpdate the branch entity

        // scd- proj initCommit the transaction
        transaction.commit();
        return true;
      } else {
        System.out.println("Branch not provided!");
        return false;
      }
    } catch (Exception e) {
      e.printStackTrace(); // scd- proj initOptionally log the exception
      return false;
    }
  }

  public boolean deactivateEmployee(String employeeCode) {
    Transaction transaction = null;
    try (Session session = HibernateUtil.getSessionFactory().openSession()) {
      transaction = session.beginTransaction();

      Employee employee = session
          .createQuery("FROM Employee WHERE employeeCode = :employeeCode", Employee.class)
          .setParameter("employeeCode", employeeCode)
          .uniqueResult();

      if (employee != null) {
        employee.setActive(false);
        session.merge(employee);
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

  public List<Employee> getActiveCashiersByBranch(String branchCode) {
    try (Session session = HibernateUtil.getSessionFactory().openSession()) {
      return session.createQuery(
          "FROM Employee e WHERE e.role = :role AND e.isActive = true AND e.branch.branchCode = :branchCode",
          Employee.class)
          .setParameter("role", "CASHIER")
          .setParameter("branchCode", branchCode)
          .getResultList();
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }

  public List<Employee> getActiveDEOByBranch(String branchCode) {
    try (Session session = HibernateUtil.getSessionFactory().openSession()) {
      return session.createQuery(
          "FROM Employee e WHERE e.role = :role AND e.isActive = true AND e.branch.branchCode = :branchCode",
          Employee.class)
          .setParameter("role", "DATA_ENTRY_OPERATOR")
          .setParameter("branchCode", branchCode)
          .getResultList();
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }

  public List<Employee> getEmployeesByRoleManager() {
    try (Session session = HibernateUtil.getSessionFactory().openSession()) {
      return session.createQuery("FROM Employee WHERE role = :role AND isActive = true", Employee.class)
          .setParameter("role", "MANAGER")
          .getResultList();
    } catch (Exception e) {
      return null;
    }
  }

  public boolean updatePassword(String employeeCode, String newPassword) {
    Transaction transaction = null;
    try (Session session = HibernateUtil.getSessionFactory().openSession()) {
      transaction = session.beginTransaction();

      Employee employee = session.get(Employee.class, employeeCode);

      if (employee != null) {
        employee.setPassword(newPassword);
        session.merge(employee);
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

  public boolean employeeExistsByEmployeeCode(String employeeCode) {
    try (Session session = HibernateUtil.getSessionFactory().openSession()) {
      Long count = session
          .createQuery("SELECT COUNT(e) FROM Employee e WHERE e.employeeCode = :employeeCode", Long.class)
          .setParameter("employeeCode", employeeCode)
          .uniqueResult();

      return count != null && count > 0;
    } catch (Exception e) {
      return false;
    }
  }

  public Employee getEmployeeByEmployeeCode(String employeeCode) {
    try (Session session = HibernateUtil.getSessionFactory().openSession()) {
      return session.get(Employee.class, employeeCode);
    } catch (Exception e) {
      return null;
    }
  }

  public boolean updateEmployee(Employee employee) {
    Transaction transaction = null;
    try (Session session = HibernateUtil.getSessionFactory().openSession()) {
      transaction = session.beginTransaction();

      Employee existingEmployee = session.get(Employee.class, employee.getEmployeeCode());

      if (existingEmployee != null) {

        existingEmployee.setName(employee.getName());

        existingEmployee.setPhoneNumber(employee.getPhoneNumber());

        existingEmployee.setSalary(employee.getSalary());

        existingEmployee.setUpdatedAt(employee.getUpdatedAt());
        existingEmployee.setEmail(employee.getEmail());

        session.merge(existingEmployee);

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

  public boolean isPhoneNumberExists(String phoneNumber) {
    try (Session session = HibernateUtil.getSessionFactory().openSession()) {
      Long count = session
          .createQuery("SELECT COUNT(e) FROM Employee e WHERE e.phoneNumber = :phoneNumber AND e.isActive = true",
              Long.class)
          .setParameter("phoneNumber", phoneNumber)
          .uniqueResult();
      return count != null && count > 0;
    } catch (Exception e) {
      System.err.println("Error checking phone number existence: " + e.getMessage());
      return false;
    }
  }

  public List<Employee> getEmployeesByBranchCode(String branchCode) {
    try (Session session = HibernateUtil.getSessionFactory().openSession()) {
      return session
          .createQuery("FROM Employee WHERE branch.branchCode = :branchCode AND isActive = true", Employee.class)
          .setParameter("branchCode", branchCode)
          .getResultList();
    } catch (Exception e) {
      System.err.println("Error retrieving employees by branch code: " + e.getMessage());
      return null;
    }
  }

  public static void main(String[] args) {

    EmployeeDAO employeeDAO = EmployeeDAO.getInstance();
    List<Employee> emps = employeeDAO.getActiveCashiersByBranch("BR-0002");
    System.out.println(emps == null);

  }

}