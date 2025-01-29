package com.agora.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@RestController
@RequestMapping("/test") // Base URL: http://localhost:8080/test
public class DatabaseTestController {

    private final DataSource dataSource;

    // Constructor to inject DataSource (database connection pool)
    public DatabaseTestController(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @GetMapping("/db") // Endpoint: http://localhost:8080/test/db
    public String testDatabaseConnection() {
        try (Connection connection = dataSource.getConnection()) {
            return "✅ Connected to: " + connection.getMetaData().getDatabaseProductName();
        } catch (SQLException e) {
            return "❌ Database connection failed: " + e.getMessage();
        }
    }
}