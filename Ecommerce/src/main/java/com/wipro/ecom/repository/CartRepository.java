package com.wipro.ecom.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.wipro.ecom.entity.Cart;
import com.wipro.ecom.entity.CartItem;
import java.util.*;

public interface CartRepository extends JpaRepository<Cart, Long> {
	
}