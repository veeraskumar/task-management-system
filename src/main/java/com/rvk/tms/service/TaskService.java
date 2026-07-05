package com.rvk.tms.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.rvk.tms.dto.TaskRequest;
import com.rvk.tms.dto.TaskResponse;
import com.rvk.tms.entity.Task;
import com.rvk.tms.enums.Status;

public interface TaskService {

	TaskResponse createTask(TaskRequest request);

	Page<TaskResponse> getAllTasks(Pageable pageable);

	TaskResponse getTaskById(Long id);

	TaskResponse updateTask(Long id, TaskRequest request);

	void deleteTaskById(Long id);
	
	Page<TaskResponse> getTaskByStatus(Status status, Pageable pageable);

	Page<TaskResponse> getTaskByCategoryId(Long categoryId, Pageable pageable);

	Page<TaskResponse> getTaskByTitleContainingIgnoreCase(String title, Pageable pageable);

	Page<TaskResponse> getTaskByUserId(Long userId, Pageable pageable);
	
	
	
	
	
}