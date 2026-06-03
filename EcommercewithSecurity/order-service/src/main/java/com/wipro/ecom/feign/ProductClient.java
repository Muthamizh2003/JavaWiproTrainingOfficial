package com.wipro.ecom.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import com.wipro.ecom.config.FeignConfig;
import com.wipro.ecom.dto.ProductDTO;

@FeignClient(name = "PRODUCT-SERVICE",configuration = FeignConfig.class)
public interface ProductClient {

    @GetMapping("/products/{id}")
    ProductDTO getProduct(@PathVariable("id") Long id);
}