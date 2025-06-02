# Patient Management System

A Spring Boot application for managing patient records. This application allows healthcare professionals to create, retrieve, update, and delete patient information efficiently.

## Features

- **Complete CRUD Operations**: Create, Read, Update, and Delete patient records
- **Custom Search Functionality**: Find patients by name patterns using JPA query methods
- **Automatic Database Schema**: Hibernate automatically creates tables from JPA entities
- **In-Memory Database**: H2 database for rapid development and testing
- **Web Console Access**: Built-in H2 console for direct database interaction
- **Production Ready**: Easy migration to MySQL or other databases
- **Automatic Testing**: CommandLineRunner demonstrates all operations on startup

## Patient Entity Structure

Each patient record contains:
- **id**: Auto-generated primary key (Long)
- **nom**: Patient's name (String)
- **dateNaissance**: Birth date (Date)
- **malade**: Health status - sick/healthy (boolean)
- **score**: Health score (int)

## Repository Features

The `PatientRepository` provides:
- **Standard CRUD**: Inherited from `JpaRepository`
- **Custom Query**: `findByNomContains(String keyword)` for name-based search
- **Pagination Support**: Built-in pagination and sorting capabilities
- **Transaction Management**: Automatic transaction handling

## Technologies

- Spring Boot
- Spring Data JPA
- H2 Database (development)
- MySQL (production)
- Lombok

## Getting Started

### Prerequisites

- Java 17 or higher
- Maven
- MySQL (for production use)

### Running the Application

1. Clone the repository
2. Build the project: `mvn clean install`
3. Run the application: `mvn spring-boot:run`
4. Access the H2 console at: http://localhost:8080/h2-console

## Database Configuration

### H2 Database (Current Configuration)

The application is currently configured to use H2 in-memory database:

```properties
spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
spring.jpa.hibernate.ddl-auto=update
spring.h2.console.enabled=true
```

### Accessing H2 Console

1. Start the application: `./mvnw spring-boot:run`
2. Open your browser and navigate to: `http://localhost:8080/h2-console`
3. Use the following connection settings:
   - **JDBC URL**: `jdbc:h2:mem:testdb`
   - **Username**: `sa`
   - **Password**: (leave empty)
4. Click "Connect" to access the database
5. You can run SQL queries like: `SELECT * FROM PATIENT;`

### MySQL Setup (Alternative Configuration)

For production use, you can switch to MySQL by updating `application.properties`:

```properties
# MySQL Configuration
spring.datasource.url=jdbc:mysql://localhost:3306/patientdb?useSSL=false&serverTimezone=UTC
spring.datasource.username=root
spring.datasource.password=yourpassword
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.jpa.database-platform=org.hibernate.dialect.MySQL8Dialect
spring.jpa.hibernate.ddl-auto=update
```

1. Install MySQL if not already installed
2. Create a database named `patientdb`:
   ```sql
   CREATE DATABASE patientdb;
   ```
3. Configure username/password in `application.properties`
4. Run the application - Hibernate will create the necessary tables

Note: The application now uses H2 for development. The MySQL connector dependency is already included for easy switching.

## CRUD Operations Demo

When you run the application, it automatically demonstrates all CRUD (Create, Read, Update, Delete) operations through the `PatientTestingCommandLineRunner`. Here's the expected output:

```
========== TESTING PATIENT REPOSITORY CRUD OPERATIONS ==========

Creating patients...

Retrieving all patients:
Patient{id=1, nom='Mohamed', dateNaissance=2025-06-02 15:16:43.656, malade=true, score=75}
Patient{id=2, nom='Ahmed', dateNaissance=2025-06-02 15:16:43.656, malade=false, score=50}
Patient{id=3, nom='Sara', dateNaissance=2025-06-02 15:16:43.656, malade=true, score=65}
Patient{id=4, nom='Fatima', dateNaissance=2025-06-02 15:16:43.656, malade=false, score=80}

Retrieving patient by ID 1:
Patient{id=1, nom='Mohamed', dateNaissance=2025-06-02 15:16:43.656, malade=true, score=75}

Searching patients with 'a' in their name:
Patient{id=1, nom='Mohamed', dateNaissance=2025-06-02 15:16:43.656, malade=true, score=75}
Patient{id=3, nom='Sara', dateNaissance=2025-06-02 15:16:43.656, malade=true, score=65}
Patient{id=4, nom='Fatima', dateNaissance=2025-06-02 15:16:43.656, malade=false, score=80}

Updating patient with ID 2:
Before update: Patient{id=2, nom='Ahmed', dateNaissance=2025-06-02 15:16:43.656, malade=false, score=50}
After update: Patient{id=2, nom='Ahmed', dateNaissance=2025-06-02 15:16:43.656, malade=true, score=95}

Deleting patient with ID 3:

Final list of patients after deletion:
Patient{id=1, nom='Mohamed', dateNaissance=2025-06-02 15:16:43.656, malade=true, score=75}
Patient{id=2, nom='Ahmed', dateNaissance=2025-06-02 15:16:43.656, malade=true, score=95}
Patient{id=4, nom='Fatima', dateNaissance=2025-06-02 15:16:43.656, malade=false, score=80}

========== CRUD OPERATIONS COMPLETED ==========
```

