package com.redcar.order.domain;

class CarNotFoundException extends RuntimeException {

    CarNotFoundException(String carName) {
        super("Could find a car with with name: " + carName);
    }
}
