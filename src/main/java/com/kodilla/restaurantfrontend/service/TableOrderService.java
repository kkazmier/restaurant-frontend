package com.kodilla.restaurantfrontend.service;

import com.kodilla.restaurantfrontend.backend.BackendTableOrder;
import com.kodilla.restaurantfrontend.domain.Dish;
import com.kodilla.restaurantfrontend.domain.TableOrder;
import com.kodilla.restaurantfrontend.mapper.TableOrderMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Service
public class TableOrderService {
    private Logger logger = LoggerFactory.getLogger(TableOrder.class);
    private RestTemplate restTemplate = new RestTemplate();
    private final String endpoint = "http://localhost:8081/v1/tableOrder/";
    private TableOrderMapper tableOrderMapper = new TableOrderMapper();

    public List<TableOrder> getTableOrders(){
        String url = endpoint + "all";
        TableOrder[] response = restTemplate.getForObject(url, TableOrder[].class);
        return Arrays.asList(response);
    }

    public TableOrder getTableOrder(Long id){
        String url = endpoint + "get/" + id;
        return restTemplate.getForObject(url, TableOrder.class);
    }

    public void createTableOrder(TableOrder order){
        BackendTableOrder backendTableOrder = tableOrderMapper.mapToBackendTableOrder(order);
        URI url = createUrl("create", backendTableOrder);
        restTemplate.postForObject(url, backendTableOrder, BackendTableOrder.class);
    }

    public void deleteTableOrder(Long id){
        String url = endpoint + "delete/" + id;
        restTemplate.delete(url);
    }

    public List<Dish> getDishes(Long id){
        String url = endpoint + "getDishes/" + id;
        return Arrays.asList(restTemplate.getForObject(url, Dish[].class));
    }

    public void addDish(Long dishId, Long orderId){
        String url = endpoint + "addDish/" + dishId + "/toTableOrder/" + orderId;
        restTemplate.put(url, null);
    }

    public void removeDish(Long dishId, Long orderId){
        String url = endpoint + "removeDish/" + dishId + "/fromTableOrder/" + orderId;
        restTemplate.put(url, null);
    }

    private URI createUrl(String name, BackendTableOrder order){
        URI url = UriComponentsBuilder.fromHttpUrl(endpoint + name)
                .queryParam("status", order.getStatus())
                .queryParam("createdTime", order.getCreatedTime())
                .queryParam("closedTime", order.getClosedTime())
                .queryParam("totalCost", order.getTotalCost())
                .queryParam("description", order.getDescription())
                .build().encode().toUri();
        return url;
    }
}
