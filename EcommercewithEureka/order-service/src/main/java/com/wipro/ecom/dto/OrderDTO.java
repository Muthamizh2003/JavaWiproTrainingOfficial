package com.wipro.ecom.dto;

import java.time.LocalDateTime;
import java.util.List;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO {

    private Long id;
    private Long userId;
    private LocalDateTime orderDate;
    private double totalAmount;

    private List<OrderItemDTO> items;
}