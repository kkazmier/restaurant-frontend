package com.kodilla.restaurantfrontend.mapper;

import com.kodilla.restaurantfrontend.backend.BackendTableOrder;
import com.kodilla.restaurantfrontend.domain.TableOrder;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class TableOrderMapper {
    public BackendTableOrder mapToBackendTableOrder(TableOrder order){
        BackendTableOrder backendOrder = new BackendTableOrder();
        if (order.getId() != null){
            backendOrder.setId(Long.parseLong(order.getId()));
        }
        if (order.getStatus() != null){
            backendOrder.setStatus(order.getStatus());
        }
        if (order.getCreatedTime() != null){
            backendOrder.setCreatedTime(LocalDateTime.parse(order.getCreatedTime().toString()));
        }
        if (order.getClosedTime() != null){
            backendOrder.setClosedTime(LocalDateTime.parse(order.getClosedTime().toString()));
        }
        if (order.getTotalCost() != null){
            backendOrder.setTotalCost(new BigDecimal(order.getTotalCost()));
        }
        if (order.getDescription() != null){
            backendOrder.setDescription(order.getDescription());
        }
        if (order.getName() != null){
            backendOrder.setName(order.getName());
        }
        return backendOrder;
    }
}
