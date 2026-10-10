package org.autoservice.model;

import lombok.Getter;
import lombok.Setter;
import lombok.Builder;
import lombok.ToString;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@ToString(includeFieldNames = false)
@EqualsAndHashCode
public class Client implements Comparable<Client>, Serializable {
    private String name;
    private int age;

    @Builder.Default
    @ToString.Exclude
    List<Car> cars = new ArrayList<>();

    public void addCar(Car car) {
        this.cars.add(car);
    }

    @Override
    public int compareTo(Client o) {
        if (o == null) {
            throw new NullPointerException();
        }

        return name.compareTo(o.name);
    }
}
