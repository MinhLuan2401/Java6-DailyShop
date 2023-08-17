package com.poly.services;

import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;
import com.poly.entity.Order;

public interface OrderService {

	public Order create(JsonNode orderData);

	public Order findById(Long id);

	public List<Order> findByUsername(String username);
	
	List<Order> findAll();
	
	List<Order> findAllDESC();

	public void delete(Long id);

	public Order update(Order order);



}
