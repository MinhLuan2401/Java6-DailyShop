package com.poly.controller.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.poly.dao.OrderDetailDAO;
import com.poly.entity.TopProduct;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/topproduct")
public class TopProductRessController {
	
	@Autowired
	OrderDetailDAO orderDetailDAO;

	@GetMapping()
	public List<TopProduct> getAll() {
		return orderDetailDAO.getTop10();
	}
	
}
