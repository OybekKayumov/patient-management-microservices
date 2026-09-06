package com.ok.authservice.service;

import com.ok.authservice.dto.LoginRequestDTO;
import com.ok.authservice.model.User;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

	private final UserService userService;

	public AuthService(UserService userService) {this.userService = userService;}

	public Optional<String> authenticate(LoginRequestDTO loginRequestDTO){

		Optional<User> user = userService.findByEmail(loginRequestDTO.getEmail())
						.filter(u -> passwordEncoder.matches(
										loginRequestDTO.getPassword(), u.getPassword()));

		return null;

	}
}
