package org.autoservice.dto;
import lombok.*;

@Getter
@Setter
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CarCreateUpdateDto {
    private String model;
    private String type;
    private int productionYear;
}
