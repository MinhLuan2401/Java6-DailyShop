package com.poly.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.poly.dao.ProductDAO;
import com.poly.entity.Product;
import com.poly.services.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	ProductDAO productDAO;


	@Override
	public List<Product> findAll() {
		return productDAO.findAll();
	}

	@Override
	public Product findById(Integer id) {
		return productDAO.findById(id).get();
	}


	@Override
	public List<Product> findByPrice() {
		return productDAO.findByPrice();
	}

	@Override
	public List<Product> findBySell() {
		return productDAO.findBySell();
	}

	@Override
	public List<Product> findByAVB() {
		return productDAO.findByAVB();
	}
	
	@Override
	public List<Product> findBySellPro() {
		return productDAO.findBySellPro();
	}

	@Override
	public Page<Product> findAllByNameLike(String keywords, Pageable pageable) {
		return productDAO.findAllByNameLike(keywords, pageable);
	}

	

	@Override
	public Product create(Product product) {
		return productDAO.save(product);
	}

	@Override
	public Product update(Product product) {
		return productDAO.save(product);
	}

	@Override
	public void delete(Integer id) {
		productDAO.deleteById(id);
	}

	@Override
	public Page<Product> findByCategoryId(String cid, Pageable pageable) {
		return productDAO.findByCategoryId(cid, pageable);
	}

	@Override
	public Page<Product> HsortByField(Pageable pageable) {
		return productDAO.HsortByField(pageable);
	}

	@Override
	public Page<Product> DsortByField(String field,Pageable pageable) {
		return productDAO.DsortByField(field, pageable);
	}

	
}
