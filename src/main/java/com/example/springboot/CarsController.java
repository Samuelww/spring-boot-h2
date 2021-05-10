package com.example.springboot;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
class CarsController {

    private final CarsRepository repository;

    CarsController(CarsRepository repository) {
        this.repository = repository;
    }


    // Aggregate root
    // tag::get-aggregate-root[]
    @GetMapping("/Cars")
    List<Cars> all() {
        return repository.findAll();
    }
   //  end::get-aggregate-root[]

    @PostMapping("/Cars")
    Cars newCar(@RequestBody Cars newCar){
            newCar.setBrand(newCar.getBrand());
            newCar.setModel(newCar.getModel());

                    return repository.save(newCar);
    }

   //  Single item

   @GetMapping("/employees/{id}")
   Cars one(@PathVariable Long id) {

          return repository.findById(id)
                .orElseThrow(() -> new CarNotFoundException(id));
    }

    @PutMapping("/Cars/{id}")
    Cars replaceCar(@RequestBody Cars newCar, @PathVariable Long id) {

       return repository.findById(id)
                .map(car -> {
                    car.setBrand(newCar.getBrand());
                    car.setModel(newCar.getModel());
                    return repository.save(car);
               })
                .orElseGet(() -> {
                    newCar.setId(id);
                    return repository.save(newCar);
                });
    }

    @DeleteMapping("/Cars/{id}")
    void deleteCar(@PathVariable Long id) {
        repository.deleteById(id);
    }
}