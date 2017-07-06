package com.att.microservices.springframework.services;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.att.microservices.springframework.converters.LineItemFormToLineItem;
import com.att.microservices.springframework.domain.Cart;
import com.att.microservices.springframework.domain.LineItem;
import com.att.microservices.springframework.repositories.CartRepository;
import com.att.microservices.springframework.repositories.LineItemRepository;

@Service
public class LineItemService {

	@Autowired
	LineItemRepository lineItemRepository;

	@Autowired
	CartRepository cartRepository;

	@Autowired
	CartService cartService;

	@Autowired
	LineItemFormToLineItem lineItemFormToLineItem;

	public List<LineItem> getLineItemsByCartId(String cartId) {
		List<LineItem> lineItems = lineItemRepository.findLineItemByCartId(cartId);
		return lineItems;
	}

	public int getLineItemsCount(String custId) {
		Cart cart = cartService.getCartByCustId(custId);
		if (cart == null) {
			cart = cartService.save(custId);
		}
		List<LineItem> lineItems = lineItemRepository.findLineItemByCartId(cart.getId().toHexString());
		return (lineItems != null ? lineItems.size() : 0);
	}

	public void saveLineItem(String productId, String custId, int price) {
		Cart cart = cartService.getCartByCustId(custId);
		if (cart == null) {
			cart = cartService.save(custId);
		}
		String cartId = cart.getId().toHexString();

		LineItem lineItem = lineItemRepository.findLineItemByCartIdAndProductId(cartId, productId);
		if (lineItem == null) {
			lineItem = new LineItem();
			lineItem.setCartId(cartId);
			lineItem.setProductId(productId);
		}
		BigDecimal totalPrice = lineItem.getTotalPrice();
		lineItem.setQuantity((lineItem.getQuantity()) + 1);
		lineItem.setTotalPrice(totalPrice.add(new BigDecimal(price)));
		lineItemRepository.save(lineItem);
	}

	/*
	 * public void save(String lineItemId) {
	 * lineItemRepository.delete(lineItemId); }
	 */

	public void deleteLineItem(String custId, String lineItemId) {
		Cart cart = cartRepository.findCartByCustId(custId);
		//LineItem item = lineItemRepository.findLineItemByCartIdAndProductId(cart.getId().toHexString(), productId);
		LineItem item = lineItemRepository.findOne(lineItemId);
		int price = (item.getTotalPrice()).intValue();
		int quantity = item.getQuantity();
		if (quantity == 1) {
			lineItemRepository.delete(lineItemId);
		} else if (quantity > 1) {
			price = price - (price / quantity);
			item.setTotalPrice(new BigDecimal(price));
			item.setQuantity(quantity - 1);
			lineItemRepository.save(item);
		}
	}

	public void deleteCart(String cartId) {
		lineItemRepository.delete(getLineItemsByCartId(cartId));
	}

}
