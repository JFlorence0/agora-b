package com.agora.service;

import com.agora.model.User;
import com.agora.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

// Marks this as a service layer component
@Service
public class UserService {

    private final UserRepository userRepository;

    // Constructor-based dependency injection
    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Get all users
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // Get user by email
    public Optional<User> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public Optional<User> getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    // Create a new user (No password hashing yet)
    public User createUser(User user) {
        return userRepository.save(user);
    }
}