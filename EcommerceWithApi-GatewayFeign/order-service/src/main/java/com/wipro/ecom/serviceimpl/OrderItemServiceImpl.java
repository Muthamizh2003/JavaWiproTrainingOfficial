package com.wipro.ecom.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wipro.ecom.entity.OrderItem;
import com.wipro.ecom.repository.OrderItemRepository;

@Service
public class OrderItemServiceImpl {
	
	@Autowired
	private OrderItemRepository orderItemRepo;
	
	public List<OrderItem> getItemsByOrder(Long orderId) {
	    return orderItemRepo.findByOrderId(orderId);
	}

}
