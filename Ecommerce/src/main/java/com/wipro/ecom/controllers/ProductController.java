package com.wipro.ecom.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.wipro.ecom.entity.Product;
import com.wipro.ecom.services.ProductService;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService service;

    @PostMapping
    public Product save(@RequestBody Product product) {
        return service.save(product);
    }

    @GetMapping
    public List<Product> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public Product getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

    @GetMapping("/search")
    public List<Product> getByName(@RequestParam String name) {
        return service.getByName(name);
    }
    @GetMapping("/priceGreater")
    public List<Product> getByPriceGreater(@RequestParam double price) {
        return service.getByPriceGreater(price);
    }

    @GetMapping("/priceLess")
    public List<Product> getByPriceLess(@RequestParam double price) {
        return service.getByPriceLess(price);
    }

    @GetMapping("/priceRange")
    public List<Product> getByRange(@RequestParam double min,
                                   @RequestParam double max) {
        return service.getByPriceRange(min, max);
    }

    @GetMapping("/contains")
    public List<Product> search(@RequestParam String keyword) {
        return service.searchByName(keyword);
    }

    @GetMapping("/top3")
    public List<Product> getTop3() {
        return service.getTop3Expensive();
    }
    @GetMapping("/exists")
    public boolean exists(@RequestParam String name) {
        return service.checkProductExists(name);
    }

}