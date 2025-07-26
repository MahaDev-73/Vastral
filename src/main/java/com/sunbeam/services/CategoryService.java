package com.sunbeam.services;

import java.util.List;
import java.util.Optional;

import com.sunbeam.entities.Category;

public interface CategoryService {
    Category addCategory(Category category);
    List<Category> getAllCategories();
    Optional<Category> getCategoryById(Long id);
    Category updateCategory(Long id, Category category);
    void deleteCategory(Long id);
}
