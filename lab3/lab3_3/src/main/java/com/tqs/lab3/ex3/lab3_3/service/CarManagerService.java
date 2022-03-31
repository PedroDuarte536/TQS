package com.tqs.lab3.ex3.lab3_3.service;

import java.util.List;
import java.util.Optional;

import com.tqs.lab3.ex3.lab3_3.data.Car;
import com.tqs.lab3.ex3.lab3_3.data.CarRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CarManagerService {
  
  @Autowired CarRepository carRepository;

  public Car save (Car car) {
    return carRepository.save(car);
  }

  public List<Car> getAllCars () {
    return carRepository.findAll();
  }

  public Optional<Car> getCarDetails (Long carId) {
    Car car = carRepository.findByCarId(carId);
    return car == null ? Optional.empty() : Optional.of(car);
  }
  
}
