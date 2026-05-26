package com.wipro.ecom.services;

import com.wipro.ecom.entity.Payment;

public interface PaymentService {

    Payment makePayment(Long orderId, Payment payment);

	Payment getPaymentByOrder(Long orderId);
}