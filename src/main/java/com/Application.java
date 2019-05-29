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

        employeeDao.addEmployee(new Employee(2, "Aleksandra", "Zajac"));
        employeeDao.addEmployee(new Employee(1, "John", "Wick"));
        employeeDao.addEmployee(new Employee(1, "Albert", "Kowalski"));
        employeeDao.addEmployee(new Employee(3, "Brian", "Boglitz"));
        employeeDao.addEmployee(new Employee(3, "Vanessa", "Suarez"));
    }
}
