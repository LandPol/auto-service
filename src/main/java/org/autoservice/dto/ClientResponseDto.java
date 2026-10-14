package org.autoservice.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
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

    @NotBlank(message = "Name cannot be blank")
    private String name;

    @Positive(message = "Age cannot be negative or zero")
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
