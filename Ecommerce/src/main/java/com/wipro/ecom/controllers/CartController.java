package com.wipro.ecom.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.wipro.ecom.entity.Cart;
import com.wipro.ecom.entity.CartItem;
import com.wipro.ecom.services.CartService;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService service;

    @GetMapping("/{id}")
    public Cart getCart(@PathVariable Long id) {
        return service.getCart(id);
    }

    @PostMapping("/add")
    public Cart addToCart(@RequestParam Long cartId,
                          @RequestParam Long productId,
                          @RequestParam int quantity) {
        return service.addToCart(cartId, productId, quantity);
    }

    @DeleteMapping("/clear/{id}")
    public Cart clearCart(@PathVariable Long id) {
        return service.clearCart(id);
    }
    
    @GetMapping("/items/{cartId}")
    public List<CartItem> getCartItems(@PathVariable Long cartId) {
        return service.getCartItems(cartId);
    }

}