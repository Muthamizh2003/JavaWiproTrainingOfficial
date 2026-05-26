package com.wipro.ecom.entity;

import jakarta.persistence.*;

@Entity
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int quantity;

    @ManyToOne
    @JoinColumn(name = "cart_id")
    private Cart cart;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
    
    
	public CartItem() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CartItem(Long id, int quantity, Cart cart, Product product) {
		super();
		this.id = id;
		this.quantity = quantity;
		this.cart = cart;
		this.product = product;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Cart getCart() {
		return cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	@Override
	public String toString() {
		return "CartItem [id=" + id + ", quantity=" + quantity + ", cart=" + cart + ", product=" + product + "]";
	}

	
    
}