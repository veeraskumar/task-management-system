package com.rvk.tms.service;

import java.util.List;

import com.rvk.tms.dto.CategoryRequest;
import com.rvk.tms.dto.CategoryResponse;

public interface CategoryService {

	CategoryResponse createCategory(CategoryRequest request);

	List<CategoryResponse> getAllCategories();

	CategoryResponse getCategoryById(Long id);

	CategoryResponse updateCategory(Long id, CategoryRequest request);

	void deleteCategory(Long id);

}