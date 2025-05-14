package com.carwash.car_service.model;

import com.carwash.car_service.dto.CarDTO;
import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity
@Data
@RequiredArgsConstructor
public class Car
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int carId;
    private String brand;
    private String model;
    @Column(unique = true)
    private String licensePlate;
    private String color;
    private String image;



    public Car(CarDTO carDTO) {
        this.brand = carDTO.brand;
        this.model = carDTO.model;
        this.licensePlate = carDTO.licensePlate;
        this.color = carDTO.color;
        this.image = carDTO.image;
    }

}
