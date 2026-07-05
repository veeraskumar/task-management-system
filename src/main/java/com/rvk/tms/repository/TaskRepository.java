package com.rvk.tms.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.rvk.tms.entity.Task;
import com.rvk.tms.enums.Status;

public interface TaskRepository extends JpaRepository<Task, Long> {

	Page<Task> findByStatus(Status status, Pageable pageable);

	Page<Task> findByCategoryId(Long categoryId, Pageable pageable);

	Page<Task> findByTitleContainingIgnoreCase(String title, Pageable pageable);

	Page<Task> findByUserId(Long userId, Pageable pageable);
}