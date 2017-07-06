package com.att.microservices.springframework.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.att.microservices.springframework.commands.CustomerCartForm;
import com.att.microservices.springframework.domain.Cart;

@Component
public class CartToCartForm implements Converter<Cart, CustomerCartForm> {
	@Override
	public CustomerCartForm convert(Cart cart) {
		CustomerCartForm form = new CustomerCartForm();
		form.setId(cart.getId().toHexString());
		form.setCustId(cart.getCustId());
		form.setTotalPrice(cart.getTotalPrice());
		return form;
	}
}
