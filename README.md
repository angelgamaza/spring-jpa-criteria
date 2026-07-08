# Spring Data JPA - Criteria Example

A practical Spring Boot example that demonstrates how to build a REST API with dynamic filtering and pagination using the JPA Criteria API.

![Java](https://img.shields.io/badge/Java-21-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.4.3-6DB33F?style=for-the-badge&logo=springboot&logoColor=white)
![Maven](https://img.shields.io/badge/Maven-%23C71A36?style=for-the-badge&logo=apachemaven&logoColor=white)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-4169E1?style=for-the-badge&logo=postgresql&logoColor=white)
![Flyway](https://img.shields.io/badge/Flyway-%23CC0200?style=for-the-badge&logo=flyway&logoColor=white)
![Docker](https://img.shields.io/badge/Docker-2496ED?style=for-the-badge&logo=docker&logoColor=white)
![Swagger](https://img.shields.io/badge/-Swagger-85EA2D?style=for-the-badge&logo=swagger&logoColor=black)

Spring JPA Criteria is a compact but complete sample application for searching MotoGP riders through a REST endpoint while keeping the implementation flexible and efficient.

It shows how to combine Criteria queries, pagination and entity relationships in a Spring Boot application.

## 📦 Why this Project Exists

- It provides a clear example of implementing dynamic search with the JPA Criteria API.
- It demonstrates how to build a paginated REST API over related entities such as riders and teams.
- It offers a simple local setup using Docker Compose and PostgreSQL.

## 🎯 Key Features

- Dynamic filtering for rider and team attributes.
- Paginated API responses with metadata.
- Criteria-based repository implementation with separate queries for IDs, count and data retrieval.
- Swagger documentation through Springdoc OpenAPI.
- Dockerized local environment for the application and database.

## 📂 Project Structure

The codebase is organized into a small set of functional layers that make the flow easy to follow:

- Controllers for exposing the REST API.
- Services for handling the business logic.
- Repository implementations for Criteria-based queries.
- DTOs, entities, mappers and exceptions for request and response mapping.

## 🚀 Getting Started

### Prerequisites

- Docker and Docker Compose.
- Java 21+.
- Maven 3.9.3+.
- A working internet connection to pull Docker images.

### Configuration

Application settings such as the server port, context path and datasource connection are defined in the application configuration file. The project expects the following environment variables for the database connection:

- `SPRING_DATASOURCE_HOST`
- `SPRING_DATASOURCE_PORT`
- `SPRING_DATASOURCE_DATABASE`
- `SPRING_DATASOURCE_USERNAME`
- `SPRING_DATASOURCE_PASSWORD`

The project also includes Springdoc OpenAPI support through the OpenAPI Maven dependency, which exposes interactive API documentation for the REST endpoints.

### Build the Application

```bash
mvn clean package
```

### Run the Application

You can start the full stack with Docker Compose:

```bash
docker-compose up --build
```

This command builds the Spring Boot application image and starts both the PostgreSQL container and the Spring Boot application.

Alternatively, you can run the application directly with Maven:

```bash
mvn spring-boot:run
```

Once the application is running, you can inspect the API at:

- http://localhost:8080/motogp/api/swagger-ui/index.html

## 🧠 Criteria Implementation

The repository implementation uses three separate queries, which is a common pattern for efficiently handling dynamic search with pagination.

Here is why each query is used:

1. **IDs Query**: The first query retrieves the distinct IDs of entities that match the given filter. By returning only IDs, it minimizes the amount of data transferred and processed, making it efficient even when the filter criteria are complex. These IDs are then used for pagination.
2. **Count Query**: The second query counts the total number of matching entities. This count is crucial for pagination metadata and is separated from the main query to keep the logic clear and efficient.
3. **Main Query**: The third query fetches the actual entities corresponding to the previously retrieved IDs. This query often uses an entity graph to load the necessary associations. Since it only retrieves data for the specific page, it avoids loading unnecessary data.

## 📚 API Reference

The service exposes a RESTful API at `/motogp/api/v1/rider` where you can search MotoGP riders.

### `POST /motogp/api/v1/rider`

Retrieves a page of MotoGP riders that matches the applied filters.

#### Query Parameters

- `page (integer, optional)`: The page number to retrieve. Defaults to `0`.
- `size (integer, optional)`: The page size for the query. Defaults to `10`.
- `order (string array, optional)`: Sort criteria for ordering results.

#### Request Body

The body of the request must be a JSON object with the following fields. All are optional.

```json
{
    "riderName": "string",
    "riderCountry": "string",
    "riderNumber": "integer",
    "riderChampionships": "integer",
    "teamName": "string",
    "teamConstructor": "string",
    "teamMotorcycle": "string",
    "teamCountry": "string"
}
```

Example body:

```json
{
    "teamConstructor": "Ducati"
}
```

#### Response

- `200 OK`: Returns a paginated collection of rider objects.

Example response:

```json
{
    "content": [
        {
            "id": 17,
            "name": "Álex Márquez",
            "country": "Spain",
            "number": 73,
            "championships": 0,
            "team": {
                "id": 8,
                "name": "Gresini Racing MotoGP",
                "constructor": "Ducati",
                "motorcycle": "Ducati Desmosedici GP24",
                "country": "Italy"
            }
        },
        {
            "id": 16,
            "name": "Fermín Aldeguer",
            "country": "Spain",
            "number": 54,
            "championships": 0,
            "team": {
                "id": 8,
                "name": "Gresini Racing MotoGP",
                "constructor": "Ducati",
                "motorcycle": "Ducati Desmosedici GP24",
                "country": "Italy"
            }
        }
    ],
    "page": {
        "size": 10,
        "number": 0,
        "totalElements": 6,
        "totalPages": 1
    }
}
```

### Usage Example

```bash
curl -X POST "http://localhost:8080/motogp/api/v1/rider?page=0&size=10&order=+name" -H "accept: application/json" -H "Content-Type: application/json" -d '{"teamConstructor": 
"Ducati"}'
```

## 👨‍💻 Author

Ángel Gamaza - angel.gamaza@gmail.com
