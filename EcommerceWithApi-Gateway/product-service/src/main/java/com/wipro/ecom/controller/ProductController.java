package com.wipro.ecom.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wipro.ecom.dto.ProductDTO;
import com.wipro.ecom.service.ProductService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService service;


	@PostMapping
	public ProductDTO save(@Valid @RequestBody ProductDTO dto) {
	    return service.save(dto);
	}

    @GetMapping
    public List<ProductDTO> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public ProductDTO getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

    @GetMapping("/search")
    public List<ProductDTO> getByName(@RequestParam String name) {
        return service.getByName(name);
    }
    @GetMapping("/priceGreater")
    public List<ProductDTO> getByPriceGreater(@RequestParam double price) {
        return service.getByPriceGreater(price);
    }

    @GetMapping("/priceLess")
    public List<ProductDTO> getByPriceLess(@RequestParam double price) {
        return service.getByPriceLess(price);
    }

    @GetMapping("/priceRange")
    public List<ProductDTO> getByRange(@RequestParam double min,
                                   @RequestParam double max) {
        return service.getByPriceRange(min, max);
    }

    @GetMapping("/contains")
    public List<ProductDTO> search(@RequestParam String keyword) {
        return service.searchByName(keyword);
    }

    @GetMapping("/top3")
    public List<ProductDTO> getTop3() {
        return service.getTop3Expensive();
    }
    @GetMapping("/exists")
    public boolean exists(@RequestParam String name) {
        return service.checkProductExists(name);
    }

}