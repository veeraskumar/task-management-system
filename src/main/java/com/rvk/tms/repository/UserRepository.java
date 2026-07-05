package com.rvk.tms.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rvk.tms.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

	Optional<User> findByEmail(String email);
	
}