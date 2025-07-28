//package com.sunbeam.controllers;
//
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.sunbeam.entities.Category;
//import com.sunbeam.services.CategoryService;
//
//@RestController
//@RequestMapping("/api/categories")
//public class CategoryController {
//
//    @Autowired
//    private CategoryService service;
//
//    @GetMapping
//    public List<Category> getAll() {
//        return service.getAllCategories();
//    }
//
//    @PostMapping
//    public Category add(@RequestBody Category c) {
//        return service.addCategory(c);
//    }
//}
