package com.att.microservices.springframework.converters;

import org.bson.types.ObjectId;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.att.microservices.springframework.commands.CustomerCartForm;
import com.att.microservices.springframework.domain.Cart;


@Component
public class CartFormToCart implements Converter<CustomerCartForm, Cart> {

	@Override
	public Cart convert(CustomerCartForm customerCartForm) {
		Cart cart = new Cart();
		if (customerCartForm.getId() != null && !StringUtils.isEmpty(customerCartForm.getId())) {
			cart.setId(new ObjectId(customerCartForm.getId()));
		}
		cart.setCustId(customerCartForm.getCustId());
		cart.setTotalPrice(customerCartForm.getTotalPrice());
		return cart;
	}
}
