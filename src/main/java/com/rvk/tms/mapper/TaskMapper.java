package com.rvk.tms.mapper;

import com.rvk.tms.dto.TaskResponse;
import com.rvk.tms.entity.Task;

public class TaskMapper {
	public static TaskResponse toResponse(Task task) {
		return new TaskResponse(task.getId(), task.getTitle(), task.getDescription(), task.getStatus(),
				task.getDueDate(), UserMapper.toResponse(task.getUser()),
				CategoryMapper.toResponse(task.getCategory()));
	}
}
