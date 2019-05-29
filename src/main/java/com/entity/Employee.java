package com.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "employees")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(name = "grade", nullable = false)
    private Integer grade;
    @Column(name = "salary", nullable = false)
    private Integer salary;
    @Column(name = "name", nullable = false, length = 30)
    private String name;
    @Column(name = "surname", nullable = false, length = 40)
    private String surName;


    public Employee(Integer grade, String name, String surName, Integer salary) {
        this.grade = grade;
        this.name = name;
        this.surName = surName;
        this.salary = salary;
    }

    public Employee() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurName() {
        return surName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    public Integer getSalary() {
        return salary;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return new StringBuilder("ID: ").append(this.id).append(" Name: ").append(this.name).append(" Surname: ")
                .append(this.surName).append(" Grade: ").append(this.grade)
                .append(" Salary: ").append(this.salary).toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Employee)) return false;
        Employee employee = (Employee) o;
        return grade.equals(employee.grade) &&
                salary.equals(employee.salary) &&
                name.equals(employee.name) &&
                surName.equals(employee.surName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, grade, salary, name, surName);
    }
}
