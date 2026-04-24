package com.ntt.userDetails.infrastructure.dto;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDetailsRequestDTO {


	@JsonProperty("userId")
	private Long userId;
	@JsonProperty("age")
    private Integer age;
	@JsonProperty("adress")
    private String adress;
}