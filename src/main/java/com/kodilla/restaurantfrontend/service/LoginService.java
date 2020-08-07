package com.kodilla.restaurantfrontend.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class LoginService {
    private Logger logger = LoggerFactory.getLogger(LoginService.class);
    private RestTemplate restTemplate = new RestTemplate();
    private final String endpoint = "http://localhost:8081/v1/employee/";

    public String getPIN(Long id){
        String url = endpoint + "getPIN/" + id;
        String pin = restTemplate.getForObject(url, null);
        logger.info("Get PIN: " + pin);
        return pin;
    }

    public Boolean setPIN(String pin, Long id){
        String url = endpoint + "setPIN/" + pin + "/employee/" + id;
        Boolean request = null;
        restTemplate.put(url, request);
        logger.info("Set PIN to: " + pin);
        return request;
    }

    public Long getActuallyActiveUserId(String pin){
        String url = endpoint + "getEmployeeIdByPIN/" + pin;
        Long id = restTemplate.getForObject(url, Long.TYPE);
        logger.info("User id: " + id);
        return id;
    }
}
