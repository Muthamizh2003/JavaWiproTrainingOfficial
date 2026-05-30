package com.wipro.ecom.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wipro.ecom.dto.CartDTO;
import com.wipro.ecom.dto.CartItemDTO;
import com.wipro.ecom.services.CartService;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService service;

    @PostMapping
    public CartDTO createCart(@RequestParam Long userId) {
        return service.saveCart(userId);
    }
    
    @GetMapping("/{id}")
    public CartDTO getCart(@PathVariable Long id) {
        return service.getCart(id);
    }

    @PostMapping("/add")
    public CartDTO addToCart(@RequestParam Long cartId,
                          @RequestParam Long productId,
                          @RequestParam int quantity) {
        return service.addToCart(cartId, productId, quantity);
    }

    @DeleteMapping("/clear/{id}")
    public CartDTO clearCart(@PathVariable Long id) {
        return service.clearCart(id);
    }
    
    @GetMapping("/items/{cartId}")
    public List<CartItemDTO> getCartItems(@PathVariable Long cartId) {
        return service.getCartItems(cartId);
    }

}