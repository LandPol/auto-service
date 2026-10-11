package org.autoservice.controller;

import lombok.AllArgsConstructor;
import org.autoservice.dto.ClientCollectionDto;
import org.autoservice.dto.ClientCreateUpdateDto;
import org.autoservice.dto.ClientResponseDto;
import org.autoservice.mapper.ClientMapper;
import org.autoservice.model.Client;
import org.autoservice.service.ClientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@RestController
@RequestMapping("/api/clients")
public class ClientController {
    private final ClientService clientService;

    @GetMapping()
    public ResponseEntity<List<ClientCollectionDto>> getAllClients() {
        List<ClientCollectionDto> dtoList = new ArrayList<>();
        List<Client> clients = clientService.findAllClients();
        for (Client client : clients) {
            dtoList.add(ClientMapper.mapClientToClientCollectionDto(client));
        }
        return new ResponseEntity<>(dtoList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClientResponseDto> getClientById(@PathVariable UUID id) {
        Client client = clientService.findClientById(id);
        if (client != null) {
            return new ResponseEntity<>(ClientMapper.mapClientToClientResponseDto(client), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping()
    public ResponseEntity<ClientResponseDto> createClient(@RequestBody ClientCreateUpdateDto newClient) {
        if (newClient != null) {
            Client client = ClientMapper.mapClientCreateUpdateDtoToClient(newClient);
            clientService.saveClient(client);
            return new ResponseEntity<>(ClientMapper.mapClientToClientResponseDto(client), HttpStatus.CREATED);
        }

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClientResponseDto> createClient(@PathVariable UUID id, @RequestBody ClientCreateUpdateDto newClient) {
        if (newClient != null && clientService.findClientById(id) != null) {
            Client client = ClientMapper.mapClientCreateUpdateDtoToClient(newClient);
            clientService.updateClient(client);

            return new ResponseEntity<>(ClientMapper.mapClientToClientResponseDto(client), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClient(@PathVariable UUID id) {
        if (clientService.deleteClientById(id)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
