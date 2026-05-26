package com.wipro.ecom.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.wipro.ecom.entity.Order;
import com.wipro.ecom.entity.OrderItem;
import com.wipro.ecom.services.OrderService;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService service;

    @PostMapping("/{cartId}")
    public Order placeOrder(@PathVariable Long cartId) {
        return service.placeOrder(cartId);
    }
    

	@GetMapping("/user/{userId}")
	public List<Order> getOrdersByUser(@PathVariable Long userId) {
	    return service.getOrdersByUser(userId);
	}

	@GetMapping("/order/items/{orderId}")
	public List<OrderItem> getOrderItems(@PathVariable Long orderId) {
	    return service.getItemsByOrder(orderId);
	}

}