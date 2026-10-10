package org.autoservice.model;

import lombok.Getter;
import lombok.Setter;
import lombok.Builder;
import lombok.ToString;
import lombok.EqualsAndHashCode;

@Getter
@Setter
@Builder
public class Car implements Comparable<Car> {
    private String model;
    private String type;
    private int rocznik;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Client client;

    @Override
    public int compareTo(Car o) {
        if (o == null) {
            throw new NullPointerException();
        }

        return model.compareTo(o.model);
    }
}
