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
	private String description;

	@ManyToOne(cascade = CascadeType.ALL)
	private Carts cart;
	
	public Items() {
		
	}
	
	public Items(double price, String description) {
		super();
		this.price = price;
		this.description = description;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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
