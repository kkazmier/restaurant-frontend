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

    public void setPIN(String pin, Long id){
        String url = endpoint + "setPIN/" + pin + "/employee/" + id;
        restTemplate.put(url, null);
        logger.info("Set PIN to: " + pin);
    }
}
