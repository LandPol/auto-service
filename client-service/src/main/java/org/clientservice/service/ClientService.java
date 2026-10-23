package org.clientservice.service;

import lombok.RequiredArgsConstructor;
import org.clientservice.model.Client;
import org.clientservice.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;
import java.util.UUID;

import static org.springframework.http.MediaType.APPLICATION_JSON;

@Service
@RequiredArgsConstructor
public class ClientService {
    private final ClientRepository clientRepository;

    private final RestClient.Builder restClientBuilder;

    @Value("${car.service.url}")
    private String carServiceUrl;

    public List<Client> findAllClients() {
        return clientRepository.findAll();
    }

    public Client findClientById(UUID id) {
        return clientRepository.findById(id).orElse(null);
    }

    public Client saveClient(Client client) {
        Client savedClient =  clientRepository.save(client);
        restClientBuilder.baseUrl(carServiceUrl).build().post()
                .uri("/internal/clients")
                .contentType(APPLICATION_JSON)
                .body(savedClient)
                .retrieve()
                .toBodilessEntity();
        return savedClient;
    }

    public boolean deleteClientById(UUID id) {
        if (clientRepository.existsById(id)) {
            clientRepository.deleteById(id);
            restClientBuilder.baseUrl(carServiceUrl).build().delete()
                    .uri("/internal/clients/{id}", id)
                    .retrieve()
                    .toBodilessEntity();
            return true;
        }
        return false;
    }

    public Client updateClient(Client newClient) {
        Client client = clientRepository.findById(newClient.getId()).orElse(null);
        if (client != null) {
            client.setName(newClient.getName());
            client.setAge(newClient.getAge());
            Client updatedClient =  clientRepository.save(client);
            restClientBuilder.baseUrl(carServiceUrl).build().put()
                    .uri("/internal/clients/{id}", newClient.getId())
                    .contentType(APPLICATION_JSON)
                    .body(client)
                    .retrieve()
                    .toBodilessEntity();
            return updatedClient;
        }
        return null;
    }
}
