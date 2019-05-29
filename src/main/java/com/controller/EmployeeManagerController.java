package com.controller;

import com.converter.JsonConverter;
import com.dao.EmployeeDaoImpl;
import com.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class EmployeeManagerController {

    public static final String EMPTY_GRADE = "";
    @Autowired
    private EmployeeDaoImpl employeeDao;
    @Autowired
    private JsonConverter jsonConverter;

    @GetMapping("/")
    public String home(Model model) {
        return "home";
    }

    @GetMapping("/find")
    public String findEmployee(@RequestParam String id, Model model) {
        Employee employee = employeeDao.findEmployeeById(Integer.valueOf(id));
        model.addAttribute("employee", employee == null ? "Sorry, there is no employee with id: " + id : jsonConverter.convertEmployeeToJson(employee));
        return "home";
    }

    @GetMapping("/findWithParam")
    public String findWithParam(Model model, @RequestParam String name, @RequestParam String surName, @RequestParam String grade) {
        Integer gradeInt = EMPTY_GRADE.equals(grade) ? null : Integer.valueOf(grade);
        List<Employee> allEmployeesWithParameters = employeeDao.findAllEmployeesWithParameters(name, surName, gradeInt);
        model.addAttribute("employeesWithParams", allEmployeesWithParameters.stream().map(employee -> jsonConverter.convertEmployeeToJson(employee)).collect(Collectors.toList()));
        return "home";
    }

    @GetMapping("/new")
    public String addEmployee(Model model, @RequestParam String name, @RequestParam String surName, @RequestParam String grade) {
        Integer employeeId = employeeDao.addEmployee(new Employee(Integer.valueOf(grade), name, surName));
        model.addAttribute("newAddedEmployee", "Added new employee: " + jsonConverter.convertEmployeeToJson(employeeDao.findEmployeeById(employeeId)));
        return "home";
    }

    @GetMapping("/update")
    public String updateEmployee(Model model, @RequestParam String id, @RequestParam String name, @RequestParam String surName, @RequestParam String grade) {
        model.addAttribute("originalEmployee", "Original Employee: " + jsonConverter.convertEmployeeToJson(employeeDao.findEmployeeById(Integer.valueOf(id))));
        employeeDao.updateEmployee(Integer.valueOf(id), new Employee(Integer.valueOf(grade), name, surName));
        model.addAttribute("updatedEmployee", "Updated employee: " + jsonConverter.convertEmployeeToJson(employeeDao.findEmployeeById(Integer.valueOf(id))));
        return "home";
    }

    @GetMapping("/delete")
    public String deleteEmployee(@RequestParam String id, Model model) {
        Employee employee = employeeDao.findEmployeeById(Integer.valueOf(id));
        employeeDao.deleteEmployeeById(Integer.valueOf(id));
        model.addAttribute("deletedEmployee", employee == null ? "Sorry, there is no employee with id: " + id : "Deleted employee: " + jsonConverter.convertEmployeeToJson(employee));
        return "home";
    }

}
