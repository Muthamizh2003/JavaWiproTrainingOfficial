package com.wipro.ecom.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.wipro.ecom.entity.Cart;

public interface CartRepository extends JpaRepository<Cart, Long> {

	Cart findByUser_Id(Long userId);
	
}