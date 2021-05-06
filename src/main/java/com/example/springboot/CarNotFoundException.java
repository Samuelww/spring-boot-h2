package com.example.springboot;

class CarNotFoundException extends RuntimeException {

    CarNotFoundException(Long id) {
        super("Could not find employee " + id);
    }
}