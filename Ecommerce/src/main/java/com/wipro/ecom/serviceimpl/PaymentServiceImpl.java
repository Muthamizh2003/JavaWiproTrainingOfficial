package com.wipro.ecom.serviceimpl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wipro.ecom.entity.CartItem;
import com.wipro.ecom.entity.Order;
import com.wipro.ecom.entity.Payment;
import com.wipro.ecom.repository.OrderRepository;
import com.wipro.ecom.repository.PaymentRepository;
import com.wipro.ecom.services.PaymentService;
@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentRepository paymentRepo;

    @Autowired
    private OrderRepository orderRepo;

    public Payment makePayment(Long orderId, Payment payment) {

        Order order = orderRepo.findById(orderId).orElseThrow();

        payment.setOrder(order);
        payment.setPaymentDate(LocalDateTime.now());

        order.setPayment(payment);

        return paymentRepo.save(payment);
    }
    

	public Payment getPaymentByOrder(Long orderId) {
	    return paymentRepo.findByOrderId(orderId);
	}


	

}