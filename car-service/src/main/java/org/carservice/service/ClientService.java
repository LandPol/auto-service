package org.carservice.service;

import lombok.AllArgsConstructor;
import org.carservice.model.Client;
import org.springframework.stereotype.Service;
import org.carservice.repository.ClientRepository;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class ClientService {
    private final ClientRepository clientRepository;

    public Client findClientById(UUID id) {
        return clientRepository.findById(id).orElse(null);
    }

    public Client saveClient(Client client) {
        return clientRepository.save(client);
    }

    public boolean deleteClientById(UUID id) {
        if (clientRepository.existsById(id)) {
            clientRepository.deleteById(id);
            return true;
        }
        return false;
    }
}