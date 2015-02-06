package com.globant.carrito.cart;

import java.util.HashSet;
import java.util.Set;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.globant.carrito.client.Clients;
import com.globant.carrito.product.Items;


@Entity
public class Carts {
	
	@Id
	@GeneratedValue
	private int cartId;
	
	@ManyToOne(cascade = CascadeType.ALL)
	private Clients client;
	
	@OneToMany(cascade = CascadeType.ALL)
	private Set<Items> items;
	
	@Column
	private Date date;
	
	@Column
	private double cartPrice;
	
	@Column
	@GeneratedValue
	private int transactionNumber;
	
	@Column
	@Enumerated(EnumType.STRING)
	private PaymentTypes paymentType;
	
	@Column
	private boolean status;
	
	public Carts(){
		
	}
	
	public Carts(Clients client) {
		this.client = client;
		items = new HashSet<Items>();
		status = true;
	}

	public Carts(Items... initialItems) {
		items = new HashSet<Items>();
		for (Items item : initialItems) {
			addItem(item);
		}
	}
	
	public void addItem(Items item) {
		items.add(item);
		// Esto es necesario para que cargue la "clave foranea"
		item.setCart(this);
	}

	public Set<Items> getItems() {
		return items;
	}

	public void setItems(Set<Items> items) {
		this.items = items;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public double getCartPrice() {
		return cartPrice;
	}

	public void setCartPrice(double cartPrice) {
		this.cartPrice = cartPrice;
	}

	public PaymentTypes getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(PaymentTypes paymentType) {
		this.paymentType = paymentType;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public int getCartId() {
		return cartId;
	}

	public int getTransactionNumber() {
		return transactionNumber;
	}
	
}
