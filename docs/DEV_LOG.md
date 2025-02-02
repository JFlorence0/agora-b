# 📝 Development Log - Agora Backend  

This document tracks the development progress of the Agora backend, built with Java Spring Boot and PostgreSQL. Each entry includes key decisions, progress made, and challenges encountered.  

---

## 🗓 January 28, 2025 - Project Initialization  

### ✅ What I Did  
- Created the **Agora Backend** project using **Spring Boot**.  
- Set up **Gradle** as the build tool instead of Maven.  
- Successfully ran the Spring Boot application to verify everything was working.  

### 🤔 Why I Chose Gradle Over Maven  
- **Gradle is newer and actively evolving**, while Maven has been around longer but feels more rigid.  
- **Performance:** Gradle builds are generally faster due to incremental builds and better caching.  
- **Flexibility:** Gradle’s scripting (Groovy/Kotlin) provides more control over dependencies and configurations.  
- **Industry Trend:** While Maven is still widely used, Gradle adoption has been increasing, especially in modern Java projects.  

### 🛠 Commands Used  
1. **Created the Spring Boot project**  
   - Used Spring Initializr to generate a project with Gradle as the build system.  

2. **Ran the application:**

---

## 🗓 January 29, 2025 - Database Setup and Connection Test  

### ✅ What I Did  
- Created a **PostgreSQL database** named `agora`.  
- Connected the **Spring Boot application** to PostgreSQL by configuring `application.properties`.  
- Created a **DatabaseTestController** to verify the connection.  
- Successfully tested the connection by visiting: http://localhost:8080/test/db
- The API returned `"✅ Connected to: PostgreSQL"`, confirming the setup worked correctly.  

### 🛠 Commands Used  
1. **Created the database in PostgreSQL:**  
CREATE DATABASE agora;
2. **Started the PostgreSQL shell:**  
psql -U postgres
3. **Ran the Spring Boot application:**  
./gradlew bootRun
4. **Tested the connection in the browser:**  
http://localhost:8080/test/db

### 🔥 Challenges  
- Initially encountered `No qualifying bean of type 'javax.sql.DataSource' available`.  
- Fixed it by ensuring **database credentials were correctly set** in `application.properties`.  

### 🚀 Next Steps  
- **Tonight:** Start working on the `User` model.  
- Define a `User` entity with basic fields (`id`, `name`, `email`).  
- Set up a `UserRepository` to interact with the database.  

---

## [2025-01-29] Implemented User Model, Repository, Service, and Controller

### ✅ Step 1: Created the `User` Model
- Added a new `User` entity in `src/main/java/com/agora/model/User.java`
- Fields: `id`, `email`, `password`, `username`, `createdAt`, `updatedAt`
- Used `@Entity` to map it as a database table
- Added lifecycle hooks (`@PrePersist` and `@PreUpdate`) to manage timestamps

### ✅ Step 2: Created `UserRepository`
- Defined `UserRepository` interface in `src/main/java/com/agora/repository/UserRepository.java`
- Extended `JpaRepository<User, Long>` to provide built-in database operations
- Added custom queries:
  - `Optional<User> findByEmail(String email);`
  - `Optional<User> findByUsername(String username);`

### ✅ Step 3: Implemented `UserService`
- Created `UserService.java` in `src/main/java/com/agora/service/`
- Injected `UserRepository` using constructor injection
- Implemented business logic methods:
  - `getAllUsers()`
  - `getUserByEmail(String email)`
  - `getUserByUsername(String username)`
  - `createUser(User user)`

### ✅ Step 4: Implemented `UserController`
- Created `UserController.java` in `src/main/java/com/agora/controller/`
- Exposed REST API endpoints:
  - `GET /users/` → Fetch all users
  - `GET /users/{email}` → Fetch user by email
  - `GET /users/username/{username}` → Fetch user by username
  - `POST /users/create` → Create a new user

### ✅ Step 5: Tested Endpoints
- Successfully tested all endpoints using **Insomnia** and `curl`
- Confirmed that user creation, retrieval by email, and retrieval by username all work

---

## [2025-01-31] Implemented User Registration & Authentication with Password Encryption

1. **Updated the User Model**  
- Added @JsonProperty(access = JsonProperty.Access.WRITE_ONLY) to user password.
- Ensured the password field is received in requests but not returned in responses
- Verified that User.java correctly maps JSON data

2. **Implemented Secure Password Storage**  
- Added BCrypt password hashing before storing user passwords
- Implemented hashing logic inside UserService.createUser(User user)

3. **Implemented the UserController for Registration**  
- updated /users/create endpoint, handles password hashing.

4. **Successfully Tested Endpoints**
- POST /users/create → Creates users securely with hashed passwords
- Tested in Insomnia and verified database entries

## [2025-02-01] Implemented Tag System for Issue Categorization

1. **Created the Tag Model**  
- Added a Tag entity with a name field (unique and required).
- Ensured Tag can be used for multiple features (Issue categorization, User follows).

2. **Implemented TagRepository**  
- Added a findByName(String name) method to prevent duplicate tags.
- Enabled basic CRUD operations for tags.

3. **Developed TagService for Efficient Tag Management**  
- Implemented findOrCreateTag(String name) to reuse existing tags or create new ones.
- Created getAllTags() to fetch all available tags.

4. **Implemented TagController (Basic API for Tags)**
- GET /tags → Fetches all available tags.
- POST /tags?name=Education → Creates a new tag if it doesn't exist.


5. **Successfully Tested Tag Creation & Retrieval**
- Verified that duplicate tags are not created.
- Tested basic tag functionality in the database.

## [2025-02-02] Implemented Issue Model, Repository, Service, and Controller

1. **Created the Issue Model**  
- Defined Issue entity with fields for title, description, status, type, region, sponsor, and reference ID.
- Made a Many-to-Many relationship with Tag for issue categorization.
- Used @PrePersist to automatically set createdAt timestamp.
- Added isActive() method to determine whether an issue is open for voting.

2. **Implemented IssueRepository**  
- Added findByStatus(IssueStatus status) → Fetches issues by status (Active, Passed, etc.).
- Added findByIssueType(IssueType issueType) → Fetches issues by type (Law, Election, Policy, etc.).
- Added findByRegion(IssueRegion region) → Fetches issues by jurisdiction.
- Added findOpenVotingIssues() → Retrieves issues that are currently open for voting.

3. **Developed IssueService**  
- Implemented methods for creating, updating, deleting, and retrieving issues.

4. **Built IssueController for REST API Access**
- GET /issues → Fetches all issues.
- GET /issues/{id} → Fetches a specific issue by ID.
- GET /issues/active → Fetches only active issues.
- GET /issues/open-voting → Fetches only issues currently open for voting.
- POST /issues → Creates a new issue.
- PUT /issues/{id} → Updates an existing issue.
- DELETE /issues/{id} → Deletes an issue if it exists.

5. **Built and Tested**
- Verified that CRUD operations work using Insomnia.