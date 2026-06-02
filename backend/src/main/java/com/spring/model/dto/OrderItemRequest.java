package com.spring.model.dto;

public record OrderItemRequest(
		int productId,
		int quantity) {
	
}
