package com.rvk.tms.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rvk.tms.dto.TaskRequest;
import com.rvk.tms.dto.TaskResponse;
import com.rvk.tms.enums.Status;
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
	public ResponseEntity<Page<TaskResponse>> getAllTasks(Pageable pageable) {
		return ResponseEntity.ok(taskService.getAllTasks(pageable));
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

	@GetMapping
	public ResponseEntity<Page<TaskResponse>> getTaskByStatus(@RequestParam Status status, Pageable pageable) {
		return ResponseEntity.ok(taskService.getTaskByStatus(status, pageable));
	}

	@GetMapping
	public ResponseEntity<Page<TaskResponse>> getTaskByCategoryId(@RequestParam Long categoryId, Pageable pageable) {
		return ResponseEntity.ok(taskService.getTaskByCategoryId(categoryId, pageable));
	}

	@GetMapping
	public ResponseEntity<Page<TaskResponse>> getTaskByTitleContainingIgnoreCase(@RequestParam String title,
			Pageable pageable) {
		return ResponseEntity.ok(taskService.getTaskByTitleContainingIgnoreCase(title, pageable));
	}

	@GetMapping
	public ResponseEntity<Page<TaskResponse>> getTaskByUserId(@RequestParam Long userId, Pageable pageable) {
		return ResponseEntity.ok(taskService.getTaskByUserId(userId, pageable));
	}

}