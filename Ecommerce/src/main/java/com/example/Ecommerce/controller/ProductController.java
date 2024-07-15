package com.example.Ecommerce.controller;

import com.example.Ecommerce.entity.Product;
import com.example.Ecommerce.services.ProductServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductServices productServices;

    @GetMapping
    public List<Product> findAllProducts() {
         return productServices.findAllProducts();
    }

    @PostMapping
    public Product addProduct(@RequestBody Product product) {
        Product product1=productServices.addProduct(product);
        return product1;
    }
    @PutMapping
    public Product updateProduct(@RequestBody Product product) {
        return productServices.UpdateProduct(product);
    }

}
