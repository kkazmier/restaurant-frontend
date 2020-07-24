package com.kodilla.restaurantfrontend.service;

import com.kodilla.restaurantfrontend.backend.BackendDish;
import com.kodilla.restaurantfrontend.backend.BackendIngredient;
import com.kodilla.restaurantfrontend.domain.Dish;
import com.kodilla.restaurantfrontend.domain.Ingredient;
import com.kodilla.restaurantfrontend.mapper.DishMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.lang.reflect.Array;
import java.net.URI;
import java.util.Arrays;
import java.util.List;

import static java.util.Optional.ofNullable;

@Service
public class DishService {
    private Logger logger = LoggerFactory.getLogger(Dish.class);
    private DishMapper mapper = new DishMapper();
    private RestTemplate restTemplate = new RestTemplate();
    private final String endpoint = "http://localhost:8081/v1/dish/";

    public List<Dish> getDishes(){
        String url = endpoint + "all";
        Dish[] response = restTemplate.getForObject(url, Dish[].class);
        logger.info("Get: " + response.length + " dish(es)");
        return Arrays.asList(ofNullable(response).orElse(new Dish[0]));
    }

    public Dish getDish(Long id){
        String url = endpoint + "get/" + id;
        Dish response = restTemplate.getForObject(url, Dish.class);
        logger.info("Get dish: " + response.toString());
        return response;
    }

    public void createDish(Dish dish){
        BackendDish backendDish = mapper.mapToBackendDish(dish);
        URI url = createUrl("create", backendDish);
        restTemplate.postForObject(url, backendDish, BackendDish.class);
    }

    public void saveDish(Dish dish){
        BackendDish backendDish = mapper.mapToBackendDish(dish);
        URI url = createUrl("update", backendDish);
        restTemplate.put(url, backendDish);
    }

    public void deleteDish(Long id){
        String url = endpoint + "delete/" + id;
        restTemplate.delete(url);
        logger.info("deleted dish has id: " + id);
    }

    public List<Ingredient> getIngredients(Long id){
        String url = endpoint + "getIngredients/" + id;
        Ingredient[] response = restTemplate.getForObject(url, Ingredient[].class);
        return Arrays.asList(response);
    }

    private URI createUrl(String name, BackendDish dish){
        URI url = UriComponentsBuilder.fromHttpUrl(endpoint + name)
                .queryParam("name", dish.getName())
                .queryParam("type", dish.getType())
                .queryParam("price", dish.getPrice())
                .queryParam("description", dish.getDescription())
                .build().encode().toUri();
        return url;
    }
}
