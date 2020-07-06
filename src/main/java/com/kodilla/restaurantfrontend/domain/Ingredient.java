package com.kodilla.restaurantfrontend.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Ingredient {
    private String id;
    private String name;
    private String type;
    private String quantity;
    private String measureUnit;
    private String price;
    private String description;
}
