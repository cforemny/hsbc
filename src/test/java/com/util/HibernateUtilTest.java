package com.util;


import com.entity.Employee;
import org.hibernate.Session;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class HibernateUtilTest {

    @Test
    public void testHibernateUtil() {

        //given
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        // Add new Employee object
        Employee emp = new Employee();
        emp.setGrade(4);
        emp.setName("Jan");
        emp.setSurName("Kowalski");
        emp.setSalary(25000);
        session.save(emp);
        session.getTransaction().commit();
        HibernateUtil.closeSession();
    }

}