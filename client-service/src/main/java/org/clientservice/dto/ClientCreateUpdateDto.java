package org.clientservice.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.*;

@Getter
@Setter
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClientCreateUpdateDto {
    @NotBlank(message = "Name cannot be blank")
    private String name;

    @Positive(message = "Age cannot be negative or zero")
    private int age;
}
