package com.rvk.tms.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rvk.tms.dto.TaskRequest;
import com.rvk.tms.dto.TaskResponse;
import com.rvk.tms.entity.Category;
import com.rvk.tms.entity.Task;
import com.rvk.tms.entity.User;
import com.rvk.tms.exception.ResourceNotFoundException;
import com.rvk.tms.mapper.TaskMapper;
import com.rvk.tms.repository.CategoryRepository;
import com.rvk.tms.repository.TaskRepository;
import com.rvk.tms.repository.UserRepository;
import com.rvk.tms.service.TaskService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TaskServiceImpl implements TaskService {

	private final TaskRepository taskRepository;
	private final UserRepository userRepository;
	private final CategoryRepository categoryRepository;

	@Override
	@Transactional
	public TaskResponse createTask(TaskRequest request) {
		User user = userRepository.findById(request.userId())
				.orElseThrow(() -> new ResourceNotFoundException("USer Not Found"));

		Category category = categoryRepository.findById(request.categoryId())
				.orElseThrow(() -> new ResourceNotFoundException("Category Not Found"));

		Task task = new Task();

		task.setTitle(request.title());
		task.setDescription(request.description());
		task.setStatus(request.status());
		task.setUser(user);
		task.setCategory(category);
		task.setDueDate(request.dueDate());

		Task savedTask = taskRepository.save(task);

		return TaskMapper.toResponse(savedTask);
	}

	@Override
	public List<TaskResponse> getAllTasks() {
		return taskRepository.findAll().stream().map(TaskMapper::toResponse).toList();
	}

	@Override
	public TaskResponse getTaskById(Long id) {
		Task task = taskRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Task is not Found"));
		return TaskMapper.toResponse(task);
	}

	@Override
	@Transactional
	public TaskResponse updateTask(Long id, TaskRequest request) {
		Task task = taskRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Task is not Found"));

		User user = userRepository.findById(request.userId())
				.orElseThrow(() -> new ResourceNotFoundException("USer Not Found"));

		Category category = categoryRepository.findById(request.categoryId())
				.orElseThrow(() -> new ResourceNotFoundException("Category Not Found"));

		task.setTitle(request.title());
		task.setDescription(request.description());
		task.setStatus(request.status());
		task.setUser(user);
		task.setCategory(category);
		task.setDueDate(request.dueDate());

		Task savedTask = taskRepository.save(task);

		return TaskMapper.toResponse(savedTask);
	}

	@Override
	@Transactional
	public void deleteTaskById(Long id) {
		Task task = taskRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Task is not Found"));
		taskRepository.delete(task);
	}

}
