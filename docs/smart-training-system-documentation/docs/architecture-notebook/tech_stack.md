---
id: tech_stack
title: Tech Stack
sidebar_label: Tech Stack
---

# Tech Stack

This section provides an overview of the technologies used in the **Smart Training System** and their purposes within the project. The architecture leverages modern technologies to ensure scalability, flexibility, and efficiency in handling real-time data and user interactions.

![System Architecture Diagram](/img/architecture.1.0.0.svg)

## Technology Stack and Purpose

### Nginx
- **Purpose**: Reverse Proxy & Load Balancing
  - SSL/TLS certificates (e.g., via certbot for certificate management)
  - Load Balancing
  - Caching
  - Authentication

### Spring (Spring Boot)
- **Purpose**: API & Business Logic Layer
  - Integration with Databases (JPA)
  - Message Handling
  - Integration with Kafka (Consumer)

### Kafka
- **Purpose**: Asynchronous Task Processing
  - Message Brokering
  - Event-Driven Architecture

### PostgreSQL
- **Purpose**: Relational Data Storage
  - Advanced Querying
  - Integration with Spring

### Timescale DB
- **Purpose**: Time-Series Data Storage
  - Efficient Time-Series Querying
  - Scalability & Retention Policies
  - Seamless PostgreSQL Integration

### Docker
- **Purpose**: Containerization & Microservices Architecture
  - Portability & Scalability
  - Efficient Resource Management


### EK Stack (Elasticsearch, Kibana)
- **Purpose**: Monitoring & Logging (Administrator)
  - Real-time Monitoring
  - Endpoints Analysis
  - Sensor Data Analysis

