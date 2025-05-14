package com.carwash.car_service.service;

import com.carwash.car_service.dto.CarDTO;
import com.carwash.car_service.model.Car;
import com.carwash.car_service.repository.CarDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarService
{
    @Autowired
    private CarDAO carDAO;

    public boolean addCar(Car car) {

         String licensePlate = car.getLicensePlate();
        if(carDAO.findByLicensePlate(licensePlate).isPresent()) return false;
        carDAO.save(car);
        return true;

    }

    public List<Car> getAllCars() {
        return carDAO.findAll();
    }

    public Optional<Car> getCarById(int id) {
        return carDAO.findById(id);
    }

    public Car updateCar(int id, CarDTO carDTO) {
        Car existingCar = carDAO.findById(id)
                .orElseThrow(() -> new RuntimeException("Car not found with id: " + id));

        existingCar.setBrand(carDTO.getBrand());
        existingCar.setModel(carDTO.getModel());
        existingCar.setLicensePlate(carDTO.getLicensePlate());
        existingCar.setColor(carDTO.getColor());
        existingCar.setImage(carDTO.getImage());

        return carDAO.save(existingCar);
    }

    public void deleteCar(int id) {
        if (!carDAO.existsById(id)) {
            throw new RuntimeException("Car not found with id: " + id);
        }
        carDAO.deleteById(id);
    }
}
