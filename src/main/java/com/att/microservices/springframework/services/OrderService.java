package com.att.microservices.springframework.services;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.att.microservices.springframework.domain.Cart;
import com.att.microservices.springframework.domain.LineItem;
import com.att.microservices.springframework.domain.Order;
import com.att.microservices.springframework.repositories.CartRepository;
import com.att.microservices.springframework.repositories.LineItemRepository;
import com.att.microservices.springframework.repositories.OrderRepository;

@Service
public class OrderService {

	@Autowired
	OrderRepository orderRepository;

	@Autowired
	CartService cartService;

	@Autowired
	LineItemRepository lineItemRepository;

	@Autowired
	CartRepository cartRepository;

	public List<Order> getOrdersByCustId(String custId) {
		List<Order> orders = orderRepository.findOrderByCustId(custId);
		System.out.println("Orders saveOrder>>>>"+orders.toString());
		return orders;
	}

	public List<Order> saveOrder(String custId) {

		Cart cart = cartService.getCartByCustId(custId);
		List<LineItem> lineItems = lineItemRepository.findLineItemByCartId(cart.getId().toHexString());
		float totalCost = 0;
		for (LineItem item : lineItems) {
			totalCost = totalCost + item.getTotalPrice().floatValue();
		}
		lineItemRepository.delete(lineItems);
		cartService.deleteCart(cart);
		Order order = new Order();
		order.setAmount(new BigDecimal(totalCost));
		order.setCustId(custId);
		orderRepository.save(order);
		List<Order> orders = orderRepository.findOrderByCustId(custId);
		System.out.println("Orders saveOrder>>>>"+orders.toString());
		return orders;
	}

}
