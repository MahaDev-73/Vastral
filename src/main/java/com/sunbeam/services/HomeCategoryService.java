package com.sunbeam.services;

import java.util.List;

import com.sunbeam.entities.HomeCategory;

public interface HomeCategoryService {
	 HomeCategory createHomeCategory(HomeCategory homeCategory);	   
	 List<HomeCategory> createCategories(List<HomeCategory> homeCategories);
	 HomeCategory updateHomeCategory(HomeCategory homeCategory,Long id) throws Exception;
	 List<HomeCategory> getAllHomeCategories();
}
