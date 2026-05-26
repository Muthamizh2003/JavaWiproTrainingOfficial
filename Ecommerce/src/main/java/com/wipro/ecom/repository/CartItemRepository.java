package com.wipro.ecom.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wipro.ecom.entity.CartItem;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
	List<CartItem> findByCartId(Long cartId);
	
}