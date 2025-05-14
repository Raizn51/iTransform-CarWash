package com.carwash.car_service.controller;

import com.carwash.car_service.dto.CarDTO;
import com.carwash.car_service.model.Car;
import com.carwash.car_service.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class CarController
{
    @Autowired
    CarService carService;

    @PostMapping("/add")
    public ResponseEntity<Boolean> addCar(@RequestBody CarDTO carDTO)
    {
        Car car = new Car(carDTO);
        boolean result =  carService.addCar(car);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/get")
    public ResponseEntity<List<Car>> getAllCars() {
        List<Car> cars = carService.getAllCars();
        if (cars != null && !cars.isEmpty()) {
            return ResponseEntity.ok(cars);
        } else {
            return ResponseEntity.noContent().build(); // No cars found
        }
    }


    @GetMapping("/get/{id}")
    public ResponseEntity<Optional<Car>> getCarById(@PathVariable int id)
    {
        Optional<Car> car = carService.getCarById(id);
        if (car != null) {
            return ResponseEntity.ok(car);

        } else {
            return ResponseEntity.notFound().build(); // Car not found
        }
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Car> updateCar(@PathVariable int id, @RequestBody CarDTO carDTO) {
        Car updatedCar = carService.updateCar(id, carDTO);
        return new ResponseEntity<>(updatedCar, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteCar(@PathVariable int id) {
        carService.deleteCar(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
