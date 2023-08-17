package com.poly.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.poly.dao.ProductDAO;
import com.poly.entity.Product;
import com.poly.services.ProductService;
import com.poly.utils.SessionService;

@Controller
public class ProductController {

	@Autowired
	ProductDAO dao;

	@Autowired
	ProductService productService;

	@Autowired
	SessionService session;

	@RequestMapping("/product/list")
	public String list(Model model,
					@RequestParam("cid") Optional<String> cid,
					@RequestParam("keywords") Optional<String> kw,
					@RequestParam("p") Optional<Integer> p) {
		
		List<Product> list4 = productService.findBySellPro();
		model.addAttribute("list4", list4);	
		if (cid.isPresent()) {
			String cidd = cid.orElse("");
			Pageable pageable = PageRequest.of(p.orElse(0), 9);
			Page<Product> page =dao.findByCategoryId(cidd, pageable);
			model.addAttribute("page", page);
			model.addAttribute("sc","?cid="+cidd+"&");
		} else {
			String keywords = kw.orElse("");
			Pageable pageable = PageRequest.of(p.orElse(0),9);
			Page<Product> page = dao.findAllByNameLike(keywords+"%", pageable);
			model.addAttribute("sc","?keywords="+keywords+"&");
			model.addAttribute("page", page);
		}

		return "user/product/product";
	}
	
	@RequestMapping("/product/list/sort/{field}")
	public String list2(Model model,
				@RequestParam("p") Optional<Integer> p,
				@PathVariable("field") String field,
				@RequestParam("price_gia") Optional<String> price_gia,
				@RequestParam("min") Optional<Double> min,
				@RequestParam("max") Optional<Double> max) {
		
		if (field.equals("Name")) {
			Pageable pageable = PageRequest.of(p.orElse(0), 9);
			Page<Product> page = dao.HsortByField(pageable);
			model.addAttribute("sc","sort/"+field+"?");
			model.addAttribute("page", page);
			return "user/product/product";
		}
		
		if (field.equals("Price")) {
			
			String price = price_gia.orElse("");
			if (!price.equalsIgnoreCase("null")) {
				if (price.equals("reduce")) {
					Pageable pageable = PageRequest.of(p.orElse(0), 9);
					Page<Product> page = dao.PDsortByField(pageable);
					model.addAttribute("sc","sort/Price?price_gia="+price+"&");
					model.addAttribute("page", page);
					return "user/product/product";
				}
				if(price.equals("increase")){
					Pageable pageable = PageRequest.of(p.orElse(0), 9);
					Page<Product> page = dao.PsortByField(pageable);
					model.addAttribute("sc","sort/Price?price_gia="+price+"&");
					model.addAttribute("page", page);
					return "user/product/product";
				}
			}
			return "user/product/product";

		}
		
		if(field.equals("CreateDate")) {
			Pageable pageable = PageRequest.of(p.orElse(0), 9);
			Page<Product> page = dao.CsortByField(pageable);
			model.addAttribute("sc","sort/"+field+"?");
			model.addAttribute("page", page);
			return "user/product/product";
		}
		
		
		return "user/product/product";

	}


	@RequestMapping("/product/detail/{id}")
	public String detail(Model model, @PathVariable("id") Integer id) {
		Product item = productService.findById(id);
		model.addAttribute("item", item);
		return "user/product/product-detail";
	}
	

}
