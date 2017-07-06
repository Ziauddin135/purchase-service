package com.att.microservices.springframework.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.att.microservices.springframework.domain.Order;

public interface OrderRepository extends CrudRepository<Order, String> {

	public List<Order> findOrderByCustId(String custId);

}
