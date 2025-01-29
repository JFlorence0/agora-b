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