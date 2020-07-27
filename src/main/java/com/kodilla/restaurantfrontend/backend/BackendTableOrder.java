package com.kodilla.restaurantfrontend.backend;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BackendTableOrder {
    private Long id;
    private String status;
    private LocalDateTime createdTime;
    private LocalDateTime closedTime;
    private Double totalCost;
    private String description;
    private String name;
}
