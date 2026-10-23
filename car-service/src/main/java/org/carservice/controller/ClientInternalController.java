package org.carservice.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.carservice.model.Client;
import org.carservice.service.ClientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@AllArgsConstructor
@RestController
@RequestMapping("internal/clients")
public class ClientInternalController {
    private final ClientService clientService;

    @PostMapping()
    public ResponseEntity<Client> createClient(@Valid @RequestBody Client newClient) {
        Client savedClient = clientService.saveClient(newClient);
        return new ResponseEntity<>(savedClient, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClientById(@PathVariable UUID id) {
        boolean result = clientService.deleteClientById(id);
        if (result) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
