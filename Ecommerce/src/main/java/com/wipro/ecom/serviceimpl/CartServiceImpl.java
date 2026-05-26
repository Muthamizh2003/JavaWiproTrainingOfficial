package com.wipro.ecom.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wipro.ecom.entity.Cart;
import com.wipro.ecom.entity.CartItem;
import com.wipro.ecom.entity.Product;
import com.wipro.ecom.repository.CartItemRepository;
import com.wipro.ecom.repository.CartRepository;
import com.wipro.ecom.repository.ProductRepository;
import com.wipro.ecom.services.CartService;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartRepository cartRepo;

    @Autowired
    private ProductRepository productRepo;

    public Cart getCart(Long cartId) {
        return cartRepo.findById(cartId).orElse(null);
    }

    public Cart addToCart(Long cartId, Long productId, int quantity) {

        Cart cart = cartRepo.findById(cartId).orElseThrow();
        Product product = productRepo.findById(productId).orElseThrow();

        if (cart.getItems() == null) {
            cart.setItems(new ArrayList<>());
        }
        for (CartItem ci : cart.getItems()) {
            if (ci.getProduct().getId().equals(productId)) {
                ci.setQuantity(ci.getQuantity() + quantity);
                return cartRepo.save(cart);
            }
        }

        CartItem item = new CartItem();
        item.setCart(cart);
        item.setProduct(product);
        item.setQuantity(quantity);

        cart.getItems().add(item);

        return cartRepo.save(cart);
    }

    public Cart clearCart(Long cartId) {
        Cart cart = cartRepo.findById(cartId).orElseThrow();
        cart.getItems().clear();
        return cartRepo.save(cart);
    }

    @Autowired
    private CartItemRepository cartItemRepo;
    
    public List<CartItem> getCartItems(Long cartId) {
    	return cartItemRepo.findByCartId(cartId);
    }

}
