package com.wipro.ecom.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wipro.ecom.dto.OrderDTO;
import com.wipro.ecom.dto.OrderItemDTO;
import com.wipro.ecom.services.OrderService;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService service;

    @PostMapping("/{cartId}")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public OrderDTO placeOrder(@PathVariable Long cartId) {
        return service.placeOrder(cartId);
    }
    

	@GetMapping("/user/{userId}")
	@PreAuthorize("hasAnyRole('USER','ADMIN')")
	public List<OrderDTO> getOrdersByUser(@PathVariable Long userId) {
	    return service.getOrdersByUser(userId);
	}

	@GetMapping("/order/items/{orderId}")
	@PreAuthorize("hasAnyRole('USER','ADMIN')")
	public List<OrderItemDTO> getOrderItems(@PathVariable Long orderId) {
	    return service.getItemsByOrder(orderId);
	}

}