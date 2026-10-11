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

    public List<Car> findAllCars() {
        return carRepository.findAll();
    }

    public Car findCarById(UUID id) {
        return carRepository.findById(id).orElse(null);
    }

    public Car saveCar(Car car) {
        return carRepository.save(car);
    }

    public boolean deleteCarById(UUID id) {
        if (carRepository.existsById(id)) {
            carRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public List<Car> findCarsByClientId(UUID clientId) {
        return carRepository.findByClientId(clientId);
    }

    public Car updateCar(Car newCar) {
        Car car = carRepository.findById(newCar.getId()).orElse(null);
        if (car != null) {
            car.setModel(newCar.getModel());
            car.setType(newCar.getType());
            car.setProductionYear(newCar.getProductionYear());
            car.setClient(newCar.getClient());
            return carRepository.save(car);
        }
        return null;
    }
}
