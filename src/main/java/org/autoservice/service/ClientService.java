package org.autoservice.service;

import lombok.AllArgsConstructor;
import org.autoservice.model.Client;
import org.autoservice.repository.ClientRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class ClientService {
    private final ClientRepository clientRepository;

    public List<Client> findAllClients() {
        return clientRepository.findAll();
    }

    public Client findClientById(UUID id) {
        return clientRepository.findById(id).orElse(null);
    }

    public Client saveClient(Client client) {
        return clientRepository.save(client);
    }

    public void deleteClientById(UUID id) {
        clientRepository.deleteById(id);
    }
}
