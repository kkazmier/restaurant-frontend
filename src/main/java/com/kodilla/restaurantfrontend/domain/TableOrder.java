package com.kodilla.restaurantfrontend.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TableOrder<DateTimeField> {
    private String id;
    private String status;
    private DateTimeField createdTime;
    private DateTimeField closedTime;
    private String totalCost;
    private String description;
    private String name;
}
