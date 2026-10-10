package org.autoservice.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(includeFieldNames = false)
public class CarDto implements Comparable<CarDto> {
    private String model;
    private String type;
    private int productionYear;

    private String clientName;

    @Override
    public int compareTo(CarDto o) {
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
