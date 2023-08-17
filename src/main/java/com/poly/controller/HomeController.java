package com.poly.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.poly.entity.Product;
import com.poly.services.ProductService;

@Controller
public class HomeController {

	@Autowired
	ProductService productService;

	@RequestMapping("/home/index")
	public String checkout(Model model) {
		List<Product> list1 = productService.findByPrice();
		model.addAttribute("list1", list1);
		List<Product> list2 = productService.findBySell();
		model.addAttribute("list2", list2);
		List<Product> list3 = productService.findByAVB();
		model.addAttribute("list3", list3);
		return "user/index";
	}

	@RequestMapping("/home/404")
	public String error() {
		return "user/blog/404";
	}

	@RequestMapping("/home/contact")
	public String checkout() {
		return "user/blog/contact";
	
	}
	
	
}
