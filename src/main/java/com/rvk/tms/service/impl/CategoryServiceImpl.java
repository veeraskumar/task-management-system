package com.rvk.tms.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rvk.tms.dto.CategoryRequest;
import com.rvk.tms.dto.CategoryResponse;
import com.rvk.tms.entity.Category;
import com.rvk.tms.exception.ResourceAlreadyExistsException;
import com.rvk.tms.exception.ResourceNotFoundException;
import com.rvk.tms.mapper.CategoryMapper;
import com.rvk.tms.repository.CategoryRepository;
import com.rvk.tms.service.CategoryService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CategoryServiceImpl implements CategoryService {

	public final CategoryRepository categoryRepository;

	@Override
	@Transactional
	public CategoryResponse createCategory(CategoryRequest request) {

		if (categoryRepository.findByName(request.name()).isPresent()) {
			throw new ResourceAlreadyExistsException("Name is already in Catogery");
		}

		Category category = new Category();

		category.setName(request.name());
		category.setDescription(request.description());

		Category savedCategory = categoryRepository.save(category);

		return CategoryMapper.toResponse(savedCategory);
	}

	@Override
	public List<CategoryResponse> getAllCategories() {
		return categoryRepository.findAll().stream().map(CategoryMapper::toResponse).toList();
	}

	@Override
	public CategoryResponse getCategoryById(Long id) {
		Category category = categoryRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Category is not Found"));
		return CategoryMapper.toResponse(category);
	}

	@Override
	@Transactional
	public CategoryResponse updateCategory(Long id, CategoryRequest request) {
		Category category = categoryRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Category is not Found"));

		category.setName(request.name());
		category.setDescription(request.description());

		Category savedCategory = categoryRepository.save(category);

		return CategoryMapper.toResponse(savedCategory);
	}

	@Override
	@Transactional
	public void deleteCategory(Long id) {
		Category category = categoryRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Category is not Found"));
		categoryRepository.delete(category);
	}

}
