# E-Commerce REST API

This is a backend REST API for an e-commerce application built using Spring Boot. It provides features like user authentication using JWT, product management, cart operations, and order processing.

## Features
- User authentication and authorization (JWT)
- Product management
- Cart management
- Order placement and tracking
- Secure APIs with Spring Security
- DTO-based clean API responses

## Tech Stack
- Java
- Spring Boot
- Spring Security + JWT
- Hibernate / JPA
- Maven
- MySQL (or your DB)

## Project Structure
- controller/ - Handles API requests
- service/ - Business logic
- repository/ - Database access layer
- entity/ - JPA entities
- dto/ - Data Transfer Objects
- security/ - JWT and security configuration

## How to Run
1. Clone the repository
2. Configure database and JWT settings in `application.properties`
3. Run `EcommerceApplication.java`
4. The API will be available at `http://localhost:8080`

## Example Endpoints
- POST /auth/login
- POST /auth/register
- GET /products
- POST /cart/add
- POST /orders/checkout
