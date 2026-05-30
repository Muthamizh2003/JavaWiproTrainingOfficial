package com.wipro.ecom.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
public class OrderItemDTO {

    private Long productId;
    private String productName;
    private double price;
    private int quantity;
}