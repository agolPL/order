package com.redcar.order.domain;


import lombok.val;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

import static java.util.stream.Collectors.toList;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestController
@RequestMapping("/api/orders")
class OrderController {

    private final OrderService orderService;

    OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    @ResponseStatus(CREATED)
    ResponseEntity createOrder(@RequestBody OrderDto orderDto) {
        val order = orderService.createOrder(orderDto);
        return buildResponse(order);
    }

    private ResponseEntity buildResponse(Order order) {
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(order.getId()).toUri();

        return ResponseEntity.created(location).build();
    }

    @GetMapping("{id}")
    OrderDto getOne(@PathVariable("id") String id) {
        return orderService.getOne(id).toDto();
    }

    @GetMapping
    List<OrderDto> getAll() {
        return orderService.getAll().stream()
                .map(Order::toDto)
                .collect(toList());
    }

    @ExceptionHandler(CarNotFoundException.class)
    @ResponseStatus(NOT_FOUND)
    void handleCarNotFoundException() {
    }
}
