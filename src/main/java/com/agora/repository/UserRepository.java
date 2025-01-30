package com.agora.repository;

import com.agora.model.User;

// Allows built in DB operations
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
// Safe querying
import java.util.Optional;

// Marks this as a Spring-managed component
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    
    Optional<User> findByEmail(String email); // Custom query to find a user by email
    
    Optional<User> findByUsername(String username); // Custom query to find a user by username
}