package org.autoservice.dto;

import lombok.*;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClientResponseDto implements Comparable<ClientResponseDto> {
    private UUID id;
    private String name;
    private int age;
    private List<CarResponseDto> cars;

    @Override
    public int compareTo(ClientResponseDto o) {
        if (o == null) {
            throw new NullPointerException();
        }

        return name.compareTo(o.name);
    }
}
