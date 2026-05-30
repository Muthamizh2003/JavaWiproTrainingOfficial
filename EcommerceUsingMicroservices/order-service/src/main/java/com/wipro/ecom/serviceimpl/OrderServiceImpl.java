package com.wipro.ecom.serviceimpl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.wipro.ecom.dto.OrderDTO;
import com.wipro.ecom.dto.OrderItemDTO;
import com.wipro.ecom.dto.ProductDTO;
import com.wipro.ecom.entity.Cart;
import com.wipro.ecom.entity.CartItem;
import com.wipro.ecom.entity.Order;
import com.wipro.ecom.entity.OrderItem;
import com.wipro.ecom.repository.CartRepository;
import com.wipro.ecom.repository.OrderItemRepository;
import com.wipro.ecom.repository.OrderRepository;
import com.wipro.ecom.services.OrderService;

import jakarta.transaction.Transactional;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepo;

    @Autowired
    private CartRepository cartRepo;

    @Autowired
    private RestTemplate restTemplate;

    private OrderDTO convertToDTO(Order order) {

        OrderDTO dto = new OrderDTO();

        dto.setId(order.getId());
        dto.setUserId(order.getUserId());
        dto.setOrderDate(order.getOrderDate());
        dto.setTotalAmount(order.getTotalAmount());

        List<OrderItemDTO> items = order.getItems().stream().map(oi -> {

            // ✅ CALL PRODUCT SERVICE
            ProductDTO product = restTemplate.getForObject(
                "http://localhost:8082/products/" + oi.getProductId(),
                ProductDTO.class
            );

            OrderItemDTO itemDTO = new OrderItemDTO();

            itemDTO.setProductId(product.getId());
            itemDTO.setProductName(product.getName());
            itemDTO.setPrice(product.getPrice());
            itemDTO.setQuantity(oi.getQuantity());

            return itemDTO;

        }).toList();

        dto.setItems(items);

        return dto;
    }
	
	private OrderItemDTO convertItemToDTO(OrderItem oi) {

	    ProductDTO product = restTemplate.getForObject(
	        "http://localhost:8082/products/" + oi.getProductId(),
	        ProductDTO.class
	    );

	    OrderItemDTO dto = new OrderItemDTO();

	    dto.setProductId(product.getId());
	    dto.setProductName(product.getName());
	    dto.setPrice(product.getPrice());
	    dto.setQuantity(oi.getQuantity());

	    return dto;
	}
	
	@Transactional
	public OrderDTO placeOrder(Long cartId) {

	    Cart cart = cartRepo.findById(cartId).orElseThrow();

	    if (cart.getItems() == null || cart.getItems().isEmpty()) {
	        throw new RuntimeException("Cart is empty");
	    }

	    Order order = new Order();

	    order.setUserId(cart.getUserId()); // ✅ FIX
	    order.setOrderDate(LocalDateTime.now());
	    order.setItems(new ArrayList<>());

	    double totalPrice = 0;

	    for (CartItem ci : cart.getItems()) {

	        // ✅ CALL PRODUCT SERVICE
	        ProductDTO product = restTemplate.getForObject(
	            "http://localhost:8082/products/" + ci.getProductId(),
	            ProductDTO.class
	        );

	        if (product.getStock() < ci.getQuantity()) {
	            throw new RuntimeException("Insufficient stock for " + product.getName());
	        }

	        OrderItem oi = new OrderItem();
	        oi.setOrder(order);
	        oi.setProductId(product.getId()); // ✅ FIX
	        oi.setQuantity(ci.getQuantity());

	        totalPrice += product.getPrice() * ci.getQuantity();
	        order.getItems().add(oi);
	    }

	    order.setTotalAmount(totalPrice);

	    cart.getItems().clear();

	    return convertToDTO(orderRepo.save(order));
	}

	public List<OrderDTO> getOrdersByUser(Long userId) {
	    return orderRepo.findByUserId(userId)
	                    .stream()
	                    .map(this::convertToDTO)
	                    .toList();
	}
	

	@Autowired
	private OrderItemRepository orderItemRepo;

	public List<OrderItemDTO> getItemsByOrder(Long orderId) {
	    return orderItemRepo.findByOrderId(orderId)
	                        .stream()
	                        .map(this::convertItemToDTO)
	                        .toList();
	}
}
