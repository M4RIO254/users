package com.ntt.usersOrchestrator.infrastructure.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserFullRequestDTO {

    private String name;
    private String email;
    private String adress;
    private Integer age;
}