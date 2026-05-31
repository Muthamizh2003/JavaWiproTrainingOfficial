package com.wipro.ecom.service;

import java.util.List;

import com.wipro.ecom.dto.ProductDTO;

public interface ProductService {


	ProductDTO save(ProductDTO dto);
	
	List<ProductDTO> getAll();
	
	ProductDTO getById(Long id);
	
	void delete(Long id);
	
	List<ProductDTO> getByName(String name);
	
	List<ProductDTO> getByPriceGreater(double price);
	
	List<ProductDTO> getByPriceLess(double price);
	
	List<ProductDTO> getByPriceRange(double min, double max);
	
	List<ProductDTO> searchByName(String keyword);
	
	List<ProductDTO> getTop3Expensive();
	
	boolean checkProductExists(String name);

}