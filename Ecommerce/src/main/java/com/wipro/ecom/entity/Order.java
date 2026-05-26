package com.wipro.ecom.entity;

import java.time.LocalDateTime;
import java.util.List;
import jakarta.persistence.*;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime orderDate;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> items;

    @OneToOne(mappedBy = "order", cascade = CascadeType.ALL)
    private Payment payment;
    

	public Order() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Order(Long id,double totalAmount, LocalDateTime orderDate, User user, List<OrderItem> items, Payment payment) {
		super();
		this.id = id;
		this.orderDate = orderDate;
		this.user = user;
		this.items = items;
		this.payment = payment;
		this.totalAmount=totalAmount;
	}
	private double totalAmount;

	public double getTotalAmount() {
	    return totalAmount;
	}

	public void setTotalAmount(double totalAmount) {
	    this.totalAmount = totalAmount;
	}
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDateTime getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(LocalDateTime orderDate) {
		this.orderDate = orderDate;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<OrderItem> getItems() {
		return items;
	}

	public void setItems(List<OrderItem> items) {
		this.items = items;
	}

	public Payment getPayment() {
		return payment;
	}

	public void setPayment(Payment payment) {
		this.payment = payment;
	}

	@Override
	public String toString() {
		return "Order [id=" + id + ", orderDate=" + orderDate + ", user=" + user + "]";
	}

	
    
}