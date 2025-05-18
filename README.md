# Patient Management System

A Spring Boot application for managing patient records. This application allows healthcare professionals to create, retrieve, update, and delete patient information efficiently.

## Features

- Store and manage patient records
- Search patients by name
- Perform basic CRUD operations on patient data
- Support for both H2 in-memory database and MySQL

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

## Project Structure

The application follows a standard Spring Boot project structure with domain-driven design principles.
