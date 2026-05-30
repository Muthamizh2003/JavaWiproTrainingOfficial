package com.wipro.ecom.dto;

import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CartItemDTO {

    private Long productId;   // ✅ instead of Product object

    private String productName; // ✅ for response

    private double price; // ✅ helpful for UI

    @Min(1)
    private int quantity;
}