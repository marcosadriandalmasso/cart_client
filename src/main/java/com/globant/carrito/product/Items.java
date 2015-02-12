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
	
	// ATTRIBUTES
	@Id
	@GeneratedValue
	private int itemId;
	
	@Column
	private double price;
	
	@Column
	private int qty;

	@ManyToOne(cascade = CascadeType.ALL)
	private Carts cart;
	
	@ManyToOne(cascade = CascadeType.ALL)
	private Product product;

	// CONSTRUCTORS
	public Items() {
		
	}
	
	public Items(double price, Product product, int qty) {
		this.price = price;
		this.product = product;
		this.qty = qty;
	}

	// GETTERS
	public int getItemId() {
		return itemId;
	}
	
	public double getPrice() {
		return price;
	}

	public int getQty() {
		return qty;
	}

	public Carts getCart() {
		return cart;
	}

	public Product getProduct() {
		return product;
	}

	// SETTERS
	/**
	 * Setter for updating quantity when adding/removing Items to cart. Called from ItemsService
	 * @param qty
	 * 
	 */
	public void setQty(int qty) {
		this.qty = qty;
	}

	/**
	 * Setter for assigning a new cart. Called from Carts 
	 * @param cart
	 */
	public void setCart(Carts cart) {
		this.cart = cart;
	}
}