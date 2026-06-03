package com.wipro.ecom.dto;

import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {

    private Long id;

    @NotBlank
    private String name;

    @Positive
    private double price;

    @Min(0)
    private int stock;
}
