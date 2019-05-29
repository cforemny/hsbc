package com.dao;

import com.entity.Employee;

import java.util.List;

public interface EmploeeDao {

    Integer addEmployee(Employee employee) ;

    public Employee findEmployeeById(Integer id) ;

    public void deleteEmployeeById(Integer id) ;

    public List<Employee> findAllEmployees();

    void updateEmployee(Integer employeeId, Employee newEmployee);
}
