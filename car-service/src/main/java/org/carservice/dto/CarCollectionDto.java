package org.carservice.dto;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CarCollectionDto {
    private UUID id;

    @NotBlank(message = "Description cannot be blank")
    private String description;
}
