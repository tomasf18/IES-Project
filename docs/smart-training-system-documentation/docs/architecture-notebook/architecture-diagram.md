---
id: architecture-diagram
title: Architectural Diagram
sidebar_label: Architectural Diagram
---

## System Architecture Diagram

Below is a high-level diagram illustrating the system architecture of the **Smart Training System**, showing how the various components and technologies are integrated to form a cohesive and scalable platform.

![System Architecture Diagram](/img/architecture.0.1.0.svg)

This architecture represents a modern, scalable web application using a microservices approach and Data-driven design. The architecture is divided into multiple layers to ensure modularity, scalability, and maintainability: 

 - **Frontend**: The user interface is built using React, a component-based JavaScript framework, enhanced with Tailwind CSS for responsive and customizable styling. Vite serves as the development build tool, providing fast reloads and optimized production builds. The frontend communicates with the backend via RESTful APIs. 

 - **Backend**: The backend is developed using Spring Boot, which handles API requests through Spring Web (REST API) and manages the business logic in the Service Layer. The backend processes incoming data, connects to databases, and integrates with RabbitMQ for asynchronous message handling. 

 - **Data Layer**: Data is stored and managed in two main databases: PostgreSQL for relational data (e.g., user information, transactional data) and Timescale DB (integrated in PostgreSQL) for efficiently handling time-series data (e.g., sensor readings). Spring Data JPA is used for seamless database integration and query handling. 

 - **Message Queuing**: RabbitMQ serves as a message broker, handling asynchronous tasks such as processing sensor data or competition results. It decouples the data sources from the backend, ensuring that high volumes of incoming data can be processed efficiently without overloading the system. 

 - **Nginx**: As the reverse proxy and load balancer, Nginx handles all incoming requests, serving static files (like the React frontend) and forwarding API requests to the backend. It also manages SSL certificates via Certbot for secure HTTPS communication. 

 - **Monitoring and Logging**: The ELK Stack (Elasticsearch, Logstash, and Kibana) is used for centralized logging, real-time monitoring, and log analysis. This setup allows tracking system health, debugging issues, and maintaining performance visibility in a production environment. 

 - **Cyber-Physical Integration**: The system ingests real-world data from physical sensors and competition data sources, feeding it into RabbitMQ for processing. This layer adds real-world interaction and data-driven insights, which can be displayed to users through the frontend. 

 - **Docker**: The entire application is containerized using Docker, ensuring portability, ease of deployment, and scalability across different environments. 

This architecture efficiently separates concerns, supports high performance, and allows for future scalability, making it ideal for applications that need real-time data processing, analytics, and a responsive user interface. 
