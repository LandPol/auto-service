package org.clientservice.component;

import lombok.RequiredArgsConstructor;
import org.clientservice.model.Client;
import org.clientservice.repository.ClientRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class ClientDataInitializer implements CommandLineRunner {
    private final ClientRepository clientRepository;

    public static final UUID KAROL_ID = UUID.fromString("11111111-1111-1111-1111-111111111111");
    public static final UUID KUBA_ID = UUID.fromString("22222222-2222-2222-2222-222222222222");
    public static final UUID KAROLINA_ID = UUID.fromString("33333333-3333-3333-3333-333333333333");
    public static final UUID JADWIGA_ID = UUID.fromString("44444444-4444-4444-4444-444444444444");

    @Override
    public void run(String... args) throws Exception {
        if (clientRepository.count() == 0) {
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

            System.out.println("Added sample clients");
        }
    }
}
