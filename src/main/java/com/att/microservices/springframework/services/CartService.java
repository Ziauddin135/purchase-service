package com.att.microservices.springframework.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.att.microservices.springframework.commands.CustomerCartForm;
import com.att.microservices.springframework.converters.CartFormToCart;
import com.att.microservices.springframework.domain.Cart;
import com.att.microservices.springframework.repositories.CartRepository;

@Service
public class CartService {

	@Autowired
	CartRepository cartRepository;

	@Autowired
	CartFormToCart cartFormToCart;

	public Cart getCartByCustId(String custId) {
		Cart cart = cartRepository.findCartByCustId(custId);
		return cart;
	}

	public Cart save(String custId) {
		Cart cart = new Cart();
		cart.setCustId(custId);
		return cartRepository.save(cart);
	}

	public void updateCart(CustomerCartForm form) {
		cartRepository.save(cartFormToCart.convert(form));
	}

	public void updateCart(Cart cart) {
		cartRepository.save(cart);
	}

	public void deleteCart(Cart cart) {
		cartRepository.delete(cart);
	}

	public void deleteCartById(String id) {
		cartRepository.delete(id);
	}

}
