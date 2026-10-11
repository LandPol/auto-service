package org.autoservice.dto;
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
    private String description;
}
