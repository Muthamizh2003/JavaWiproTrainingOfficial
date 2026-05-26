package com.wipro.ecom.services;

import java.util.List;

import com.wipro.ecom.entity.Product;

public interface ProductService {

    Product save(Product product);

    List<Product> getAll();

    Product getById(Long id);

    void delete(Long id);

	List<Product> getByName(String name);

	List<Product> getByPriceGreater(double price);

	List<Product> getByPriceLess(double price);

	List<Product> getByPriceRange(double min, double max);

	List<Product> searchByName(String keyword);

	List<Product> getTop3Expensive();

	boolean checkProductExists(String name);
}