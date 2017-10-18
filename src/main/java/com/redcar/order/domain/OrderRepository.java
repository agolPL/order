package com.redcar.order.domain;

import lombok.val;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
class OrderRepository {

    private final Map<String, Order> orders = new HashMap<>();

    Order save(Order order) {
        orders.put(order.getId(), order);
        return order;
    }

    Order findOne(String id) {
        return orders.get(id);
    }

    List<Order> findAll() {
        return new ArrayList<>(orders.values());
    }
}
