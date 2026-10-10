package org.autoservice;

import org.autoservice.model.Car;
import org.autoservice.model.Client;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        Client client1 = Client.builder()
                .name("Jan Kowalski")
                .age(35)
                .build();

        Car car1 = Car.builder()
                .model("Golf")
                .type("Hatchback")
                .year(2015)
                .client(client1)
                .build();

        Car car2 = Car.builder()
                .model("Passat")
                .type("Kombi")
                .year(2018)
                .client(client1)
                .build();

        client1.addCar(car1);
        client1.addCar(car2);

        Client client2 = Client.builder()
                .name("Anna Nowak")
                .age(28)
                .build();

        Car car3 = Car.builder()
                .model("Focus")
                .type("Sedan")
                .year(2020)
                .client(client2)
                .build();

        client2.addCar(car3);

        List<Client> clients = List.of(client1, client2);

        //Print all clients and their cars
        System.out.println("== All clients and their cars ==");
        for (Client client : clients) {
            System.out.println(client);
            for (Car car : client.getCars()) {
                System.out.println("  " + car);
            }
        }

        //Create set of all cars and print them
        System.out.println("\n== All cars ==");
        Set<Car> allCars = clients.stream()
                .flatMap(client -> client.getCars().stream())
                .collect(Collectors.toSet());

        allCars.forEach(System.out::println);

        //Filter and sort all cars
        System.out.println("\n== All cars filtered and sorted ==");
        allCars.stream()
                .filter(car -> car.getYear() >= 2018)
                .sorted()
                .forEach(System.out::println);
    }
}
