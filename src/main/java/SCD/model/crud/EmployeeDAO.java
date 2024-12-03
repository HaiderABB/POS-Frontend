package SCD.model.crud;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import SCD.model.entities.Branch;
import SCD.model.entities.Employee;
import SCD.utils.HibernateUtil;

public class EmployeeDAO {

  public Employee login(String employeeCode, String password) {
    try (Session session = HibernateUtil.getSessionFactory().openSession()) {
      return session
          .createQuery("FROM Employee WHERE employeeCode = :employeeCode AND password = :password", Employee.class)
          .setParameter("employeeCode", employeeCode)
          .setParameter("password", password)
          .uniqueResult();
    } catch (Exception e) {
      return null;
    }
  }

  public boolean setFirstLoginToFalse(String employeeCode) {
    // Use try-with-resources to ensure session is automatically closed
    try (Session session = HibernateUtil.getSessionFactory().openSession()) {
      Transaction transaction = session.beginTransaction();
      Employee employee = session.get(Employee.class, employeeCode);
      if (employee != null && employee.isFirstLogin()) {
        employee.setFirstLogin(false);
        session.merge(employee); // Merge changes to the employee
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

  // Add another employee and update total employees in the branch
  public boolean addEmployee(Employee employee) {
    // Use try-with-resources to ensure session is automatically closed
    try (Session session = HibernateUtil.getSessionFactory().openSession()) {
      Transaction transaction = session.beginTransaction();

      // Persist the new employee
      session.persist(employee);

      // Get the associated branch and update total employees
      Branch branch = employee.getBranch();
      if (branch != null) {
        branch.setTotalEmployees(branch.getTotalEmployees() + 1);
        session.merge(branch); // Merge the updated branch
      }

      transaction.commit();
      return true;
    } catch (Exception e) {
      return false;
    }
  }

  // Remove an employee
  public boolean removeEmployee(String employeeCode) {
    // Use try-with-resources to ensure session is automatically closed
    try (Session session = HibernateUtil.getSessionFactory().openSession()) {
      Transaction transaction = session.beginTransaction();
      Employee employee = session.get(Employee.class, employeeCode);
      if (employee != null) {
        session.remove(employee); // Use remove to delete the employee
        transaction.commit();
        return true;
      }
      return false;
    } catch (Exception e) {
      return false;
    }
  }

  // Fetch all employees with the "Cashier" role
  public List<Employee> getEmployeesByRoleCashier() {
    try (Session session = HibernateUtil.getSessionFactory().openSession()) {
      return session.createQuery("FROM Employee WHERE role = :role", Employee.class)
          .setParameter("role", "Cashier")
          .getResultList();
    } catch (Exception e) {
      return null;
    }
  }

  // Fetch all employees with the "Data Entry Operator" role
  public List<Employee> getEmployeesByRoleDataEntryOperator() {
    try (Session session = HibernateUtil.getSessionFactory().openSession()) {
      return session.createQuery("FROM Employee WHERE role = :role", Employee.class)
          .setParameter("role", "Data Entry Operator")
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

}