package org.autoservice.component;

import lombok.AllArgsConstructor;
import org.autoservice.model.Car;
import org.autoservice.model.Client;
import org.autoservice.service.CarService;
import org.autoservice.service.ClientService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Scanner;
import java.util.UUID;

@Component
@AllArgsConstructor
public class ConsoleRunner implements CommandLineRunner {
    private final ClientService clientService;
    private final CarService carService;

    @Override
    public void run(String... args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\n== Workshop Console ==");
            System.out.println("1. List all clients");
            System.out.println("2. List all cars");
            System.out.println("3. Find client by id");
            System.out.println("4. Find car by id");
            System.out.println("5. Add client");
            System.out.println("6. Add car");
            System.out.println("7. Delete client");
            System.out.println("8. Delete car");
            System.out.println("9. Find car by client id");
            System.out.println("10. Update client by id");
            System.out.println("11. Update car by id");
            System.out.println("0. Exit");
            System.out.println("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    clientService.findAllClients().forEach(System.out::println);
                    break;
                case 2:
                    carService.findAllCars().forEach(System.out::println);
                    break;
                case 3:
                    System.out.println("Enter client UUID: ");
                    try {
                        UUID clientUUID = UUID.fromString(scanner.nextLine());
                        System.out.println(clientService.findClientById(clientUUID));
                    } catch (IllegalArgumentException e) {
                        System.out.println("Invalid UUID");
                    }
                    break;
                case 4:
                    System.out.println("Enter car UUID: ");
                    try {
                        UUID carUUID = UUID.fromString(scanner.nextLine());
                        System.out.println(carService.findCarById(carUUID));
                    } catch (IllegalArgumentException e) {
                        System.out.println("Invalid UUID");
                    }
                    break;
                case 5:
                    System.out.println("Enter client name: ");
                    String name = scanner.nextLine();

                    System.out.println("Enter client age: ");
                    int age = scanner.nextInt();
                    scanner.nextLine();

                    Client client = Client.builder()
                            .id(UUID.randomUUID())
                            .name(name)
                            .age(age)
                            .build();
                    if (clientService.saveClient(client) != null) {
                        System.out.println(client + " added successfully");
                    } else {
                        System.out.println(client + " added failed");
                    }
                    break;
                case 6:
                    System.out.println("Enter car model: ");
                    String model = scanner.nextLine();

                    System.out.println("Enter car type: ");
                    String type = scanner.nextLine();

                    System.out.println("Enter car production yeat: ");
                    int productionYear = scanner.nextInt();
                    scanner.nextLine();

                    try {
                        System.out.println("Enter car owner UUID: ");
                        UUID clientOwnerId = UUID.fromString(scanner.nextLine());
                        Client clientOwner = clientService.findClientById(clientOwnerId);

                        if (clientOwner != null) {
                            Car car = Car.builder()
                                    .id(UUID.randomUUID())
                                    .model(model)
                                    .type(type)
                                    .productionYear(productionYear)
                                    .client(clientOwner)
                                    .build();

                            if (carService.saveCar(car) != null) {
                                System.out.println(car + " added successfully");
                            } else {
                                System.out.println(car + " added failed");
                            }
                        } else {
                            System.out.println("Car with given ID not found");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid production year format");
                    } catch (IllegalArgumentException e) {
                        System.out.println("Invalid UUID");
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 7:
                    try {
                        System.out.println("Enter client UUID: ");
                        UUID clientId = UUID.fromString(scanner.nextLine());
                        boolean deleted = clientService.deleteClientById(clientId);
                        if (deleted) {
                            System.out.println("Client deleted successfully");
                        } else {
                            System.out.println("Client not found");
                        }
                    } catch (IllegalArgumentException e) {
                        System.out.println("Invalid UUID");
                    }
                    break;
                case 8:
                    try {
                        System.out.println("Enter car UUID: ");
                        UUID carId = UUID.fromString(scanner.nextLine());
                        boolean deleted = carService.deleteCarById(carId);
                        if (deleted) {
                            System.out.println("Car deleted successfully");
                        } else {
                            System.out.println("Car not found");
                        }
                    } catch (IllegalArgumentException e) {
                        System.out.println("Invalid UUID");
                    }
                    break;
                case 9:
                    try {
                        System.out.println("Enter client UUID: ");
                        UUID clientUUID = UUID.fromString(scanner.nextLine());
                        carService.findCarsByClientId(clientUUID).forEach(System.out::println);
                    } catch (IllegalArgumentException e) {
                        System.out.println("Invalid UUID");
                    }
                    break;
                case 10:
                    try {
                        System.out.println("Enter client UUID: ");
                        UUID clientUUID = UUID.fromString(scanner.nextLine());

                        System.out.println("Enter client name: ");
                        String clientName = scanner.nextLine();

                        System.out.println("Enter client age: ");
                        int clientAge = scanner.nextInt();
                        scanner.nextLine();

                        Client newClient = Client.builder()
                                .id(clientUUID)
                                .name(clientName)
                                .age(clientAge)
                                .build();

                        if (clientService.updateClient(newClient) != null) {
                            System.out.println(newClient + " updated successfully");
                        } else {
                            System.out.println(newClient + " not found");
                        }

                    } catch (NumberFormatException e) {
                        System.out.println("Invalid age format");
                    } catch (IllegalArgumentException e) {
                        System.out.println("Invalid UUID");
                    }
                    break;
                case 11:
                    try {
                        System.out.println("Enter car UUID: ");
                        UUID carUUID = UUID.fromString(scanner.nextLine());

                        System.out.println("Enter car model: ");
                        String carModel = scanner.nextLine();

                        System.out.println("Enter car type: ");
                        String carType = scanner.nextLine();

                        System.out.println("Enter car production year: ");
                        int carProductionYear = scanner.nextInt();
                        scanner.nextLine();

                        System.out.println("Enter car owner UUID: ");
                        UUID carOwnerId = UUID.fromString(scanner.nextLine());

                        Car newCar = Car.builder()
                                .id(carUUID)
                                .model(carModel)
                                .type(carType)
                                .productionYear(carProductionYear)
                                .client(clientService.findClientById(carOwnerId))
                                .build();

                        if (carService.updateCar(newCar) != null) {
                            System.out.println(newCar + " updated successfully");
                        } else {
                            System.out.println(newCar + " not found");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid production year format");
                    } catch (IllegalArgumentException e) {
                        System.out.println("Invalid UUID");
                    }
                    break;
                case 0:
                    running = false;
                    break;
            }
        }
    }
}
