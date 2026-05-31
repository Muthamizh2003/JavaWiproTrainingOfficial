package com.wipro.ecom.services;

import java.util.List;

import com.wipro.ecom.dto.OrderDTO;
import com.wipro.ecom.dto.OrderItemDTO;

public interface OrderService {


	OrderDTO placeOrder(Long cartId);

	List<OrderItemDTO> getItemsByOrder(Long orderId);

	List<OrderDTO> getOrdersByUser(Long userId);

}
