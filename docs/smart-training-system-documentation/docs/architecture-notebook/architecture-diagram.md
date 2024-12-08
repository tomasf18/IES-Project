---
id: architecture-diagram
title: Architectural Diagram
sidebar_label: Architectural Diagram
---

## System Architecture Diagram

Below is a high-level diagram illustrating the system architecture of the **Smart Training System**, showing how the various components and technologies are integrated to form a cohesive and scalable platform.

![System Architecture Diagram](/img/architecture_detailed.0.2.1.svg)

This architecture represents a **modern**, **scalable web application** using a **decoupled services** approach and **Data-driven design**. The architecture is divided into multiple layers to ensure modularity, scalability, and maintainability:

 - **Frontend**: The user interface is built using **ReactJS**, a component-based JavaScript framework, enhanced with **Tailwind CSS** for responsive and customizable styling. **Vite** serves as the development build tool, providing fast reloads and optimized production builds. The frontend communicates with the backend via **RESTful APIs**.

 - **Backend**: The backend is developed using **Spring Boot**, which handles API requests through **Spring Web (REST API)** and manages the business logic in the **Service Layer**. The backend processes incoming data, connects to databases, and integrates with **Kafka** for asynchronous message handling.

 - **Data Layer**: Data is stored and managed in two main databases: **PostgreSQL** for relational data (e.g., user information) and **Timescale DB** (integrated in PostgreSQL) for efficiently handling time-series data (e.g., sensor readings). **Spring Data JPA** is used for seamless database integration and query handling.

 - **Message Queuing**: **Kafka** serves as a message broker, handling asynchronous tasks such as processing sensor data. It decouples the data sources from the backend, ensuring that high volumes of incoming data can be processed efficiently without overloading the system.  

 - **Nginx**: As the **reverse proxy, Nginx** handles all incoming requests, serving static files (like the React frontend) and forwarding API requests to the backend.

 - **Monitoring and Logging**: The **EK Stack** (Elasticsearch, and Kibana) is used for **real-time monitoring**. This setup allows tracking **system health** and **maintaining performance** visibility in a production environment.

 - **Sensors**: The system ingests data from virtual sensors designed to simulate real-world data, feeding it into Kafka for processing. This layer generates data-driven insights, which are then made accessible to users through the frontend.

 - **Docker**: The entire application is containerized using Docker, ensuring portability, ease of deployment, and scalability across different environments.

This architecture efficiently separates concerns, supports high performance, and allows for future scalability, making it ideal for applications that need real-time data processing, analytics, and a responsive user interface.
