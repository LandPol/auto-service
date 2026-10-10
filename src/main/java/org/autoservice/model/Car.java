package org.autoservice.model;

import lombok.Getter;
import lombok.Setter;
import lombok.Builder;
import lombok.ToString;
import lombok.EqualsAndHashCode;
import org.autoservice.dto.CarDto;

import java.io.Serializable;

@Getter
@Setter
@Builder
@ToString(includeFieldNames = false)
public class Car implements Comparable<Car>, Serializable {
    private String model;
    private String type;
    private int year;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Client client;

    @Override
    public int compareTo(Car o) {
        if (o == null) {
            throw new NullPointerException();
        }

        int modelResult = model.compareTo(o.getModel());

        if (modelResult != 0) {
            return modelResult;
        }

        int typeResult = type.compareTo(o.getType());

        if (typeResult != 0) {
            return typeResult;
        }

        return year - o.year;
    }

    public CarDto toDto() {
        return CarDto.builder()
                .model(this.model)
                .type(this.type)
                .year(this.year)
                .clientName(this.client.getName())
                .build();
    }
}
