package org.autoservice.dto;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(includeFieldNames = false)
public class CarDto implements Comparable<CarDto> {
    @NotBlank(message = "Model cannot be blank")
    private String model;

    @NotBlank(message = "Type cannot be blank")
    private String type;

    @NotNull(message = "Production year cannot be null")
    @Min(value = 1900, message = "Production year cannot be earlier than 1900")
    private int productionYear;

    @AssertTrue(message = "Production year cannot be greater than the current +1")
    private boolean isProductionYearValid() {
        int maxYear = LocalDate.now().getYear() + 1;
        return productionYear <= maxYear;
    }

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
