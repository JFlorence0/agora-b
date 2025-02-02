package com.agora.models;

// This allows the defined class to be mapped to a database table
import jakarta.persistence.*;
// For timestamps etc
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

// Marks the class as a database table
@Entity
// Declares the name of the table in Postgres
@Table(name = "users")
public class User {
    //PK
    @Id
    // auto-increments the ID
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // Figure out why long instead of int?
    private Long id;

    // Make email REQUIRED and unique
    @Column(nullable = false, unique = true)
    private String email;

    // REQUIRED
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Column(nullable = false)
    private String password;

    // NULL/BANK/UNIQUE if it exists.
    @Column(unique = true)
    private String username;

    // Auto now add = true
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    /* @PrePersist means that this runs BEFORE creating an instance of the model 
     * it sets the created and updated at fields at once
    */
    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = createdAt;
    }

    // Same as above, but runs before update.
    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }

    // Use Long instead of Int bc int only goes up to about 2 billion.
    public Long getId() { return id; }

    // public method but email itself is only accessible via this method
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    // same as email
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    // same as email, password
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    // same as email, password, username.
    public LocalDateTime getCreatedAt() { return createdAt; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }
}
