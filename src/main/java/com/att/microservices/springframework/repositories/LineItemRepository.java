package com.att.microservices.springframework.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.att.microservices.springframework.domain.LineItem;

public interface LineItemRepository extends CrudRepository<LineItem, String> {
	List<LineItem> findLineItemByCartId(String cartId);
	LineItem findLineItemByCartIdAndProductId(String cartId, String productId);
}
