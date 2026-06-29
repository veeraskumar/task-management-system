package com.rvk.tms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rvk.tms.entity.Task;

public interface TaskRepository extends JpaRepository<Task, Long> {

}