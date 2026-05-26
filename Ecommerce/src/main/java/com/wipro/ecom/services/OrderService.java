package com.wipro.ecom.services;

import java.util.List;

import com.wipro.ecom.entity.Order;
import com.wipro.ecom.entity.OrderItem;

public interface OrderService {

    Order placeOrder(Long cartId);

	List<OrderItem> getItemsByOrder(Long orderId);
	
	List<Order> getOrdersByUser(Long userId);
}
