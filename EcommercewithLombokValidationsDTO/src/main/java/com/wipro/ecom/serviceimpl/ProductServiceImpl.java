package com.wipro.ecom.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wipro.ecom.dto.ProductDTO;
import com.wipro.ecom.entity.Product;
import com.wipro.ecom.repository.ProductRepository;
import com.wipro.ecom.services.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository repo;
    
    private ProductDTO convertToDTO(Product p) {
        ProductDTO dto = new ProductDTO();
        dto.setId(p.getId());
        dto.setName(p.getName());
        dto.setPrice(p.getPrice());
        dto.setStock(p.getStock());
        return dto;
    }
    private Product convertToEntity(ProductDTO dto) {
        Product p = new Product();
        p.setName(dto.getName());
        p.setPrice(dto.getPrice());
        p.setStock(dto.getStock());
        return p;
    }
    public ProductDTO save(ProductDTO dto) {

        Product product = convertToEntity(dto);

        Product saved = repo.save(product);

        return convertToDTO(saved);
    }

    public List<ProductDTO> getAll() {
        return repo.findAll()
                   .stream()
                   .map(this::convertToDTO)
                   .toList();
    }

    public ProductDTO getById(Long id) {
        Product p = repo.findById(id).orElseThrow();
        return convertToDTO(p);
    }
    
    public void delete(Long id) {
        repo.deleteById(id);
    }

    public List<ProductDTO> getByName(String name) {
        return repo.findByName(name).stream().map(this::convertToDTO).toList();
    }

    public List<ProductDTO> getByPriceGreater(double price) {
        return repo.findByPriceGreaterThan(price).stream().map(this::convertToDTO).toList();
    }

    public List<ProductDTO> getByPriceLess(double price) {
        return repo.findByPriceLessThan(price).stream().map(this::convertToDTO).toList();
    }

    public List<ProductDTO> getByPriceRange(double min, double max) {
        return repo.findByPriceBetween(min, max).stream().map(this::convertToDTO).toList();
    }

    public List<ProductDTO> searchByName(String keyword) {
        return repo.findByNameContaining(keyword).stream().map(this::convertToDTO).toList();
    }

    public List<ProductDTO> getTop3Expensive() {
        return repo.findTop3ByOrderByPriceDesc().stream().map(this::convertToDTO).toList();
    }

    public boolean checkProductExists(String name) {
        return repo.existsByName(name);
    }

}