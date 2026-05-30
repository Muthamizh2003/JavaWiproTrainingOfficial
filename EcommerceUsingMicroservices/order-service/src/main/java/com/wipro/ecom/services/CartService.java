package com.wipro.ecom.services;

import java.util.List;

import com.wipro.ecom.dto.CartDTO;
import com.wipro.ecom.dto.CartItemDTO;

public interface CartService {


	CartDTO getCart(Long cartId);
	
	CartDTO addToCart(Long cartId, Long productId, int quantity);
	
	CartDTO clearCart(Long cartId);
	
	List<CartItemDTO> getCartItems(Long cartId);
	
	CartDTO saveCart(Long userId);
	

	
}
