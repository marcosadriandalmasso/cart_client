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

	// ATTRIBUTES
	
	@Id
	@GeneratedValue
	private int clientId;
	
	@Column
	private String name;
	
	@Column
	private String username;
	
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

	// CONSTRUCTORS
	
	public Clients() {
		
	}
	
	public Clients(String name, String username, String password,
			String shippingAddress, String telephone, String email,
			boolean mailist) {
		this.name = name;
		this.username = username;
		this.password = password;
		this.shippingAddress = shippingAddress;
		this.telephone = telephone;
		this.email = email;
		this.mailist = mailist;
	}

	// GETTERS
	
	public int getClientId() {
		return clientId;
	}
	
	public String getName() {
		return name;
	}

//	public void setName(String name) {
//		this.name = name;
//	}

	public String getUsername() {
		return username;
	}

//	public void setUserName(String userName) {
//		this.userName = userName;
//	}

	public String getPassword() {
		return password;
	}

//	public void setPassword(String password) {
//		this.password = password;
//	}

	public String getShippingAddress() {
		return shippingAddress;
	}

//	public void setShippingAddress(String shippingAddress) {
//		this.shippingAddress = shippingAddress;
//	}

	public String getTelephone() {
		return telephone;
	}

//	public void setTelephone(String telephone) {
//		this.telephone = telephone;
//	}

	public String getEmail() {
		return email;
	}

//	public void setEmail(String email) {
//		this.email = email;
//	}

	public boolean isMailist() {
		return mailist;
	}

//	public void setMailist(boolean mailist) {
//		this.mailist = mailist;
//	}

	public List<Carts> getCart() {
		return cart;
	}

//	public void setCart(List<Carts> cart) {
//		this.cart = cart;
//	}
}
