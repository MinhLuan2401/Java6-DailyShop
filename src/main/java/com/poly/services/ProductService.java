package com.poly.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.poly.entity.Product;

public interface ProductService {
	
	List<Product> findAll();
	
	Page<Product> findAllByNameLike(String keywords, Pageable pageable);

	Product findById(Integer id);

	
	List<Product> findByPrice();
	List<Product> findBySell();
	List<Product> findByAVB();
	List<Product> findBySellPro();
	
	public Product create(Product product);

	public Product update(Product product);

	public void delete(Integer id);
	
	Page<Product> findByCategoryId(String cid, Pageable pageable);
	Page<Product> HsortByField(Pageable pageable);
	Page<Product> DsortByField(String field, Pageable pageable);

}
