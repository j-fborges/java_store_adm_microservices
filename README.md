## 📋 Overview

**java_store_adm_microservices** is a **Portfolio Project Monorepo** featuring a distributed microservices architecture built with Java Spring Boot. This project demonstrates enterprise-grade system design for managing an online store, implementing a scalable configuration server and specialized microservices for customer, product, and order management.

## 🏗️ Project Architecture

### Repository Structure

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

## 🔧 Core Microservices

### 1. **Config Server**
- **Purpose**: Centralized configuration management for all microservices
- **Technology Stack**:
  - Spring Boot 3.5.10
  - Spring Cloud Config Server 2025.0.1
  - Java 17
- **Key Dependencies**:
  - Spring Boot Starter Web
  - Spring Cloud Config Server
  - Lombok (for reduced boilerplate)
  - Spring Boot DevTools (for development)

**Responsibility**: Manages and distributes application configurations to all dependent microservices, enabling dynamic configuration updates without service restarts.

---

### 2. **Customer Service**
- **Purpose**: Manages customer profiles, registration, and customer-related operations
- **Technology Stack**:
  - Spring Boot 3.5.10
  - Spring Cloud 2025.0.1
  - MongoDB (NoSQL database)
  - Java 17
- **Key Dependencies**:
  - Spring Boot Starter Data MongoDB
  - Spring Boot Starter Validation
  - Spring Boot Starter Web
  - Spring Cloud Starter Config (connects to Config Server)
  - Spring Cloud Starter OpenFeign (inter-service communication)
  - SpringDoc OpenAPI 2.8.13 (API documentation)
  - Lombok

**Responsibilities**:
- CRUD operations for customer data
- Customer validation and management
- Integration with other microservices via OpenFeign
- Provides REST APIs with Swagger/OpenAPI documentation

---

### 3. **Product Service**
- **Purpose**: Manages product catalog, inventory, and product information
- **Technology Stack**:
  - Spring Boot 3.5.10
  - Spring Cloud 2025.0.1
  - MongoDB (NoSQL database)
  - Java 17
- **Key Dependencies**:
  - Spring Boot Starter Data MongoDB
  - Spring Boot Starter Validation
  - Spring Boot Starter Web
  - Spring Cloud Starter Config
  - Spring Cloud Starter OpenFeign
  - SpringDoc OpenAPI 2.8.13
  - Lombok

**Responsibilities**:
- Product catalog management
- Inventory tracking
- Product information storage and retrieval
- REST API endpoints for product queries
- Inter-service communication with other microservices

---

### 4. **Order Service** (Sale Service)
- **Purpose**: Manages customer orders and sales transactions
- **Technology Stack**:
  - Spring Boot 3.5.10
  - Spring Cloud 2025.0.1
  - MongoDB (NoSQL database)
  - Java 17
- **Key Dependencies**:
  - Spring Boot Starter Data MongoDB
  - Spring Boot Starter Validation
  - Spring Boot Starter Web
  - Spring Cloud Starter Config
  - Spring Cloud Starter OpenFeign
  - SpringDoc OpenAPI 2.8.13
  - Lombok

**Responsibilities**:
- Order creation and management
- Sales transaction processing
- Order status tracking
- Integration with Customer and Product services
- REST API endpoints for order operations

---

## 🛠️ Technology Stack

### Framework & Runtime
- **Java Version**: 17
- **Spring Boot**: 3.5.10
- **Spring Cloud**: 2025.0.1

### Data Management
- **Database**: MongoDB (NoSQL - all business services)
- **Data Access**: Spring Data MongoDB

### Communication & Integration
- **Service-to-Service**: Spring Cloud OpenFeign (declarative HTTP client)
- **Configuration**: Spring Cloud Config Client
- **Bootstrap**: Spring Cloud Bootstrap (early initialization)

### Development & Documentation
- **Build Tool**: Maven (pom.xml)
- **API Documentation**: SpringDoc OpenAPI 2.8.13 (Swagger UI)
- **Code Generation**: Lombok (annotation processing)
- **Development Tools**: Spring Boot DevTools
- **Testing**: Spring Boot Starter Test

### Build Configuration
- **Maven Compiler**: Java 17 target
- **Spring Boot Maven Plugin**: Package and run applications
- **Lombok Annotation Processing**: Compile-time code generation

## ✨ Key Features

✅ **Microservices Architecture** - Independently deployable services with clear responsibility separation  
✅ **Centralized Configuration** - Config Server for unified application management  
✅ **Service Communication** - OpenFeign for seamless inter-service HTTP calls  
✅ **API Documentation** - Built-in Swagger UI via SpringDoc OpenAPI  
✅ **Data Validation** - Spring Validation for input integrity  
✅ **MongoDB Integration** - Flexible document-based data storage  
✅ **Cloud-Native Design** - Spring Cloud components for scalability  
✅ **Development Experience** - DevTools for hot reload and faster development  

## 📦 Monorepo Benefits

This monorepo structure provides:
- **Unified Versioning**: All services versioned together (0.0.1-SNAPSHOT)
- **Consistent Dependencies**: Shared Spring Boot parent version (3.5.10)
- **Simplified Management**: Single repository for the entire system
- **Code Reusability**: Shared configurations and utilities
- **Coordinated Deployment**: Deploy related services together

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

## 📝 Project Details

- **Repository**: `j-fborges/java_store_adm_microservices`
- **Version**: 0.0.1-SNAPSHOT
- **Package**: br.com.j_fborges
- **License**: To be defined
- **Author**: j-fborges

## 🎯 Use Cases

This project is ideal for:
- Learning microservices architecture patterns
- Understanding Spring Cloud ecosystem
- Building scalable e-commerce platforms
- Portfolio demonstration of distributed systems knowledge
- Foundation for online store management systems

---

**Last Updated**: March 30, 2026
