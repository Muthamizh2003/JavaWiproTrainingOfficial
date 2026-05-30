package com.wipro.ecom.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.wipro.ecom.dto.CartDTO;
import com.wipro.ecom.dto.CartItemDTO;
import com.wipro.ecom.dto.ProductDTO;
import com.wipro.ecom.entity.Cart;
import com.wipro.ecom.entity.CartItem;
import com.wipro.ecom.repository.CartItemRepository;
import com.wipro.ecom.repository.CartRepository;
import com.wipro.ecom.services.CartService;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartRepository cartRepo;

    @Autowired
    private RestTemplate restTemplate;
    
    private CartDTO convertToDTO(Cart cart) {

        CartDTO dto = new CartDTO();

        dto.setId(cart.getId());
        dto.setUserId(cart.getUserId()); // ✅ FIX

        if (cart.getItems() != null) {

            List<CartItemDTO> items = cart.getItems().stream().map(ci -> {

                // ✅ CALL PRODUCT SERVICE
                ProductDTO product = restTemplate.getForObject(
                		"http://product-service/products/" + ci.getProductId(),
                    ProductDTO.class
                );

                CartItemDTO itemDTO = new CartItemDTO();

                itemDTO.setProductId(product.getId());
                itemDTO.setProductName(product.getName());
                itemDTO.setPrice(product.getPrice());
                itemDTO.setQuantity(ci.getQuantity());

                return itemDTO;

            }).toList();

            dto.setItems(items);
        } else {
            dto.setItems(List.of());
        }

        return dto;
    }
    private CartItemDTO convertItemToDTO(CartItem ci) {

        ProductDTO product = restTemplate.getForObject(
        		"http://product-service/products/"+ ci.getProductId(),
            ProductDTO.class
        );

        CartItemDTO dto = new CartItemDTO();

        dto.setProductId(product.getId());
        dto.setProductName(product.getName());
        dto.setPrice(product.getPrice());
        dto.setQuantity(ci.getQuantity());

        return dto;
    }
    public CartDTO getCart(Long cartId) {
        Cart cart = cartRepo.findById(cartId).orElseThrow();
        return convertToDTO(cart);
    }
    public CartDTO addToCart(Long cartId, Long productId, int quantity) {

        Cart cart = cartRepo.findById(cartId).orElseThrow();

        // ✅ Validate product exists via API
        ProductDTO product = restTemplate.getForObject(
        		"http://product-service/products/" + productId,
            ProductDTO.class
        );

        if (cart.getItems() == null) {
            cart.setItems(new ArrayList<>());
        }

        for (CartItem ci : cart.getItems()) {
            if (ci.getProductId().equals(productId)) { // ✅ FIX
                ci.setQuantity(ci.getQuantity() + quantity);
                return convertToDTO(cartRepo.save(cart));
            }
        }

        CartItem item = new CartItem();
        item.setCart(cart);
        item.setProductId(product.getId()); // ✅ FIX
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

        Cart existing = cartRepo.findByUserId(userId); // ✅ FIX

        if (existing != null) {
            return convertToDTO(existing);
        }

        Cart cart = new Cart();
        cart.setUserId(userId); // ✅ FIX
        cart.setItems(new ArrayList<>());

        return convertToDTO(cartRepo.save(cart));
    }


}
