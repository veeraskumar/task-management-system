package com.rvk.tms.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rvk.tms.dto.UserRegisterRequest;
import com.rvk.tms.dto.UserResponse;
import com.rvk.tms.dto.UserUpdateRequest;
import com.rvk.tms.entity.User;
import com.rvk.tms.enums.Role;
import com.rvk.tms.exception.ResourceAlreadyExistsException;
import com.rvk.tms.exception.ResourceNotFoundException;
import com.rvk.tms.mapper.UserMapper;
import com.rvk.tms.repository.UserRepository;
import com.rvk.tms.service.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;

	@Override
	@Transactional
	public UserResponse resgisterUser(UserRegisterRequest request) {

		if (userRepository.findByEmail(request.email()).isPresent())
			throw new ResourceAlreadyExistsException("Email Alreay Exists");

		User user = new User();

		user.setName(request.name());
		user.setEmail(request.email());
		user.setRole(Role.USER);
		user.setPassword(request.password());
		user.setCreatedAt(LocalDateTime.now());

		User savedUser = userRepository.save(user);

		return UserMapper.toResponse(savedUser);
	}

	@Override
	public List<UserResponse> getAllUsers() {
		return userRepository.findAll().stream().map(UserMapper::toResponse).toList();
	}

	@Override
	public UserResponse getUserById(Long id) {
		User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User Not Exist"));
		return UserMapper.toResponse(user);
	}

	@Override
	@Transactional
	public UserResponse updateUser(Long id, UserUpdateRequest request) {
		User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User Not Exist"));

		user.setName(request.name());
		user.setEmail(request.email());

		User savedUser = userRepository.save(user);

		return UserMapper.toResponse(savedUser);
	}

	@Override
	@Transactional
	public void deleteUser(Long id) {
		User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User Not Exist"));
		userRepository.delete(user);
	}

}
