package com.wipro.ecom.dto;

import lombok.*;
import jakarta.validation.constraints.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PaymentDTO {

    private Long id;

    private Long orderId;

    @Positive
    private double amount;

    @NotBlank
    private String paymentMethod;

    private String status;
}