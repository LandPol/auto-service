package org.autoservice.mapper;

import org.autoservice.dto.CarResponseDto;
import org.autoservice.dto.ClientCollectionDto;
import org.autoservice.dto.ClientCreateUpdateDto;
import org.autoservice.dto.ClientResponseDto;
import org.autoservice.model.Car;
import org.autoservice.model.Client;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ClientMapper {
    //ClientCreateUpdateDto
    public static ClientCreateUpdateDto mapClientToClientCreateUpdateDto(Client client) {
        return ClientCreateUpdateDto.builder()
                .name(client.getName())
                .age(client.getAge())
                .build();
    }

    public static Client mapClientCreateUpdateDtoToClient(ClientCreateUpdateDto clientCreateUpdateDto) {
        return Client.builder()
                .id(UUID.randomUUID())
                .name(clientCreateUpdateDto.getName())
                .age(clientCreateUpdateDto.getAge())
                .build();
    }

    //ClientResponseDto
    public static ClientResponseDto mapClientToClientResponseDto(Client client) {
        List<CarResponseDto> carResponseDtoList = new ArrayList<>();
        for (Car car : client.getCars()) {
            carResponseDtoList.add(CarMapper.mapCarToCarResponseDto(car));
        }
        return ClientResponseDto.builder()
                .id(client.getId())
                .name(client.getName())
                .age(client.getAge())
                .cars(carResponseDtoList)
                .build();
    }

    //ClientCollectionDto
    public static ClientCollectionDto mapClientToClientCollectionDto(Client client) {
        return ClientCollectionDto.builder()
                .id(client.getId())
                .name(client.getName())
                .build();
    }
}
