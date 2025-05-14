package com.carwash.car_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarDTO {
    public String brand;
    public String model;
    public String licensePlate;
    public String color;
    public String image;
}
