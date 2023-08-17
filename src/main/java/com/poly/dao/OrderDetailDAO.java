package com.poly.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.poly.entity.OrderDetail;
import com.poly.entity.RevenueReport;
import com.poly.entity.TopProduct;

public interface OrderDetailDAO extends JpaRepository<OrderDetail, Long> {
	
	@Query("SELECT new TopProduct(o.product, sum(o.quantity)) FROM OrderDetail o GROUP BY o.product ORDER BY sum(o.quantity) DESC")
	List<TopProduct> getTop10();
	
	@Query("Select new RevenueReport(d.product.category, sum(d.price*d.quantity), sum(d.quantity))"
			+ " from OrderDetail d Group By d.product.category")
	List<RevenueReport> getRevenueByCategory();
}
