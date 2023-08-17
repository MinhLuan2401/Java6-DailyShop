package com.poly.services;

import java.util.List;

import com.poly.entity.Category;

public interface CategoryService {

	List<Category> findAll();

	Category create(Category category);

	Category update(Category category);

	void delete(String id);

}
