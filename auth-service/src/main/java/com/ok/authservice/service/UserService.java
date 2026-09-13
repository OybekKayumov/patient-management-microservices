package com.ok.authservice.service;

import com.ok.authservice.model.User;
import com.ok.authservice.repo.UserRepo;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

	private final UserRepo userRepo;

	public UserService(UserRepo userRepo) {
		this.userRepo = userRepo;
	}

	public Optional<User> findByEmail(String email){

		return userRepo.findByEmail(email);
	}
}
