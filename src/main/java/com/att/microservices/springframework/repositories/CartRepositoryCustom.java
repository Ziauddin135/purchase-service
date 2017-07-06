package com.att.microservices.springframework.repositories;

import com.att.microservices.springframework.domain.Cart;


public interface CartRepositoryCustom /*extends CrudRepository<Cart, String>*/ {
	
	Cart findCartByCustomer(String custId);
}
