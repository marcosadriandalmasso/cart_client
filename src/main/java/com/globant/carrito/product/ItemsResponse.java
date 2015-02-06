package com.globant.carrito.product;

import java.util.ArrayList;
import java.util.List;

public class ItemsResponse {

	private List<ItemsDto> results = new ArrayList<ItemsDto>();
	public List<ItemsDto> getResults() {
		return results;
	}
}
