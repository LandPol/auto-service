package org.carservice.model;

import jakarta.persistence.*;
import lombok.*;
import org.carservice.dto.CarDto;

import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
@Builder
@ToString(includeFieldNames = false)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "cars")
public class Car implements Comparable<Car>, Serializable {
    @Id
    private UUID id;

    private String model;

    private String type;

    @Column(name = "production_year")
    private int productionYear;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id")
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

        return Integer.compare(productionYear, o.getProductionYear());
    }

    public CarDto toDto() {
        return CarDto.builder()
                .model(this.model)
                .type(this.type)
                .productionYear(this.productionYear)
                .clientName(this.client.getName())
                .build();
    }
}
