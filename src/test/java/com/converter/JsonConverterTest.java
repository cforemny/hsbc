package com.converter;

import com.entity.Employee;
import org.junit.Test;

import static org.junit.Assert.*;

public class JsonConverterTest {

private static final String EMPLOYEE_JSON = "{\"id\":null,\"grade\":2,\"name\":\"Aleksandra\",\"surName\":\"Zajac\"}";
private static final String EMPLOYEE_JSON_NULL = "null";

    @Test
    public void convertEmployeeToJson() {
        //given
        JsonConverter jsonConverter = new JsonConverter();
        Employee employee = new Employee(2, "Aleksandra", "Zajac");
        //when
        String result = jsonConverter.convertEmployeeToJson(employee);
        //then
        assert EMPLOYEE_JSON.equals(result);
    }

    @Test
    public void convertEmptyEmployeeToJson() {
        //given
        JsonConverter jsonConverter = new JsonConverter();
        //when
        String result = jsonConverter.convertEmployeeToJson(null);
        //then
        assert EMPLOYEE_JSON_NULL.equals(result);
    }

}