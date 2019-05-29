package com;

import com.controller.EmployeeManagerController;
import com.dao.EmployeeDaoImpl;
import com.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {


    private static EmployeeDaoImpl employeeDao = new EmployeeDaoImpl();

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
        fillUpEmployeeTable();
    }

    private static void fillUpEmployeeTable() {

        employeeDao.addEmployee(new Employee(2, "Aleksandra", "Zajac",7000));
        employeeDao.addEmployee(new Employee(1, "John", "Wick",4000));
        employeeDao.addEmployee(new Employee(1, "Albert", "Kowalski",4000));
        employeeDao.addEmployee(new Employee(3, "Brian", "Boglitz",15000));
        employeeDao.addEmployee(new Employee(3, "Vanessa", "Suarez",15000));
    }
}
