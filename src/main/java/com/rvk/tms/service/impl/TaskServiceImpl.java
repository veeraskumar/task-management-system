package com.rvk.tms.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rvk.tms.dto.TaskRequest;
import com.rvk.tms.dto.TaskResponse;
import com.rvk.tms.entity.Category;
import com.rvk.tms.entity.Task;
import com.rvk.tms.entity.User;
import com.rvk.tms.enums.Role;
import com.rvk.tms.enums.Status;
import com.rvk.tms.exception.ResourceNotFoundException;
import com.rvk.tms.mapper.TaskMapper;
import com.rvk.tms.repository.CategoryRepository;
import com.rvk.tms.repository.TaskRepository;
import com.rvk.tms.security.CustomUserDetails;
import com.rvk.tms.service.TaskService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TaskServiceImpl implements TaskService {

	private final TaskRepository taskRepository;
	private final CategoryRepository categoryRepository;

	private User getCurrentUser() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		if (authentication == null || !(authentication.getPrincipal() instanceof CustomUserDetails userDetails)) {
			throw new AccessDeniedException("User is not authenticated");
		}

		return userDetails.getUser();
	}

	private void checkTaskPermission(Task task) {

		User currentUser = getCurrentUser();

		if (currentUser.getId().equals(task.getUser().getId()))
			return;

		if (currentUser.getRole() == Role.ADMIN)
			return;

		if (currentUser.getRole() == Role.MANAGER)
			return;

		throw new AccessDeniedException("You are not allowed to access this task.");
	}

	@Override
	@Transactional
	public TaskResponse createTask(TaskRequest request) {

		Category category = categoryRepository.findById(request.categoryId())
				.orElseThrow(() -> new ResourceNotFoundException("Category Not Found"));

		Task task = new Task();

		task.setTitle(request.title());
		task.setDescription(request.description());
		task.setStatus(request.status());
		task.setUser(getCurrentUser());
		task.setCategory(category);
		task.setDueDate(request.dueDate());

		Task savedTask = taskRepository.save(task);

		return TaskMapper.toResponse(savedTask);
	}

	@PreAuthorize("hasRole('ADMIN') or hasRole('MANAGER')")
	@Override
	public Page<TaskResponse> getAllTasks(Pageable pageable) {
		Page<Task> page = taskRepository.findAll(pageable);
		return page.map(TaskMapper::toResponse);
	}

	@Override
	public TaskResponse getTaskById(Long id) {
		Task task = taskRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Task is not Found"));
		checkTaskPermission(task);
		return TaskMapper.toResponse(task);
	}

	@Override
	@Transactional
	public TaskResponse updateTask(Long id, TaskRequest request) {
		Task task = taskRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Task is not Found"));

		checkTaskPermission(task);

		Category category = categoryRepository.findById(request.categoryId())
				.orElseThrow(() -> new ResourceNotFoundException("Category Not Found"));

		task.setTitle(request.title());
		task.setDescription(request.description());
		task.setStatus(request.status());
		task.setUser(getCurrentUser());
		task.setCategory(category);
		task.setDueDate(request.dueDate());

		Task savedTask = taskRepository.save(task);

		return TaskMapper.toResponse(savedTask);
	}

	@Override
	@Transactional
	public void deleteTaskById(Long id) {
		Task task = taskRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Task is not Found"));
		checkTaskPermission(task);
		taskRepository.delete(task);
	}

	@Override
	public Page<TaskResponse> getTaskByStatus(Status status, Pageable pageable) {
		return taskRepository.findByStatus(status, pageable).map(TaskMapper::toResponse);
	}

	@Override
	public Page<TaskResponse> getTaskByCategoryId(Long categoryId, Pageable pageable) {
		return taskRepository.findByCategoryId(categoryId, pageable).map(TaskMapper::toResponse);
	}

	@Override
	public Page<TaskResponse> getTaskByTitleContainingIgnoreCase(String title, Pageable pageable) {
		return taskRepository.findByTitleContainingIgnoreCase(title, pageable).map(TaskMapper::toResponse);
	}

	@Override
	public Page<TaskResponse> getTaskByUserId(Long userId, Pageable pageable) {
		User user = getCurrentUser();
		return taskRepository.findByUserId(user.getId(), pageable).map(TaskMapper::toResponse);
	}

}
