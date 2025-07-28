//package com.sunbeam.controllers;
//
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.sunbeam.entities.Product;
//import com.sunbeam.services.ProductService;
//
//@RestController
//@RequestMapping("/api/products")
//public class ProductController {
//    @Autowired private ProductService service;
//
//    @PostMapping public Product add(@RequestBody Product p) { return service.createProduct(p); }
//    @GetMapping public List<Product> all() { return service.getAllProducts(); }
//    @GetMapping("/{id}") public Product one(@PathVariable int id) { return service.getProductById(id); }
//    @PutMapping("/{id}") public Product update(@PathVariable int id, @RequestBody Product p) { return service.updateProduct(id, p); }
//    @DeleteMapping("/{id}") public void delete(@PathVariable int id) { service.deleteProduct(id); }
//}
