package com.agora.controller;

import com.agora.model.User;
import com.agora.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

// Marks this as the REST API controller
@RestController
// All endpoints in the class will start with "users"
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    // Constructor injection of UserService
    public UserController(UserService userService) {
        this.userService = userService;
    }

    // Get all users
    @GetMapping("/")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    // Get a user by email
    @GetMapping("/{email}")
    public Optional<User> getUserByEmail(@PathVariable String email) {
        return userService.getUserByEmail(email);
    }

    // Get a user by username
    @GetMapping("/username/{username}")
    public Optional<User> getUserByUsername(@PathVariable String username) {
        return userService.getUserByUsername(username);
    }

    // Create a new user
    @PostMapping("/create")
    public User createUser(@RequestBody User user) {
        return userService.createUser(user);
    }
}