package org.autoservice.dto;
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
    private String name;
}
