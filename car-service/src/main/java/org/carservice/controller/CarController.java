package org.carservice.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.carservice.dto.*;
import org.carservice.mapper.CarMapper;
import org.carservice.model.Car;
import org.carservice.model.Client;
import org.carservice.service.CarService;
import org.carservice.service.ClientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@RestController
@RequestMapping("/api/clients/{clientId}/cars")
public class CarController {
    private final CarService carService;
    private final ClientService clientService;

    @GetMapping()
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

    @GetMapping("/{id}")
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

    @PostMapping()
    public ResponseEntity<CarResponseDto> createCar(@PathVariable UUID clientId, @Valid @RequestBody CarCreateUpdateDto newCar) {
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

    @PutMapping("/{id}")
    public ResponseEntity<CarResponseDto> updateCar(@PathVariable UUID clientId, @PathVariable UUID id, @Valid @RequestBody CarCreateUpdateDto newCar) {
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

    @DeleteMapping("/{id}")
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
