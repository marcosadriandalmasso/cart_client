package com.globant.carrito.product;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.globant.carrito.cart.Carts;

@Entity
public class Items {
	
	@Id
	@GeneratedValue
	private int itemId;
	
	@Column
	private double price;
	
	@Column
	private int qty;

	public int getQty() {
		return qty;
	}

	public void setQty(int qty) {
		this.qty = qty;
	}

	@ManyToOne(cascade = CascadeType.ALL)
	private Carts cart;
	
	@ManyToOne(cascade = CascadeType.ALL)
	private Product product;
	
	public Product getProduct() {
		return product;
	}

	public Items() {
		
	}
	
	public Items(double price, Product product, int qty) {
		this.price = price;
		this.product = product;
		this.qty = qty;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getItemId() {
		return itemId;
	}

	public Carts getCart() {
		return cart;
	}

	public void setCart(Carts cart) {
		this.cart = cart;
	}
	
}
