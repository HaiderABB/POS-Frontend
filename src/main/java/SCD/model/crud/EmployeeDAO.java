package SCD.model.crud;

import org.hibernate.Session;
import org.hibernate.Transaction;

import SCD.model.entities.Branch;
import SCD.model.entities.Employee;
import SCD.utils.HibernateUtil;

public class EmployeeDAO {

  public Employee login(String username, String password) {
    // Automatically closes the session at the end of the try block
    try (Session session = HibernateUtil.getSessionFactory().openSession()) {
      return session.createQuery("FROM Employee WHERE username = :username AND password = :password", Employee.class)
          .setParameter("username", username)
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

}
