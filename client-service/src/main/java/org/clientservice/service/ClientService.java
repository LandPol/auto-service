package org.clientservice.service;

import lombok.AllArgsConstructor;
import org.clientservice.model.Client;
import org.clientservice.repository.ClientRepository;
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

    public boolean deleteClientById(UUID id) {
        if (clientRepository.existsById(id)) {
            clientRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public Client updateClient(Client newClient) {
        Client client = clientRepository.findById(newClient.getId()).orElse(null);
        if (client != null) {
            client.setName(newClient.getName());
            client.setAge(newClient.getAge());
            return clientRepository.save(client);
        }
        return null;
    }
}
