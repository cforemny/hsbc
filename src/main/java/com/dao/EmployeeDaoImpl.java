package com.dao;

import com.entity.Employee;
import com.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.persistence.NoResultException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class EmployeeDaoImpl implements EmploeeDao {

    private static final Logger logger = LoggerFactory.getLogger(EmployeeDaoImpl.class);
    private Session session;

    @Override
    public Integer addEmployee(Employee employee) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.saveOrUpdate(employee);
            transaction.commit();
            logger.info("Added new Employee - " + employee.toString());
        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            logger.info("Trancation rolled back because of: " + ex);
        }
        return employee.getId();
    }

    @Override
    public void updateEmployee(Integer employeeId, Employee newEmployee) {

        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Employee employee = (Employee) session.get(Employee.class, employeeId);
            employee.setName(newEmployee.getName());
            employee.setSurName(newEmployee.getSurName());
            employee.setGrade(newEmployee.getGrade());
            employee.setSalary(newEmployee.getSalary());
            session.update(employee);
            transaction.commit();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Employee findEmployeeById(Integer id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "FROM Employee I WHERE I.id = :id";
            Query query = session.createQuery(hql, Employee.class);
            query.setParameter("id", id);
            return query.getSingleResult() != null ? (Employee) query.getSingleResult() : null;
        } catch (NoResultException ex) {
            logger.info("Cannot find user with id: " + id);
            return null;
        }
    }

    @Override
    public void deleteEmployeeById(Integer id) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Employee employee = (Employee) session.load(Employee.class, id);
            session.delete(employee);
            transaction.commit();
        } catch (Exception ex) {
            logger.info("Wrong Employee" + ex);
        }
    }

    @Override
    public List<Employee> findAllEmployees() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM Employee", Employee.class).list();
        }
    }


    public List<Employee> findAllEmployeesWithParameters(String name, String surName, Integer grade, Integer salary) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            List<Employee> employees = session.createQuery("FROM Employee", Employee.class).list();
            Stream<Employee> employeesStream = employees.stream();

            if (name != null && !"".equals(name)) {
                employeesStream = employeesStream.filter(employee -> employee.getName().equals(name));
            }
            if (surName != null  && !"".equals(surName)) {
                employeesStream = employeesStream.filter(employee -> employee.getSurName().equals(surName));
            }
            if (grade != null  && !"".equals(grade)) {
                employeesStream = employeesStream.filter(employee -> employee.getGrade().equals(grade));
            }
            if (salary != null  && !"".equals(salary)) {
                employeesStream = employeesStream.filter(employee -> employee.getSalary().equals(salary));
            }
            return employeesStream.collect(Collectors.toList());
        }
    }
}
