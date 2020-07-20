package com.kodilla.restaurantfrontend.mapper;

import com.kodilla.restaurantfrontend.backend.BackendDish;
import com.kodilla.restaurantfrontend.domain.Dish;

public class DishMapper {
    public BackendDish mapToBackendDish(Dish dish){
        return new BackendDish(
                Long.parseLong(dish.getId()),
                dish.getName(),
                dish.getType(),
                Double.parseDouble(dish.getPrice()),
                dish.getDescription());
    }
}
