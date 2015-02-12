package com.globant.carrito.product;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Product {
	
	// ATTRIBUTES
	
	@Id
	@GeneratedValue
	private int id;
	
	@Column
	private String name;
	
	@Column
	private double price;
	
	// GETTERS
	
	public int getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public double getPrice() {
		return price;
	}
}
