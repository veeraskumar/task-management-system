package com.rvk.tms.mapper;

import com.rvk.tms.dto.CategoryResponse;
import com.rvk.tms.entity.Category;

public class CategoryMapper {
	public static CategoryResponse toResponse(Category category) {
		return new CategoryResponse(category.getId(), category.getName(), category.getDescription());
	}
}
