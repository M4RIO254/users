package com.ntt.usersOrchestrator.infrastructure.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRequestDTO {

    @NotBlank(message = "El nombre es obligatorio")
    @Size(min = 3, max = 20)
    @JsonProperty("name")
    private String name;

    @NotBlank(message = "El email es obligatorio")
    @Email(message = "Email inválido")
    @JsonProperty("emeal")
    private String emeal;
}
