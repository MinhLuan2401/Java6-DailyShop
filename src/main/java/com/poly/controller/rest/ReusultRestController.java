package com.poly.controller.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.poly.dao.OrderDetailDAO;
import com.poly.entity.RevenueReport;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/result")
public class ReusultRestController {
	
	@Autowired
	OrderDetailDAO orderDetailDAO;
	
	@GetMapping()
	public List<RevenueReport> getAll() {
		return orderDetailDAO.getRevenueByCategory();
	}

}
