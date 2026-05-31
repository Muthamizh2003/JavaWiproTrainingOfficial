package com.wipro.ecom.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wipro.ecom.entity.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
	Payment findByOrderId(Long orderId);
}
