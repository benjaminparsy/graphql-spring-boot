# GraphQL with Spring Boot

This project is a sample application to learn and explore the use of GraphQL with Spring Boot. It provides a basic implementation of GraphQL and demonstrates how to configure, develop, and test GraphQL APIs with Spring Boot.

## Prerequisites

- [Java 17+](https://adoptopenjdk.net/)
- [Maven 3.8+](https://maven.apache.org/)
- [Postman](https://www.postman.com/) or a GraphQL client

## Features

- Basic configuration of GraphQL in Spring Boot.
- Example model (e.g., `Book` and `Author`).
- GraphQL operations: `query`, `mutation` and `subscription`
- Unit tests for GraphQL queries and mutations.

## Installation

1. Clone the project:

   ```bash
   git clone https://github.com/benjaminparsy/graphql-spring-boot.git
   ```

2. Compile and run the application:

   ```bash
   mvn clean install
   mvn spring-boot:run
   ```

3. Access the GraphQL interface via :

- http://localhost:8080/graphiql for a user interface.

## Project structure

- src/main/java: Contains the main source code.
    - `user` : User feature
    - `book` : Book feature
    - `shared` : Shared component, service and model.
    - `configuration` : Manage configuration (beans, etc)

- src/main/resources` :
    - `schema.graphqls`: GraphQL schema containing types, queries, mutations and subscriptions.
    - `messages/error.properties`: Error messages 
