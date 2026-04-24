package com.ntt.usersOrchestrator.infrastructure.dtos;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDetailsRequestDTO {


	private Long userId;
    private Integer age;
    private String adress;
}