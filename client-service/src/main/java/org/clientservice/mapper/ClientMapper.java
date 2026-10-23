package org.clientservice.mapper;

import org.clientservice.dto.ClientCollectionDto;
import org.clientservice.dto.ClientCreateUpdateDto;
import org.clientservice.dto.ClientResponseDto;
import org.clientservice.model.Client;

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
        return ClientResponseDto.builder()
                .id(client.getId())
                .name(client.getName())
                .age(client.getAge())
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
