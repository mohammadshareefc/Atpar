package com.example.Ecommerce.services;


import com.example.Ecommerce.entity.Product;
import com.example.Ecommerce.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServices {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> findAllProducts() {
        return productRepository.findAll();
    }

    public Product addProduct(Product product) {
        Product product1=productRepository.save(product);
        return product1;
    }

    public Product UpdateProduct(Product product) {
        return productRepository.save(product);
    }
}
