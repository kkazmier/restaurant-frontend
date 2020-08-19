package com.kodilla.restaurantfrontend.mapper;

import com.kodilla.restaurantfrontend.backend.BackendTableOrder;
import com.kodilla.restaurantfrontend.domain.TableOrder;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class TableOrderMapper {
    public BackendTableOrder mapToBackendTableOrder(TableOrder order){
        return new BackendTableOrder(
                Long.parseLong(order.getId()),
                order.getStatus(),
                LocalDateTime.parse(order.getCreatedTime().toString()),
                LocalDateTime.parse(order.getClosedTime().toString()),
                new BigDecimal(order.getTotalCost()),
                order.getDescription(),
                order.getName()
        );
    }
}
