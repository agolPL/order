package com.redcar.order.domain;

import lombok.Getter;

import java.util.UUID;

import static lombok.AccessLevel.PACKAGE;

class Order {

    @Getter(PACKAGE)
    private final String id;
    private final String carName;

    Order(String carName) {
        this.carName = carName;
        id = UUID.randomUUID().toString();
    }

    static Order from(Car car) {
        return new Order(car.getName());
    }

    OrderDto toDto() {
        return new OrderDto()
                .setId(id)
                .setCarName(carName);
    }
}
