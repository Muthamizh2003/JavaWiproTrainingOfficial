package com.wipro.ecom.cbservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wipro.ecom.dto.ProductDTO;
import com.wipro.ecom.feign.ProductClient;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@Service
public class ProductCBService {

    @Autowired
    private ProductClient productClient;

    @CircuitBreaker(name = "productService", fallbackMethod = "productFallbackMethod")
    public ProductDTO getProduct(Long productId) {
        System.out.println("Calling Product Service...");
        return productClient.getProduct(productId);
    }

    public ProductDTO productFallbackMethod(Long productId, Throwable ex) {
        System.out.println("✅ Product fallback triggered");

        ProductDTO product = new ProductDTO();
        product.setId(productId);
        product.setName("Fallback Product");
        product.setPrice(0);
        product.setStock(0);

        return product;
    }
}