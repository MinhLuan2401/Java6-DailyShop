package com.poly.controller;


import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.poly.dao.OrderDAO;
import com.poly.entity.Order;
import com.poly.services.OrderService;
import com.poly.utils.ParamService;

@Controller
public class OrderController {
	
	@Autowired
	OrderService orderService;
	
	@Autowired
	OrderDAO orderDAO;	

	@Autowired
	ParamService paramService;
	
	@RequestMapping("/order/checkout")
	public String checkout() {		
		return "user/cart/checkout";
	}
	
	@RequestMapping("/order/list")
	public String list(Model model, HttpServletRequest request) {
		String username = request.getRemoteUser();
		 model.addAttribute("orders", orderService.findByUsername(username));
		return "user/cart/myorder";
	}
	
	@RequestMapping("/order/detail/{id}")
	public String detail(@PathVariable("id") Long id, Model model) {
		 model.addAttribute("order" , orderService.findById(id));
		 return "user/cart/orderdetail";
	}
	
	@RequestMapping("/order/listAll")
	public String list(Model model) {
		 model.addAttribute("orders", orderService.findAllDESC());
		return "user/cart/ADorder";
	}
	
	@PostMapping("/order/listAll/{id}")
	public String update(@PathVariable Long id, @ModelAttribute("order") Order order, @RequestParam String action) {
	    Order existingOrder = orderService.findById(id);
	    existingOrder.setAction(action);
	    orderService.update(existingOrder);
	    return "redirect:/order/listAll";
	}

}
