package com.att.microservices.springframework.repositories;

import java.util.List;

import com.att.microservices.springframework.domain.LineItem;

public interface LineItemRepositoryCustom {
	List<LineItem> findLineItemsByCartId(String cartId);
	LineItem findLineItemsByCartId(String cartId, String productId);
}
