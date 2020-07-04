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
    private Long id;
    private String name;
    private String type;
    private Double quantity;
    private String measureUnit;
    private Double price;
    private String description;
}
