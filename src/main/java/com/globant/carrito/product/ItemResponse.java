package com.globant.carrito.product;

import java.util.ArrayList;
import java.util.List;

public class ItemResponse {

	private List<ItemDto> results = new ArrayList<ItemDto>();
	public List<ItemDto> getResults() {
		return results;
	}
}
