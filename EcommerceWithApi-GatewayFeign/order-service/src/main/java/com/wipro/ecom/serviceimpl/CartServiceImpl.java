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
import com.wipro.ecom.feign.ProductClient;
import com.wipro.ecom.feign.UserClient;
import com.wipro.ecom.repository.CartItemRepository;
import com.wipro.ecom.repository.CartRepository;
import com.wipro.ecom.services.CartService;

@Service
public class CartServiceImpl implements CartService {

	@Autowired
	private UserClient userClient;

	@Autowired
	private ProductClient productClient;
	
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
            	ProductDTO product = productClient.getProduct(ci.getProductId());
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

    	ProductDTO product = productClient.getProduct( ci.getProductId());

        CartItemDTO dto = new CartItemDTO();

        dto.setProductId(product.getId());
        dto.setProductName(product.getName());
        dto.setPrice(product.getPrice());
        dto.setQuantity(ci.getQuantity());

        return dto;
    }
    public CartDTO getCart(Long cartId) {
        Cart cart = cartRepo.findById(cartId).orElseThrow();
        Object user = userClient.getUser(cart.getUserId());
    	
	    if (user == null) {
	        throw new RuntimeException("User not found ❌");
	    }
        return convertToDTO(cart);
    }
    public CartDTO addToCart(Long cartId, Long productId, int quantity) {

        Cart cart = cartRepo.findById(cartId).orElseThrow();
        
        ProductDTO product = productClient.getProduct(productId);
        
		if (product == null) {
		    throw new RuntimeException("Product not found ❌");
		}

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


    	Object user = userClient.getUser(userId);

    	if (user == null) {
    		throw new RuntimeException("User not found ❌");
    	}

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
