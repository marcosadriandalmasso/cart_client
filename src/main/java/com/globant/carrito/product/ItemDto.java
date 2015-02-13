package com.globant.carrito.product;

public class ItemDto {

	private int quantity;
	private String productName;
	private double amount;
	private int productId;
	public ItemDto (int quantity, String productName, double amount, int productId) {
		this.quantity = quantity;
		this.productName = productName;
		this.amount = amount;
		this.productId = productId;
	}
	public int getQuantity() {
		return quantity;
	}
	public String getProductName() {
		return productName;
	}
	public double getAmount() {
		return amount;
	}
	public int getProductId() {
		return productId;
	}
}
