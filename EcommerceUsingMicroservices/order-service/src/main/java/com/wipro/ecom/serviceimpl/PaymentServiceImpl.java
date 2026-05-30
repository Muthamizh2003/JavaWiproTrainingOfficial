package com.wipro.ecom.serviceimpl;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wipro.ecom.dto.PaymentDTO;
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
    
    private PaymentDTO convertToDTO(Payment payment) {
        PaymentDTO dto = new PaymentDTO();

        dto.setId(payment.getId());
        dto.setOrderId(payment.getOrder().getId());
        dto.setAmount(payment.getAmount());
        dto.setPaymentMethod(payment.getPaymentMethod());
        dto.setStatus(payment.getStatus());

        return dto;
    }
    
    private Payment convertToEntity(PaymentDTO dto, Order order) {

        Payment payment = new Payment();

        payment.setAmount(dto.getAmount());
        payment.setPaymentMethod(dto.getPaymentMethod());
        payment.setStatus("SUCCESS"); 
        payment.setOrder(order);
        payment.setPaymentDate(LocalDateTime.now());

        return payment;
    }

    public PaymentDTO makePayment(Long orderId, PaymentDTO dto) {

        Order order = orderRepo.findById(orderId).orElseThrow();

        Payment payment = convertToEntity(dto, order);

        order.setPayment(payment);

        Payment saved = paymentRepo.save(payment);

        return convertToDTO(saved);
    }
    

    public PaymentDTO getPaymentByOrder(Long orderId) {

        Payment payment = paymentRepo.findByOrderId(orderId);

        return convertToDTO(payment);
    }


	

}