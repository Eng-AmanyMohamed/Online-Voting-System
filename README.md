# 🗳️ Online Voting System (Back-End)

 **This project is developed as part of the *Banque Misr Backend Internship* program, focusing on mastering Spring Boot, RESTful APIs, security, and enterprise-grade application design.**

A secure, role-based online voting system built with **Spring Boot**, designed to manage digital elections with JWT authentication, vote integrity, and real-time results.

This backend API supports:
- Admin and voter roles
- Secure login with JWT
- Candidate registration
- Voter assignment
- One-time voting within a time window
- Real-time result calculation
- RESTful design with validation and error handling

---
## 🛠️ Technologies & Tools Used

| Category       | Technology |
|----------------|----------|
| Framework      | Spring Boot (Java 17+) |
| Build Tool     | Maven |
| Database       | H2 (Dev), MySQL (Production) |
| Security       | Spring Security + JWT (JWT) |
| Validation     | Jakarta Bean Validation (Hibernate Validator) |
| ORM            | Spring Data JPA / Hibernate |
| API Testing      | Postman ,Swagger UI  |
---

## 📁 Project Structure
```
src/main/java/com/onlinevotingsystem/
│
├── model/ → JPA entities (User, Candidate, Election, Vote, Role enum)
├── dto/ → Data Transfer Objects
│ ├── request/ → Input DTOs (UserRegisterDto, VoteDto)
│ └── response/ → Output DTOs (UserResponseDto, ResultDto)
├── repository/ → Spring Data JPA repositories
├── service/ → Business logic 
├── controller/ → REST endpoints (AdminController, VoterController, AuthController)
├── security/ → JWT filters, config, utilities
├── exception/ → Custom exceptions and global handler
└── OnlineVotingSystemApplication.java → Main class
```
## 🚀 Project Setup

### 1. Prerequisites
- Java 17 or higher
- Maven 
- IDE (IntelliJ IDEA, Eclipse, or VS Code)
- MySQL

### 2. Clone the Project
```bash
git clone https://github.com/Eng-AmanyMohamed/online-voting-system.git
cd online-voting-system
```
### 3. Configure Database
Edit src/main/resources/application.properties (for MySQL):
```
spring.datasource.url=jdbc:mysql://localhost:3306/bank_system
spring.datasource.username=yourusername
spring.datasource.password=yourpassword
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

### 4.🧪 Running the Application
Using Maven (Command Line)
```
mvn spring-boot:run
```
---
## 🌐 Sample API Endpoints

All secured endpoints require a valid JWT in the header:  
`Authorization: Bearer <your-jwt-token>`
###  User (Authentication & Management)
- POST `/api/users/register`Register a new user (ِAdmin,Voter).
- POST `/api/auth/login`Authenticate a user (admin or voter) and receive a JWT.
### 🗳️ Election
- POST `/api/admin/elections` Create a new election with a voting time window. (Admin only)
- POST `/api/admin/voters/assign` Assign a registered voter to an election. (Admin only)
###  Candidate
- POST `/api/admin/candidates` Register a new candidate for an election. (Admin only)
- GET `/api/voter/candidates` Get the list of all candidates in the current election. (Voter only)
### 📝 Vote
- POST `/api/voter/vote` Cast a vote for a candidate. Only allowed once per voter and within election time. (Voter only)
### 📊 Results
- GET `/api/admin/results` Get real-time election results sorted by vote count (descending). (Admin only)
---
## ✅ Validation & Security
- JWT Required: All endpoints (except /login) require a valid token in Authorization: Bearer <token> header.
  - Role-Based Access:
  - Admin: Full access
  - Voter: Vote & view candidates only
- Input Validation:
  - Email format, password strength, required fields
  - Invalid data → 400 Bad Request
- Vote Protection:
  - One vote per user
  - Voting only during startTime to endTime
  - Attempt outside window → 403 Forbidden with "Voting is currently closed."
 
---

## 🧹 Global Exception Handling
- Returns structured JSON errors:
```
json
{
  "timestamp": "2025-04-05T10:00:00",
  "status": 400,
  "error": "Bad Request",
  "message": "Email is required",
}
```
- Handles:
   - Invalid login.
   - Duplicate vote.
   - Resource not found.
   - Voting closed.
   - Unauthorized access.

---

## Team:
This project was a collaborative effort with an outstanding team:
- Amany Mohamed
- Mohamed Mostafa
- Youssef Ahmed
- Touka Mohamed
- Leena Hesham
- Esraa Tarek
- Ayten Aaser
- Mariam Ayman

