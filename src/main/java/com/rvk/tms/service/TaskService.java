package com.rvk.tms.service;

import java.util.List;

import com.rvk.tms.dto.TaskRequest;
import com.rvk.tms.dto.TaskResponse;

public interface TaskService {

	TaskResponse createTask(TaskRequest request);

	List<TaskResponse> getAllTasks();

	TaskResponse getTaskById(Long id);

	TaskResponse updateTask(Long id, TaskRequest request);

	void deleteTaskById(Long id);
}