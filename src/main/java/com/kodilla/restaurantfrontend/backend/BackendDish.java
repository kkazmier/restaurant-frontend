package com.kodilla.restaurantfrontend.backend;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BackendDish {
    private Long id;
    private String name;
    private String type;
    private Double price;
    private String description;
}
