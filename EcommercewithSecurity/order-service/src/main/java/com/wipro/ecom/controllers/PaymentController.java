package com.wipro.ecom.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wipro.ecom.dto.PaymentDTO;
import com.wipro.ecom.services.PaymentService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/payment")
public class PaymentController {

    @Autowired
    private PaymentService service;

	
	@PostMapping("/{orderId}")
	@PreAuthorize("hasRole('USER')")
	public PaymentDTO makePayment(@PathVariable Long orderId,
	                             @Valid @RequestBody PaymentDTO dto) {
	    return service.makePayment(orderId, dto);
	}


	@GetMapping("/order/{orderId}")
	@PreAuthorize("hasRole('USER','ADMIN')")
	public PaymentDTO getPaymentByOrder(@PathVariable Long orderId) {
	    return service.getPaymentByOrder(orderId);
	}
	
}