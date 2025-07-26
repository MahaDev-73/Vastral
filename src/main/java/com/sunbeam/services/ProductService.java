package com.sunbeam.services;

import java.util.List;

import com.sunbeam.entities.Product;

public interface ProductService {
    Product createProduct(Product product);
    List<Product> getAllProducts();
    Product getProductById(int id);
    Product updateProduct(int id, Product product);
    void deleteProduct(int id);
}
