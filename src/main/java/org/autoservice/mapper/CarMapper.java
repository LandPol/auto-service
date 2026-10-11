package org.autoservice.mapper;

import org.autoservice.dto.CarCollectionDto;
import org.autoservice.dto.CarCreateUpdateDto;
import org.autoservice.dto.CarResponseDto;
import org.autoservice.model.Car;

import java.util.UUID;

public class CarMapper {
    //CarCreateUpdateDto
    public static CarCreateUpdateDto mapCarToCarCreateUpdateDto(Car car) {
        return CarCreateUpdateDto.builder()
                .model(car.getModel())
                .type(car.getType())
                .productionYear(car.getProductionYear())
                .build();
    }

    public static Car mapCarCreateUpdateDtoToCar(CarCreateUpdateDto carCreateUpdateDto) {
        return Car.builder()
                .id(UUID.randomUUID())
                .model(carCreateUpdateDto.getModel())
                .type(carCreateUpdateDto.getType())
                .productionYear(carCreateUpdateDto.getProductionYear())
                .build();
    }

    //CarResponseDto
    public static CarResponseDto mapCarToCarResponseDto(Car car) {
        return CarResponseDto.builder()
                .id(car.getId())
                .model(car.getModel())
                .type(car.getType())
                .productionYear(car.getProductionYear())
                .clientId(car.getClient().getId())
                .build();
    }

    //CarCollectionDto
    public static CarCollectionDto mapCarToCarCollectionDto(Car car) {
        return CarCollectionDto.builder()
                .id(car.getId())
                .description(car.getModel() + " " + car.getType() + " " + car.getProductionYear())
                .build();
    }
}
