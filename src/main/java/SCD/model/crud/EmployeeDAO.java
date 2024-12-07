package SCD.model.crud;

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

  public List<Employee> getEmployeesByRoleCashier() {
    try (Session session = HibernateUtil.getSessionFactory().openSession()) {
      return session.createQuery("FROM Employee WHERE role = :role AND isActive = true", Employee.class)
          .setParameter("role", "CASHIER")
          .getResultList();
    } catch (Exception e) {
      return null;
    }
  }

  public List<Employee> getEmployeesByRoleDataEntryOperator() {
    try (Session session = HibernateUtil.getSessionFactory().openSession()) {
      return session.createQuery("FROM Employee WHERE role = :role AND isActive = true", Employee.class)
          .setParameter("role", "DATA_ENTRY_OPERATOR")
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

  public boolean updateEmployee(Employee employee) {
    Transaction transaction = null;
    try (Session session = HibernateUtil.getSessionFactory().openSession()) {
      transaction = session.beginTransaction();

      Employee existingEmployee = session.get(Employee.class, employee.getEmployeeCode());

      if (existingEmployee != null) {
        // Update employee details
        existingEmployee.setName(employee.getName()); // Update name

        existingEmployee.setPhoneNumber(employee.getPhoneNumber()); // Update phone number

        existingEmployee.setSalary(employee.getSalary()); // Update salary

        existingEmployee.setUpdatedAt(employee.getUpdatedAt()); // Update updatedAt

        session.merge(existingEmployee);

        // Commit the transaction
        transaction.commit();
        return true;
      } else {
        // Employee with this employeeCode does not exist
        return false;
      }
    } catch (Exception e) {
      if (transaction != null) {
        transaction.rollback();
      }
      return false;
    }
  }

}