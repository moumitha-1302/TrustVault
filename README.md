# TrustVault

TrustVault is a document management web application built with Java and Spring Boot.

## Features
- Login-protected application pages
- Document upload and management
- Document download and deletion
- PostgreSQL database integration
- HTML-based web interface

## Technology Stack
- Java
- Spring Boot
- Spring Security
- Spring Data JPA
- PostgreSQL
- Maven
- HTML

## Requirements
- Java Development Kit (JDK)
- PostgreSQL
- Git

## Configuration

Configure the database and login passwords using environment variables:

- `DB_PASSWORD` — PostgreSQL password
- `APP_LOGIN_PASSWORD` — application login password

Never publish actual passwords or private documents.

## Running the Project

1. Create a PostgreSQL database named `trustvault`.
2. Set the required environment variables.
3. Open a terminal in the project directory.
4. Run:

   `.\mvnw.cmd spring-boot:run`

5. Open `http://localhost:8080` in your browser.

## Project Status

TrustVault is a learning project and prototype. It requires further security testing and improvements before use with sensitive documents in a production environment.
