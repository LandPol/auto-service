package org.carservice.component;

import lombok.RequiredArgsConstructor;
import org.carservice.model.Car;
import org.carservice.model.Client;
import org.carservice.repository.CarRepository;
import org.carservice.repository.ClientRepository;
import org.carservice.service.ClientService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class CarDataInitializer implements CommandLineRunner {
    private final CarRepository carRepository;
    private final ClientRepository clientRepository;
    private final ClientService clientService;

    public static final UUID KAROL_ID = UUID.fromString("11111111-1111-1111-1111-111111111111");
    public static final UUID KUBA_ID = UUID.fromString("22222222-2222-2222-2222-222222222222");
    public static final UUID KAROLINA_ID = UUID.fromString("33333333-3333-3333-3333-333333333333");
    public static final UUID JADWIGA_ID = UUID.fromString("44444444-4444-4444-4444-444444444444");

    public static final UUID KAROLINA_CAR_ID = UUID.fromString("55555555-5555-5555-5555-555555555555");

    @Override
    public void run(String... args) throws Exception {
        if (carRepository.count() == 0) {
            Client client1 = Client.builder()
                    .id(KAROL_ID)
                    .name("Karol")
                    .age(52)
                    .build();
            clientRepository.save(client1);

            Client client2 = Client.builder()
                    .id(KUBA_ID)
                    .name("Kuba")
                    .age(43)
                    .build();
            clientRepository.save(client2);

            Client client3 = Client.builder()
                    .id(KAROLINA_ID)
                    .name("Karolina")
                    .age(34)
                    .build();
            clientRepository.save(client3);

            Client client4 = Client.builder()
                    .id(JADWIGA_ID)
                    .name("Jadwiga")
                    .age(26)
                    .build();
            clientRepository.save(client4);


            Car car1 = Car.builder()
                    .id(UUID.randomUUID())
                    .model("Toyota Yaris")
                    .type("Hatchback")
                    .productionYear(2024)
                    .client(clientService.findClientById(KAROLINA_ID))
                    .build();
            carRepository.save(car1);

            Car car2 = Car.builder()
                    .id(KAROLINA_CAR_ID)
                    .model("Ferrari Italia 458")
                    .type("Coupe")
                    .productionYear(2006)
                    .client(clientService.findClientById(KAROLINA_ID))
                    .build();
            carRepository.save(car2);

            Car car3 = Car.builder()
                    .id(UUID.randomUUID())
                    .model("Lamborghini Urus")
                    .type("SUV")
                    .productionYear(2022)
                    .client(clientService.findClientById(KAROL_ID))
                    .build();
            carRepository.save(car3);

            Car car4 = Car.builder()
                    .id(UUID.randomUUID())
                    .model("Mercedes-Benz GLS")
                    .type("SUV")
                    .productionYear(2018)
                    .client(clientService.findClientById(JADWIGA_ID))
                    .build();
            carRepository.save(car4);

            System.out.println("Added sample cars");
        }
    }
}
