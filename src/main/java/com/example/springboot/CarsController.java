package com.example.springboot;

import java.util.List;
import org.springframework.web.bind.annotation.*;

@RestController
class CarsController {

    private final CarsRepository repository;

    CarsController(CarsRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/Cars")
    List<Cars> all() {
        return repository.findAll();
    }

    @PostMapping("/Cars")
    Cars newCar(@RequestBody Cars newCar){
            newCar.setBrand(newCar.getBrand());
            newCar.setModel(newCar.getModel());

                    return repository.save(newCar);
    }

    @RequestMapping(value = "/Cars/{id}", method = RequestMethod.PUT)
    Cars replaceCar(@RequestBody Cars newCar, @PathVariable Long id) throws Exception {
        return repository.findById(id)
                .map(Car -> {
                    Car.setBrand(newCar.getBrand());
                    Car.setModel(newCar.getModel());
                    return repository.save(Car)
                            ;
                })
                .orElseThrow(() -> new Exception("Not found"));
    }

    @DeleteMapping("/Cars/{id}")
    void deleteCar(@PathVariable Long id) {
        repository.deleteById(id);
    }
}