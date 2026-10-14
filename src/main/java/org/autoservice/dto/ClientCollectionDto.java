package org.autoservice.dto;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClientCollectionDto {
    private UUID id;

    @NotBlank(message = "Name cannot be blank")
    private String name;
}
