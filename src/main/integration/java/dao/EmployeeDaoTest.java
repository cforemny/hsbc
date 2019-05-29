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
        Integer employeeId = employeeDao.addEmployee(new Employee(3, "Vanessa", "Suarez"));
        Employee result = employeeDao.findEmployeeById(employeeId);
        //then
        assert result != null;
    }

    @Test
    public void addEmployeeTest() {

        //given
        Employee employee = new Employee(3, "Barbara", "Chrzanowska");
        //when
        Integer employeeId = employeeDao.addEmployee(employee);
        //then
        assert employee.equals(employeeDao.findEmployeeById(employeeId));
    }

    @Test
    public void updateEmployeeTest() {

        //given
        Employee employee = new Employee(2, "Barbara", "Nowacka");
        Employee employeeUpdated = new Employee(2, "Barbara", "Nowacka-Pierog");
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
        Employee employee = new Employee(3, "Barbara", "Chrzanowska");
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
        Employee employee = new Employee(3, "Barbara", "Chrzanowska");
        Employee employee1 = new Employee(1, "Paul", "McDonald");
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
        employeeDao.addEmployee( new Employee(3, "Monika", "Chrzanowska"));
        employeeDao.addEmployee( new Employee(3, "Monika", "Nowicka"));
        List<Employee> result = employeeDao.findAllEmployeesWithParameters("Monika", null, null);
        //then
        assert result.size() == 2;
    }

}