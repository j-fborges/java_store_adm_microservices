## 📋 Overview

**java_store_adm_microservices** is a **Portfolio Project Monorepo** featuring a distributed microservices architecture built with Java Spring Boot. This project demonstrates enterprise-grade system design for managing an online store, implementing a scalable configuration server and specialized microservices for customer, product, and order management.

## 🏗️ Project Architecture

### Repository Structure

> [!NOTE]
> **A monorepo was chosen to simplify versioning and present the project as a cohesive portfolio piece.**

```
java_store_adm_microservices/
├── apps/
│   └── api/
│       ├── config-server/          # Centralized configuration management
│       ├── customer-service/       # Customer management microservice
│       ├── order-service/          # Order/Sales management microservice
│       └── product-service/        # Product catalog management microservice
└── README.md
```
## 🛠️ Core Microservices & Technology Stack

All business microservices (customer, order, product) share a common technology foundation:

- **Java 17**
- **Spring Boot 3.5.10**
- **Spring Cloud 2025.0.1**
- **MongoDB (NoSQL)** with Spring Data MongoDB
- **Spring Cloud Config Client** – connects to the Config Server
- **Spring Cloud OpenFeign** – declarative HTTP client for inter-service communication
- **SpringDoc OpenAPI 2.8.13** – provides Swagger UI documentation
- **Spring Boot Validation** – input validation
- **Lombok** – reduces boilerplate code
- **Spring Boot DevTools** – development-time hot reload

The **Config Server** is a dedicated microservice that uses Spring Cloud Config Server to centralize configuration for all other services.

| Service | Purpose | Key Differentiators |
|---------|---------|---------------------|
| **Config Server** | Centralized configuration management | Spring Cloud Config Server only; no database |
| **Customer Service** | Manages customer profiles & registration | MongoDB persistence; REST API for customer CRUD |
| **Product Service** | Manages product catalog & inventory | MongoDB persistence; product search & stock |
| **Order Service** | Manages orders & sales transactions | MongoDB persistence; integrates with customer & product |

Each microservice exposes its own REST API and Swagger UI at `http://localhost:{port}/swagger-ui.html`.

---

## ✨ Key Features

✅ **Microservices Architecture** - Independently deployable services with clear responsibility separation  
✅ **Centralized Configuration** - Config Server for unified application management  
✅ **Service Communication** - OpenFeign for seamless inter-service HTTP calls  
✅ **API Documentation** - Built-in Swagger UI via SpringDoc OpenAPI  
✅ **Data Validation** - Spring Validation for input integrity  
✅ **MongoDB Integration** - Flexible document-based data storage  
✅ **Cloud-Native Design** - Spring Cloud components for scalability  
✅ **Development Experience** - DevTools for hot reload and faster development  


## 🚀 Getting Started

### Prerequisites
- Java 17 or higher
- Maven 3.6+
- MongoDB instance running

### Build & Run

**Build all services:**
```bash
mvn clean install
```

**Run individual service:**
```bash
cd apps/api/config-server
mvn spring-boot:run
```

**Access API Documentation:**
Each service exposes Swagger UI at `http://localhost:{port}/swagger-ui.html`
