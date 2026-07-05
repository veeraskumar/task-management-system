# Task Management System

A secure RESTful Task Management System built with **Spring Boot 3**, **Spring Security**, **JWT Authentication**, **Hibernate**, and **MariaDB**.

The application allows users to manage tasks with role-based access control. It implements secure authentication using JWT access and refresh tokens, supports role-based authorization for **USER**, **MANAGER**, and **ADMIN**, and follows a clean layered architecture.

## Features

* User Registration & Login
* JWT Authentication & Authorization
* Access Token & Refresh Token Support
* Logout with Refresh Token Revocation
* Role-Based Access Control (USER, MANAGER, ADMIN)
* Task Management (Create, Read, Update, Delete)
* Category Management
* Task Ownership Authorization
* Global Exception Handling
* Request Validation
* Pagination
* Swagger/OpenAPI Documentation
* Layered Architecture
* MariaDB Database Integration

## Tech Stack

* Java 21
* Spring Boot 3
* Spring Security
* Spring Data JPA (Hibernate)
* MariaDB
* Maven
* JWT (JJWT)
* Lombok
* Swagger / OpenAPI

## Architecture

```text
Controller
    │
    ▼
Service
    │
    ▼
Repository
    │
    ▼
MariaDB
```

## Swagger UI

After starting the application, open:

http://localhost:8080/swagger-ui/index.html

The API documentation is available here, and protected endpoints can be tested using the Authorize button with a JWT Bearer token.

## Running the Application

1. Clone the repository
2. Configure MariaDB
3. Update application.properties
4. Run the application:

	```bash
	mvn spring-boot:run
	```
5. Open Swagger UI

## Author

**Veeras**

- GitHub: https://github.com/veeraskumar
- LinkedIn: https://linkedin.com/in/veeraskumar