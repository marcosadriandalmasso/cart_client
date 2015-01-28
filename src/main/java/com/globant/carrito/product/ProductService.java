package com.globant.carrito.product;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductService {
	@RequestMapping(value = "/service/products", method = RequestMethod.GET)
	@ResponseBody
	public ProductResponse getProducts() {
		ProductResponse response = new ProductResponse();
		response.getResults().add(new Product(123, "Coca cola", 15.5));
		response.getResults().add(new Product(456, "Fernet", 87.30));
		return response;
	}
}
