package org.autoservice.controller;

import lombok.AllArgsConstructor;
import org.autoservice.dto.*;
import org.autoservice.mapper.CarMapper;
import org.autoservice.model.Car;
import org.autoservice.service.CarService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@RestController
@RequestMapping("/api/cars")
public class CarController {
    private final CarService carService;

    @GetMapping()
    public ResponseEntity<List<CarCollectionDto>> getAllCars() {
        List<CarCollectionDto> dtoList = new ArrayList<>();
        List<Car> cars = carService.findAllCars();
        for (Car car : cars) {
            dtoList.add(CarMapper.mapCarToCarCollectionDto(car));
        }
        return new ResponseEntity<>(dtoList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CarResponseDto> getCarById(@PathVariable UUID id) {
        Car car = carService.findCarById(id);
        if (car != null) {
            return new ResponseEntity<>(CarMapper.mapCarToCarResponseDto(car), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @PostMapping()
    public ResponseEntity<CarResponseDto> createCar(@RequestBody CarCreateUpdateDto newCar) {
        if (newCar != null) {
            Car car = CarMapper.mapCarCreateUpdateDtoToCar(newCar);
            carService.saveCar(car);
            return new ResponseEntity<>(CarMapper.mapCarToCarResponseDto(car), HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CarResponseDto> updateCar(@PathVariable UUID id, @RequestBody CarCreateUpdateDto newCar) {
        if (newCar != null && carService.findCarById(id) != null) {
            Car car = CarMapper.mapCarCreateUpdateDtoToCar(newCar);
            carService.updateCar(car);
            return new ResponseEntity<>(CarMapper.mapCarToCarResponseDto(car), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCar(@PathVariable UUID id) {
        if (carService.deleteCarById(id)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
