package org.autoservice.model;

import lombok.Getter;
import lombok.Setter;
import lombok.Builder;
import lombok.ToString;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@ToString
@EqualsAndHashCode
public class Client implements Comparable<Client> {
    private String name;
    private int age;

    @Builder.Default
    List<Car> cars = new ArrayList<>();

    @Override
    public int compareTo(Client o) {
        if (o == null) {
            throw new NullPointerException();
        }

        return name.compareTo(o.name);
    }
}
