package org.autoservice.controller;

import lombok.AllArgsConstructor;
import org.autoservice.dto.*;
import org.autoservice.mapper.CarMapper;
import org.autoservice.model.Car;
import org.autoservice.model.Client;
import org.autoservice.service.CarService;
import org.autoservice.service.ClientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@RestController
public class CarController {
    private final CarService carService;
    private final ClientService clientService;

    @GetMapping("/api/clients/{clientId}/cars")
    public ResponseEntity<List<CarCollectionDto>> getAllCars(@PathVariable UUID clientId) {
        if (clientService.findClientById(clientId) == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        List<CarCollectionDto> dtoList = new ArrayList<>();
        List<Car> cars = carService.findCarsByClientId(clientId);
        for (Car car : cars) {
            dtoList.add(CarMapper.mapCarToCarCollectionDto(car));
        }
        return new ResponseEntity<>(dtoList, HttpStatus.OK);
    }

    @GetMapping("/api/clients/{clientId}/cars/{id}")
    public ResponseEntity<CarResponseDto> getCarById(@PathVariable UUID clientId, @PathVariable UUID id) {
        if (clientService.findClientById(clientId) == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Car car = carService.findCarById(id);
        if (car != null) {
            return new ResponseEntity<>(CarMapper.mapCarToCarResponseDto(car), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @PostMapping("/api/clients/{clientId}/cars")
    public ResponseEntity<CarResponseDto> createCar(@PathVariable UUID clientId, @RequestBody CarCreateUpdateDto newCar) {
        Client clientOwner = clientService.findClientById(clientId);
        if (clientOwner == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        if (newCar != null) {
            Car car = CarMapper.mapCarCreateUpdateDtoToCar(newCar);
            car.setClient(clientOwner);
            carService.saveCar(car);
            return new ResponseEntity<>(CarMapper.mapCarToCarResponseDto(car), HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/api/clients/{clientId}/cars/{id}")
    public ResponseEntity<CarResponseDto> updateCar(@PathVariable UUID clientId, @PathVariable UUID id, @RequestBody CarCreateUpdateDto newCar) {
        if (clientService.findClientById(clientId) == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        if (newCar != null && carService.findCarById(id) != null) {
            Car car = CarMapper.mapCarCreateUpdateDtoToCar(newCar);
            car.setId(id);
            car.setClient(clientService.findClientById(clientId));
            return new ResponseEntity<>(CarMapper.mapCarToCarResponseDto(carService.updateCar(car)), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/api/clients/{clientId}/cars/{id}")
    public ResponseEntity<Void> deleteCar(@PathVariable UUID clientId, @PathVariable UUID id) {
        if (clientService.findClientById(clientId) == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        if (carService.deleteCarById(id)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
