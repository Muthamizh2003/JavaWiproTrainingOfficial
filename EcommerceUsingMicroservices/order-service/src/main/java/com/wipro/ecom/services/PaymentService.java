package com.wipro.ecom.services;

import com.wipro.ecom.dto.PaymentDTO;

public interface PaymentService {

	PaymentDTO makePayment(Long orderId, PaymentDTO dto);
	
	PaymentDTO getPaymentByOrder(Long orderId);

}