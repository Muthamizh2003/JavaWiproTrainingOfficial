package com.wipro.ecom.dto;

import jakarta.validation.constraints.*;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    private Long id;

    @NotBlank(message = "Name cannot be empty")
    private String name;

    @Email
    @NotBlank
    private String email;
}