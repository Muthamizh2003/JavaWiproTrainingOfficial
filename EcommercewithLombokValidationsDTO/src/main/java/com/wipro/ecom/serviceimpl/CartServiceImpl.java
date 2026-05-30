package com.wipro.ecom.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wipro.ecom.dto.CartDTO;
import com.wipro.ecom.dto.CartItemDTO;
import com.wipro.ecom.entity.Cart;
import com.wipro.ecom.entity.CartItem;
import com.wipro.ecom.entity.Product;
import com.wipro.ecom.entity.User;
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
    private CartDTO convertToDTO(Cart cart) {

        CartDTO dto = new CartDTO();

        dto.setId(cart.getId());
        dto.setUserId(cart.getUser().getId());

        // ✅ FIX: handle null
        if (cart.getItems() != null) {

            List<CartItemDTO> items = cart.getItems().stream().map(ci -> {
                CartItemDTO itemDTO = new CartItemDTO();
                itemDTO.setProductId(ci.getProduct().getId());
                itemDTO.setProductName(ci.getProduct().getName());
                itemDTO.setPrice(ci.getProduct().getPrice());
                itemDTO.setQuantity(ci.getQuantity());
                return itemDTO;
            }).toList();

            dto.setItems(items);

        } else {
            dto.setItems(List.of());  // ✅ empty list
        }

        return dto;
    }
    private CartItemDTO convertItemToDTO(CartItem ci) {

        CartItemDTO dto = new CartItemDTO();

        dto.setProductId(ci.getProduct().getId());
        dto.setProductName(ci.getProduct().getName());
        dto.setPrice(ci.getProduct().getPrice());
        dto.setQuantity(ci.getQuantity());

        return dto;
    }
    public CartDTO getCart(Long cartId) {
        Cart cart = cartRepo.findById(cartId).orElseThrow();
        return convertToDTO(cart);
    }
    public CartDTO addToCart(Long cartId, Long productId, int quantity) {

        Cart cart = cartRepo.findById(cartId).orElseThrow();
        Product product = productRepo.findById(productId).orElseThrow();

        if (cart.getItems() == null) {
            cart.setItems(new ArrayList<>());
        }

        for (CartItem ci : cart.getItems()) {
            if (ci.getProduct().getId().equals(productId)) {
                ci.setQuantity(ci.getQuantity() + quantity);
                return convertToDTO(cartRepo.save(cart));
            }
        }

        CartItem item = new CartItem();
        item.setCart(cart);
        item.setProduct(product);
        item.setQuantity(quantity);

        cart.getItems().add(item);

        return convertToDTO(cartRepo.save(cart));
    }
 
    public CartDTO clearCart(Long cartId) {
        Cart cart = cartRepo.findById(cartId).orElseThrow();
        cart.getItems().clear();
        return convertToDTO(cartRepo.save(cart));
    }

    @Autowired
    private CartItemRepository cartItemRepo;
    
    public List<CartItemDTO> getCartItems(Long cartId) {
        return cartItemRepo.findByCartId(cartId)
                           .stream()
                           .map(this::convertItemToDTO)
                           .toList();
    }

    public CartDTO saveCart(Long userId) {

    	Cart existing = cartRepo.findByUser_Id(userId);

		
		if (existing != null) {
		    return convertToDTO(existing);
		}


        Cart cart = new Cart();

        User user = new User();
        user.setId(userId);

        cart.setUser(user);
        cart.setItems(new ArrayList<>());

        Cart saved = cartRepo.save(cart);

        return convertToDTO(saved);
    }


}
