package com.kodilla.restaurantfrontend.service;

import com.kodilla.restaurantfrontend.domain.Ingredient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

import static java.util.Optional.ofNullable;

@Service
public class IngredientService {
    Logger logger = LoggerFactory.getLogger(Ingredient.class);

    RestTemplate restTemplate = new RestTemplate();

    public List<Ingredient> getIngredients(){
        String url = "http://localhost:8081/v1/ingredient/all";
        Ingredient[] response = restTemplate.getForObject(url, Ingredient[].class);
        logger.info("get " + response.length + " ingredient(s)");
        return Arrays.asList(ofNullable(response).orElse(new Ingredient[0]));
    }

    public void saveIngredient(Ingredient ingredient){
        
    }
}
