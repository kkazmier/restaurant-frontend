package com.kodilla.restaurantfrontend.mapper;

import com.kodilla.restaurantfrontend.backend.BackendEmployee;
import com.kodilla.restaurantfrontend.domain.Employee;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class EmployeeMapper {
    public BackendEmployee mapToBackendEmployee(Employee employee){
        return new BackendEmployee(
                Long.parseLong(employee.getId()),
                employee.getFirstName(),
                employee.getLastName(),
                employee.getEmail(),
                employee.getPhone(),
                LocalDate.now(),
                employee.getLogin(),
                employee.getPassword(),
                employee.getPIN()
        );
    }
}
