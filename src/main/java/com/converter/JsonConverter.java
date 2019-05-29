package com.converter;

import com.entity.Employee;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class JsonConverter {

    public String convertEmployeeToJson(Employee employee) {

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.writeValueAsString(employee);
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }
}
