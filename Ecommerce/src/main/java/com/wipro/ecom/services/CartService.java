package com.wipro.ecom.services;

import java.util.List;

import com.wipro.ecom.entity.Cart;
import com.wipro.ecom.entity.CartItem;

public interface CartService {

    Cart getCart(Long cartId);

    Cart addToCart(Long cartId, Long productId, int quantity);

    Cart clearCart(Long cartId);

    List<CartItem> getCartItems(Long cartId);

	
}
