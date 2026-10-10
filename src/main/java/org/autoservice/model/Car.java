package org.autoservice.model;

import lombok.Getter;
import lombok.Setter;
import lombok.Builder;
import lombok.ToString;
import lombok.EqualsAndHashCode;
import org.autoservice.dto.CarDto;

@Getter
@Setter
@Builder
@ToString(includeFieldNames = false)
public class Car implements Comparable<Car> {
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

        return model.compareTo(o.model);
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
