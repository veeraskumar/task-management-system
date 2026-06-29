package com.rvk.tms.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rvk.tms.dto.TaskRequest;
import com.rvk.tms.dto.TaskResponse;
import com.rvk.tms.service.TaskService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/tasks")
public class TaskController {

	private final TaskService taskService;

	@PostMapping
	public ResponseEntity<TaskResponse> createTask(@Valid @RequestBody TaskRequest request) {
		return ResponseEntity.status(HttpStatus.CREATED).body(taskService.createTask(request));
	}

	@GetMapping
	public ResponseEntity<List<TaskResponse>> getAllTasks() {
		return ResponseEntity.ok(taskService.getAllTasks());
	}

	@GetMapping("/{id}")
	public ResponseEntity<TaskResponse> createTask(@PathVariable Long id) {
		return ResponseEntity.ok(taskService.getTaskById(id));
	}

	@PutMapping("/{id}")
	public ResponseEntity<TaskResponse> updateTask(@PathVariable Long id, @Valid @RequestBody TaskRequest request) {
		return ResponseEntity.ok(taskService.updateTask(id, request));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
		taskService.deleteTaskById(id);
		return ResponseEntity.noContent().build();
	}

}