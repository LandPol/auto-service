package org.autoservice.dto;

import lombok.*;

@Getter
@Setter
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClientCreateUpdateDto {
    private String name;
    private int age;
}
