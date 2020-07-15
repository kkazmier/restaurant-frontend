package com.kodilla.restaurantfrontend.service;

import com.google.gson.Gson;
import com.kodilla.restaurantfrontend.backend.BackendIngredient;
import com.kodilla.restaurantfrontend.domain.Ingredient;
import com.kodilla.restaurantfrontend.mapper.IngredientMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Arrays;
import java.util.List;

import static java.util.Optional.ofNullable;

@Service
public class IngredientService {
    private Logger logger = LoggerFactory.getLogger(Ingredient.class);
    private IngredientMapper mapper = new IngredientMapper();
    private RestTemplate restTemplate = new RestTemplate();
    private final String endpoint = "http://localhost:8081/v1/ingredient/";

    public List<Ingredient> getIngredients(){
        String url = endpoint + "all";
        Ingredient[] response = restTemplate.getForObject(url, Ingredient[].class);
        logger.info("get " + response.length + " ingredient(s)");
        return Arrays.asList(ofNullable(response).orElse(new Ingredient[0]));
    }

    public Ingredient getIngredient(Long id){
        String url = endpoint + "get/" + id;
        Ingredient response = restTemplate.getForObject(url, Ingredient.class);
        logger.info("get " + response.toString());
        return response;
    }

    public void createIngredient(Ingredient ingredient){
        BackendIngredient backendIngredient = mapper.mapToBackendIngredient(ingredient);
        URI url = createUrl("create", backendIngredient);
        restTemplate.postForObject(url, backendIngredient, BackendIngredient.class);
    }

    public void saveIngredient(Ingredient ingredient){
        BackendIngredient backendIngredient = mapper.mapToBackendIngredient(ingredient);
        URI url = createUrl("update", backendIngredient);
        restTemplate.put(url, backendIngredient);
    }

    public void deleteIngredient(Long id){
        String url = endpoint + "delete/" + id;
        restTemplate.delete(url);
        logger.info("deleted ingredient has id: " + id);
    }

    private URI createUrl(String name, BackendIngredient ingredient){
        URI url = UriComponentsBuilder.fromHttpUrl(endpoint + name)
                .queryParam("name", ingredient.getName())
                .queryParam("type", ingredient.getType())
                .queryParam("quantity", ingredient.getQuantity())
                .queryParam("measureUnit", ingredient.getMeasureUnit())
                .queryParam("price", ingredient.getPrice())
                .queryParam("description", ingredient.getDescription())
                .build().encode().toUri();
        return url;
    }
}
