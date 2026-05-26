package com.wipro.ecom.serviceimpl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wipro.ecom.entity.Product;
import com.wipro.ecom.repository.ProductRepository;
import com.wipro.ecom.services.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository repo;

    public Product save(Product product) {
        return repo.save(product);
    }

    public List<Product> getAll() {
        return repo.findAll();
    }

    public Product getById(Long id) {
        return repo.findById(id).orElse(null);
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }

    public List<Product> getByName(String name) {
        return repo.findByName(name);
    }

    public List<Product> getByPriceGreater(double price) {
        return repo.findByPriceGreaterThan(price);
    }

    public List<Product> getByPriceLess(double price) {
        return repo.findByPriceLessThan(price);
    }

    public List<Product> getByPriceRange(double min, double max) {
        return repo.findByPriceBetween(min, max);
    }

    public List<Product> searchByName(String keyword) {
        return repo.findByNameContaining(keyword);
    }

    public List<Product> getTop3Expensive() {
        return repo.findTop3ByOrderByPriceDesc();
    }

    public boolean checkProductExists(String name) {
        return repo.existsByName(name);
    }

}