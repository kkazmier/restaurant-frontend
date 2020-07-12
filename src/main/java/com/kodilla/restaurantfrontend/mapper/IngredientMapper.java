package com.kodilla.restaurantfrontend.mapper;

import com.kodilla.restaurantfrontend.backend.BackendIngredient;
import com.kodilla.restaurantfrontend.domain.Ingredient;

public class IngredientMapper {
    public BackendIngredient mapToBackendIngredient(Ingredient ingredient){
        return new BackendIngredient(
                Long.parseLong(ingredient.getId()),
                ingredient.getName(),
                ingredient.getType(),
                Double.parseDouble(ingredient.getQuantity()),
                ingredient.getMeasureUnit(),
                Double.parseDouble(ingredient.getPrice()),
                ingredient.getDescription()
        );
    }
}
