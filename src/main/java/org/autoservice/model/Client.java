package org.autoservice.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Builder
@ToString(includeFieldNames = false)
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "clients")
public class Client implements Comparable<Client>, Serializable {
    @Id
    private UUID id;

    private String name;

    private int age;

    @Builder.Default
    @ToString.Exclude
    @OneToMany(mappedBy = "client", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
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