### Explanation of CRUD Operations:

#### 1. **CREATE Operation**
- **Purpose**: Add new patient records to the database
- **Implementation**: Creates 4 sample patients with different attributes
- **Result**: 4 patients stored in H2 database with auto-generated IDs

#### 2. **READ Operations**
- **Read All**: `patientRepository.findAll()` retrieves all patients
- **Read by ID**: `patientRepository.findById(1L)` finds a specific patient
- **Custom Search**: `patientRepository.findByNomContains("a")` finds patients whose names contain "a"
- **Result**: Demonstrates different ways to query patient data

#### 3. **UPDATE Operation**
- **Purpose**: Modify existing patient information
- **Implementation**: Updates patient ID 2's `malade` status from `false` to `true` and `score` from `50` to `95`
- **Result**: Shows before and after states of the updated patient

#### 4. **DELETE Operation**
- **Purpose**: Remove patient records from the database
- **Implementation**: Deletes patient with ID 3 (Sara)
- **Result**: Final list shows only 3 remaining patients

### Technical Details:

- **Entity**: `Patient` class with JPA annotations
- **Repository**: `PatientRepository` extends `JpaRepository<Patient, Long>`
- **Database**: H2 in-memory database for development
- **Auto-execution**: `CommandLineRunner` runs CRUD tests on application startup

## Project Structure

The application follows a standard Spring Boot project structure with domain-driven design principles:

```
src/main/java/
├── com/bellagnech/patientmanagement/
│   └── PatientmanagementApplication.java       # Main Spring Boot application
└── com/example/patientmanagement/
    ├── PatientTestingCommandLineRunner.java    # CRUD operations demo
    ├── entities/
    │   └── Patient.java                         # JPA Entity
    └── repositories/        └── PatientRepository.java               # Data Access Layer
```

## Development Notes

### Package Structure Explanation

The project uses two main packages due to the development progression:

1. **`com.bellagnech.patientmanagement`**: Contains the main Spring Boot application
2. **`com.example.patientmanagement`**: Contains business logic (entities, repositories, services)

The main application class uses annotations to scan both packages:
```java
@SpringBootApplication(scanBasePackages = {"com.bellagnech.patientmanagement", "com.example.patientmanagement"})
@EnableJpaRepositories(basePackages = "com.example.patientmanagement.repositories")
@EntityScan(basePackages = "com.example.patientmanagement.entities")
```

### Key Implementation Highlights

- **JPA Annotations**: `@Entity`, `@Id`, `@GeneratedValue` for database mapping
- **Repository Pattern**: Spring Data JPA reduces boilerplate code
- **Dependency Injection**: `@Autowired` for automatic bean wiring
- **CommandLineRunner**: Automatic execution of demo code on startup

## Next Steps (TODO Items 7-8)

This project covers TODO items 1-6. The remaining items include:

7. **Migrate to MySQL**: Already prepared - just update `application.properties`
8. **Extend with additional entities**: Add Doctor, Appointment, Consultation, Users, and Roles entities

### Planned Extensions

- **Doctor Entity**: Medical professionals management
- **Appointment Entity**: Patient-doctor appointment scheduling
- **Consultation Entity**: Medical consultation records
- **User Management**: Authentication and authorization
- **Role-based Access**: Different user types and permissions

## Contributing

This is a learning project demonstrating Spring Boot fundamentals including:
- JPA entity mapping
- Repository pattern implementation
- Database configuration
- CRUD operations
- Custom query methods
