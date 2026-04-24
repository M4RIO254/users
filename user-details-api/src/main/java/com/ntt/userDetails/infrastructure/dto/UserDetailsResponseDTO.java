package com.ntt.userDetails.infrastructure.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDetailsResponseDTO {
	@JsonProperty("id")
    private Long id;
	@JsonProperty("userid")
    private Long userId;
	@JsonProperty("age")
    private Integer age;
	@JsonProperty("adress")
    private String adress;
	
}