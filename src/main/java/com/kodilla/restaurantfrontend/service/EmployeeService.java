package com.kodilla.restaurantfrontend.service;

import com.kodilla.restaurantfrontend.backend.BackendEmployee;
import com.kodilla.restaurantfrontend.domain.Employee;
import com.kodilla.restaurantfrontend.mapper.EmployeeMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class EmployeeService {
    private final String endpoint = "http://localhost:8081/v1/employee/";
    private RestTemplate restTemplate = new RestTemplate();
    private EmployeeMapper employeeMapper = new EmployeeMapper();

    public List<Employee> getEmployees(){
        String url = endpoint + "all";
        Employee[] response = restTemplate.getForObject(url, Employee[].class);
        return Arrays.asList(response);
    }

    public Employee getEmployee(Long id){
        String url = endpoint + "get/" + id;
        return restTemplate.getForObject(url, Employee.class);
    }

    public void addEmployee(Employee employee){
        String url = endpoint + "create";
        restTemplate.postForObject(url, employeeMapper.mapToBackendEmployee(employee), BackendEmployee.class);
    }

    public void updateEmployee(Employee employee){
        String url = endpoint + "update";
        restTemplate.put(url, employeeMapper.mapToBackendEmployee(employee));
    }

    public void removeEmployee(Long id){
        String url = endpoint + "create";
    }
}
