package com.wipro.ecom.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.wipro.ecom.entity.CartItem;
import com.wipro.ecom.entity.Payment;
import com.wipro.ecom.services.PaymentService;

@RestController
@RequestMapping("/payment")
public class PaymentController {

    @Autowired
    private PaymentService service;

    @PostMapping("/{orderId}")
    public Payment makePayment(@PathVariable Long orderId,
                               @RequestBody Payment payment) {
        return service.makePayment(orderId, payment);
    }

	@GetMapping("/order/{orderId}")
	public Payment getPaymentByOrder(@PathVariable Long orderId) {
	    return service.getPaymentByOrder(orderId);
	}
	
}