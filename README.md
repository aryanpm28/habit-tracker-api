# Habit Tracker API

A simple and beginner-friendly REST API built using Spring Boot to help users track daily habits and maintain consistency.

---

## Features

- Create a new habit
- Get all habits
- Get habit by ID
- Update habit details
- Delete a habit
- Mark habit as completed
- View habit completion history
- Habit statistics tracking
- Global exception handling
- Swagger API documentation

---

## Technologies Used

- Java
- Spring Boot
- Spring Data JPA
- MySQL
- Maven
- Lombok
- Swagger / OpenAPI

---

## Project Structure

```bash
src
 └── main
     └── java
         └── com.HabitTrackerAPI.Habit.Tracker.API
             ├── AuthController.java
             ├── GlobalExceptionHandler.java
             ├── Habit.java
             ├── HabitAlreadyCompletedException.java
             ├── HabitController.java
             ├── HabitDTO.java
             ├── HabitLog.java
             ├── HabitRepository.java
             ├── HabitResponseDTO.java
             ├── HabitService.java
             ├── HabitStatsDTO.java
             ├── ResourceNotFoundException.java
             └── SwaggerConfig.java
```

---

## API Endpoints

### Habit APIs

| Method | Endpoint | Description |
|--------|-----------|-------------|
| POST | `/habits` | Create habit |
| GET | `/habits` | Get all habits |
| GET | `/habits/{id}` | Get habit by ID |
| PUT | `/habits/{id}` | Update habit |
| DELETE | `/habits/{id}` | Delete habit |

### Habit Tracking APIs

| Method | Endpoint | Description |
|--------|-----------|-------------|
| POST | `/habits/{id}/complete` | Mark habit as completed |
| GET | `/habits/{id}/history` | Get habit history |
| GET | `/habits/{id}/stats` | Get habit statistics |

---

## Swagger Documentation

After running the project, open:

```bash
http://localhost:8080/swagger-ui/index.html
```

---

## Database Configuration

Update your `application.properties` file:

```properties
spring.datasource.url=jdbc:mysql://localhost:3307/habit_db
spring.datasource.username=your_username
spring.datasource.password=your_password

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

---

## How to Run the Project

### 1. Clone the repository

```bash
git clone https://github.com/aryanpm28/habit-tracker-api.git
```

### 2. Open the project in VS Code or IntelliJ

### 3. Configure MySQL database

### 4. Run the application

```bash
mvn spring-boot:run
```

---

## Sample JSON

### Create Habit

```json
{
  "name": "Exercise",
  "time": "07:00:00",
  "category": "Health",
  "reminderEnabled": true
}
```

---

## Author

Aryan Patil
