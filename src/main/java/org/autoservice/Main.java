package org.autoservice;

import org.autoservice.dto.CarDto;
import org.autoservice.model.Car;
import org.autoservice.model.Client;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ForkJoinPool;
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
                .productionYear(2015)
                .client(client1)
                .build();

        Car car2 = Car.builder()
                .model("Passat")
                .type("Kombi")
                .productionYear(2018)
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
                .productionYear(2020)
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
                .filter(car -> car.getProductionYear() >= 2018)
                .sorted()
                .forEach(System.out::println);

        //Transform each Car to CarDto
        List<CarDto> carDtoList = allCars.stream()
                .map(Car::toDto)
                .sorted()
                .toList();

        //Print carDto list
        System.out.println("\n== Car dto list ==");
        carDtoList.forEach(System.out::println);

        String filename = "clients.ser";

        //Data serialization into the clients.ser file
        System.out.println("\n== Data serialization ==");
        try {
            FileOutputStream file = new FileOutputStream(filename);
            ObjectOutputStream out = new ObjectOutputStream(file);
            for (Client client : clients) {
                out.writeObject(client);
            }
            out.close();
            file.close();
            System.out.println("Clients have been serialized");

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        List<Client> deserializedClients = new ArrayList<>();

        //Data deserialization into deserializedClients list
        System.out.println("\n== Data deserialization ==");
        try {
            FileInputStream file = new FileInputStream(filename);
            ObjectInputStream in = new ObjectInputStream(file);

            while (true) {
                try {
                    Client client = (Client) in.readObject();
                    deserializedClients.add(client);
                } catch (EOFException e) {
                    break;
                }
            }

            in.close();
            file.close();
            System.out.println("Clients have been deserialized");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        //Print deserialized clients and their cars
        System.out.println("\n== Deserialized clients ==");
        for (Client client : deserializedClients) {
            System.out.println(client);
            for (Car car : client.getCars()) {
                System.out.println("  " + car);
            }
        }

        System.out.println("\n== Clients and their cars using threads ==");
        ForkJoinPool pool = new ForkJoinPool(2);

        try {
            pool.submit(() -> clients.parallelStream().forEach(client -> {
                StringBuilder result = new StringBuilder("[ " + Thread.currentThread().getName() + " ] " + client);
                for (Car car : client.getCars()) {
                    result.append("\n  ").append(car);
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        System.out.println(e.getMessage());
                    }
                }
                System.out.println(result);
            })).join();
        } finally {
            pool.shutdown();
        }

        pool.shutdown();
    }
}
