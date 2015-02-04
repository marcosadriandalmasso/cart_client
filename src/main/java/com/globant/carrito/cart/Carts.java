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
import javax.persistence.OneToOne;

import com.globant.carrito.product.Items;


@Entity
public class Carts {
	
	@Id
	@GeneratedValue
	private int cartId;
	
	@ManyToOne(cascade = CascadeType.ALL)
	private int clientId;
	
	@OneToOne(cascade = CascadeType.ALL)
	private Set<Items> items;
	
	@OneToOne(cascade = CascadeType.ALL)
	private int paymentId;
	
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

	public int getClientId() {
		return clientId;
	}

	public void setClientId(int clientId) {
		this.clientId = clientId;
	}

	public Set<Items> getItems() {
		return items;
	}

	public void setItems(Set<Items> items) {
		this.items = items;
	}

	public int getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(int paymentId) {
		this.paymentId = paymentId;
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
