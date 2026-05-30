package com.wipro.ecom.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wipro.ecom.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

	List<Product> findByName(String name);
    List<Product> findByPriceGreaterThan(double price);
    List<Product> findByPriceLessThan(double price);
    List<Product> findByPriceBetween(double min, double max);
    List<Product> findByNameContaining(String keyword);
    List<Product> findByNameOrderByPriceDesc(String name);
    List<Product> findTop3ByOrderByPriceDesc();
	boolean existsByName(String name);

}
