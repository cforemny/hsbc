package dao;

import com.dao.EmployeeDaoImpl;
import com.entity.Employee;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

/*
Tests needs to be executed separately
 */
public class EmployeeDaoTest {


    private EmployeeDaoImpl employeeDao;

    @Before
    public void setUp() {
        this.employeeDao = new EmployeeDaoImpl();
    }


    @Test
    public void findEmployeeByIdTest() {

        //given

        //when
        Integer employeeId = employeeDao.addEmployee(new Employee(3, "Vanessa", "Suarez",15000));
        Employee result = employeeDao.findEmployeeById(employeeId);
        //then
        assert result != null;
    }

    @Test
    public void addEmployeeTest() {

        //given
        Employee employee = new Employee(3, "Barbara", "Chrzanowska",15000);
        //when
        Integer employeeId = employeeDao.addEmployee(employee);
        //then
        assert employee.equals(employeeDao.findEmployeeById(employeeId));
    }

    @Test
    public void updateEmployeeTest() {

        //given
        Employee employee = new Employee(2, "Barbara", "Nowacka",7000);
        Employee employeeUpdated = new Employee(2, "Barbara", "Nowacka-Pierog",7000);
        Integer employeeId = employeeDao.addEmployee(employee);
        //when
        employeeDao.updateEmployee(employeeId, employeeUpdated);
        //then
        assert employeeUpdated.equals(employeeDao.findEmployeeById(employeeId));
    }

    @Test
    public void deleteEmployeeByIdTest() {
        //given

        //when
        Employee employee = new Employee(3, "Barbara", "Chrzanowska",15000);
        Integer employeeId = employeeDao.addEmployee(employee);
        assert employee.equals(employeeDao.findEmployeeById(employeeId));
        employeeDao.deleteEmployeeById(employeeId);
        //then
        assert employeeDao.findEmployeeById(employeeId) == null;
    }

    @Test
    public void findAllEmployeesTest() {

        //given

        //when
        Employee employee = new Employee(3, "Barbara", "Chrzanowska",15000);
        Employee employee1 = new Employee(1, "Paul", "McDonald",4000);
        employeeDao.addEmployee(employee);
        employeeDao.addEmployee(employee1);
        List<Employee> result = employeeDao.findAllEmployees();
        //then
        assert result.size() == 2;
    }

    @Test
    public void findAllEmployeesWithParametersTest() {

        //given

        //when
        employeeDao.addEmployee( new Employee(3, "Monika", "Chrzanowska",15000));
        employeeDao.addEmployee( new Employee(3, "Monika", "Nowicka",15000));
        List<Employee> result = employeeDao.findAllEmployeesWithParameters("Monika", null, null,null);
        //then
        assert result.size() == 2;
    }

}