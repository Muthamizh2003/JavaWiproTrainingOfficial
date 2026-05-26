package com.wipro.ecom.serviceimpl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wipro.ecom.entity.Cart;
import com.wipro.ecom.entity.CartItem;
import com.wipro.ecom.entity.Order;
import com.wipro.ecom.entity.OrderItem;
import com.wipro.ecom.entity.Product;
import com.wipro.ecom.repository.CartRepository;
import com.wipro.ecom.repository.OrderItemRepository;
import com.wipro.ecom.repository.OrderRepository;
import com.wipro.ecom.services.OrderService;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepo;

    @Autowired
    private CartRepository cartRepo;

    public Order placeOrder(Long cartId) {

        Cart cart = cartRepo.findById(cartId).orElseThrow();

        if (cart.getItems() == null || cart.getItems().isEmpty()) {
            throw new RuntimeException("Cart is empty");
        }

        Order order = new Order();
        order.setUser(cart.getUser());
        order.setOrderDate(LocalDateTime.now());
        order.setItems(new ArrayList<>());

        double totalPrice = 0;

        for (CartItem ci : cart.getItems()) {

            Product product = ci.getProduct();

            if (product.getStock() < ci.getQuantity()) {
                throw new RuntimeException("Insufficient stock for " + product.getName());
            }

            product.setStock(product.getStock() - ci.getQuantity());

            OrderItem oi = new OrderItem();
            oi.setOrder(order);
            oi.setProduct(product);
            oi.setQuantity(ci.getQuantity());

            totalPrice += product.getPrice() * ci.getQuantity();

            order.getItems().add(oi);
        }

        order.setTotalAmount(totalPrice);

        cart.getItems().clear();

        return orderRepo.save(order);
    }

	public List<Order> getOrdersByUser(Long userId) {
	    return orderRepo.findByUserId(userId);
	}
	

	@Autowired
	private OrderItemRepository orderItemRepo;

	public List<OrderItem> getItemsByOrder(Long orderId) {
	    return orderItemRepo.findByOrderId(orderId);
	}
}
