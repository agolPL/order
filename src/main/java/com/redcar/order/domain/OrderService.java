package com.redcar.order.domain;

import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Slf4j
@Service
class OrderService {

    private final RestTemplate restClient;
    private final OrderRepository orderRepository;

    OrderService(RestTemplate restClient, OrderRepository orderRepository) {
        this.restClient = restClient;
        this.orderRepository = orderRepository;
    }

    Order createOrder(OrderDto order) {
        log.info("Got order {}", order);
        val car = findCar(order.getCarName());
        return orderRepository.save(Order.from(car));
    }

    private Car findCar(String carName) {

        val cars = restClient
                .getForObject(
                        "http://catalog:8080/api/cars",
                        Car[].class);

        return Arrays.stream(cars)
                .filter(c -> c.getName().equals(carName))
                .findFirst()
                .orElseThrow(() -> new CarNotFoundException(carName));
    }

    Order getOne(String id) {
        return orderRepository.findOne(id);
    }

    List<Order> getAll() {
        return orderRepository.findAll();
    }
}
