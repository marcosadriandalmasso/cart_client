package com.globant.carrito.client;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.globant.carrito.cart.Carts;

@Entity
public class Clients {

	@Id
	@GeneratedValue
	private int clientId;
	
	@Column
	private String name;
	
	@Column
	private String userName;
	
	@Column
	private String password;
	
	@Column
	private String shippingAddress;
	
	@Column
	private String telephone;
	
	@Column
	private String email;
	
	@Column
	private boolean mailist;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "client")
	private List<Carts> cart;

	public Clients() {
		
	}
	
	public Clients(String name, String userName, String password,
			String shippingAddress, String telephone, String email,
			boolean mailist) {
		super();
		this.name = name;
		this.userName = userName;
		this.password = password;
		this.shippingAddress = shippingAddress;
		this.telephone = telephone;
		this.email = email;
		this.mailist = mailist;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getShippingAddress() {
		return shippingAddress;
	}

	public void setShippingAddress(String shippingAddress) {
		this.shippingAddress = shippingAddress;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isMailist() {
		return mailist;
	}

	public void setMailist(boolean mailist) {
		this.mailist = mailist;
	}

	public List<Carts> getCart() {
		return cart;
	}

	public void setCart(List<Carts> cart) {
		this.cart = cart;
	}

	public int getClientId() {
		return clientId;
	}
	
}
