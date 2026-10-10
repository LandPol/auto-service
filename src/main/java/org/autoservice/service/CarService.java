package org.autoservice.service;

import lombok.AllArgsConstructor;
import org.autoservice.model.Car;
import org.autoservice.repository.CarRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class CarService {
    private final CarRepository carRepository;

    public List<Car> findAll() {
        return carRepository.findAll();
    }

    public Car findById(UUID id) {
        return carRepository.findById(id).orElse(null);
    }

    public Car saveCar(Car car) {
        return carRepository.save(car);
    }

    public void deleteCarById(UUID id) {
        carRepository.deleteById(id);
    }

    public List<Car> findByClientId(UUID clientId) {
        return carRepository.findByClientId(clientId);
    }
}
