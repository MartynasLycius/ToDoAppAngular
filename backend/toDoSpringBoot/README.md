Project Development Documentation
===============================

Introduction
------------
This document provides an overview and documentation for the development of the ToDo application using Spring Boot. It includes information about the project structure, dependencies, and other important details.

Project Overview
----------------
The ToDo application is a simple task management system built using Spring Boot. It allows users to create, update, and delete tasks. The application uses a PostgreSQL database for storing task data and Hibernate for object-relational mapping. Flyway is used for database migration, and the application is tested using JUnit.

Project Structure
-----------------
The project follows the standard Maven directory structure for a Spring Boot application. The main components of the project structure are as follows:

- `src/main/java`: Contains the Java source code of the application.
- `src/main/resources`: Contains configuration files and static resources.
- `src/test/java`: Contains the test classes for unit testing.

Key Dependencies
----------------
The project has the following key dependencies:

- `spring-boot-starter-web`: Provides the necessary libraries for building web applications using Spring Boot.
- `spring-boot-starter-data-jpa`: Includes libraries for working with relational databases using JPA.
- `hibernate-validator`: Provides validation support for the Hibernate framework.
- `flyway-core`: Enables database migration using Flyway.
- `spring-boot-devtools`: Provides development-time features like automatic restarts and live reloading.
- `postgresql`: The PostgreSQL JDBC driver for connecting to the PostgreSQL database.
- `spring-boot-starter-test`: Includes libraries for testing Spring Boot applications.
- `lombok`: Simplifies Java code by generating boilerplate code automatically.
- `junit`: A popular testing framework for Java.

Configuration
-------------
The project uses the following configuration:

- Java version: 17
- Parent project: `spring-boot-starter-parent` version 3.1.0
- Group ID: com.anjarul
- Artifact ID: toDo
- Version: 0.0.1-SNAPSHOT

Database Configuration
----------------------
The application requires a PostgreSQL database for storing task data. The database configuration details can be found in the `application.properties` file located in the `src/main/resources` directory. Ensure that the following properties are properly configured:

```
# DATASOURCE
spring.datasource.url=jdbc:postgresql://localhost:5432/anjarul
spring.datasource.username=postgres
spring.datasource.password=
```

- `spring.datasource.url`: Specifies the URL of the PostgreSQL database. Update it to match your database connection details.
- `spring.datasource.username`: Specifies the username to access the database.
- `spring.datasource.password`: Specifies the password corresponding to the username.

Build and Deployment
--------------------
To build and deploy the ToDo application, follow these steps:

1. Ensure that you have Java 17 installed on your system.
2. Set up a PostgreSQL database and configure the database connection details in the `application.properties` file.
3. Run the database migration using Flyway to create the necessary tables.
4. Build the project using Maven: `mvn clean install`.
5. Run the application: `java -jar target/toDo-0.0.1-SNAPSHOT.jar`.

Testing
-------
The project includes unit tests for testing various components of the application. The tests are written using JUnit and can be executed using the `mvn test` command.

Conclusion
----------
This documentation provides an overview of the ToDo application developed using Spring Boot. It covers the project structure, dependencies, configuration, including the PostgreSQL database configuration, build, deployment, and testing. You can refer to this documentation for understanding the project and its development aspects.