package com.wipro.ecom.entity;

import java.time.LocalDateTime;
import jakarta.persistence.*;

@Entity
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double amount;
    private String paymentMethod;
    private String status;

    private LocalDateTime paymentDate;

    @OneToOne
    @JoinColumn(name = "order_id")
    private Order order;

    
	public Payment() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Payment(Long id, double amount, String paymentMethod, String status, LocalDateTime paymentDate,
			Order order) {
		super();
		this.id = id;
		this.amount = amount;
		this.paymentMethod = paymentMethod;
		this.status = status;
		this.paymentDate = paymentDate;
		this.order = order;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public LocalDateTime getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(LocalDateTime paymentDate) {
		this.paymentDate = paymentDate;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	@Override
	public String toString() {
		return "Payment [id=" + id + ", amount=" + amount + ", paymentMethod=" + paymentMethod + ", status=" + status
				+ ", paymentDate=" + paymentDate + ", order=" + order + "]";
	}

	
    
}