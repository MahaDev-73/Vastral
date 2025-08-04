package com.sunbeam.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sunbeam.entities.Home;
import com.sunbeam.entities.HomeCategory;
import com.sunbeam.services.HomeCategoryService;
import com.sunbeam.services.HomeService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor

public class HomeCategoryController {

	    private final HomeCategoryService homeCategoryService;
	    private final HomeService homeService;

	    @PostMapping("/home/categories")
	    public ResponseEntity<Home> createHomeCategories(
	            @RequestBody List<HomeCategory> homeCategories
	    ) {
	        List<HomeCategory> categories = homeCategoryService.createCategories(homeCategories);
	        Home home=homeService.creatHomePageData(categories);
	        return new ResponseEntity<>(home, HttpStatus.ACCEPTED);
	    }
	
	    @GetMapping("/admin/home-category")
	    public ResponseEntity<List<HomeCategory>> getHomCategory() {
	    	List<HomeCategory> categories = homeCategoryService.getAllHomeCategories();
	    	return ResponseEntity.ok(categories);
	    }
	    
	    
	    @PatchMapping("/admin/home-category/{id}")
	    public ResponseEntity<HomeCategory> updateHomeCategory(@PathVariable Long id, 
	    													@RequestBody HomeCategory homeCategory) throws Exception{
			HomeCategory updateHomeCategory = homeCategoryService.updateHomeCategory(homeCategory, id);				
	    	return ResponseEntity.ok(updateHomeCategory);
	    }
	    
}
