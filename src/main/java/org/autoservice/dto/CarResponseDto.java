package org.autoservice.dto;
import lombok.*;
import org.autoservice.model.Client;

import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CarResponseDto implements Comparable<CarResponseDto> {
    private UUID id;
    private String model;
    private String type;
    private int productionYear;
    private Client client;

    @Override
    public int compareTo(CarResponseDto o) {
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
}
