package com.wipro.ecom.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wipro.ecom.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
	List<Order> findByUserId(Long userId);
}
