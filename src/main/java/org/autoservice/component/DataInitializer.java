package org.autoservice.component;

import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.autoservice.service.CarService;
import org.autoservice.service.ClientService;
import org.springframework.stereotype.Component;
import org.autoservice.model.Client;
import org.autoservice.model.Car;
import java.util.UUID;

@Component
@AllArgsConstructor
public class DataInitializer {
    private final ClientService clientService;

    @PostConstruct
    public void initData() {
        Client client1 = Client.builder()
                .id(UUID.randomUUID())
                .name("Jan Kowalski")
                .age(35)
                .build();

        Car car1 = Car.builder()
                .id(UUID.randomUUID())
                .model("Golf")
                .type("Hatchback")
                .productionYear(2015)
                .client(client1)
                .build();

        Car car2 = Car.builder()
                .id(UUID.randomUUID())
                .model("Passat")
                .type("Kombi")
                .productionYear(2018)
                .client(client1)
                .build();

        client1.addCar(car1);
        client1.addCar(car2);
        clientService.saveClient(client1);

        Client client2 = Client.builder()
                .id(UUID.randomUUID())
                .name("Anna Nowak")
                .age(28)
                .build();

        Car car3 = Car.builder()
                .id(UUID.randomUUID())
                .model("Focus")
                .type("Sedan")
                .productionYear(2020)
                .client(client2)
                .build();

        client2.addCar(car3);
        clientService.saveClient(client2);
    }
}
