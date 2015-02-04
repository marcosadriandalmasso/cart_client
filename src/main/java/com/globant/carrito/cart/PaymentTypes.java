package com.globant.carrito.cart;

import javax.persistence.Entity;

@Entity
public enum PaymentTypes {

	CREDITCARD,
	PAYPAL,
	CASH,
}
