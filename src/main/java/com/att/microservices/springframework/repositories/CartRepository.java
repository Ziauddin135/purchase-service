package com.att.microservices.springframework.repositories;

import org.springframework.data.repository.CrudRepository;

import com.att.microservices.springframework.domain.Cart;

public interface CartRepository extends CrudRepository<Cart, String> {
	public Cart findCartByCustId(String custId);
}
