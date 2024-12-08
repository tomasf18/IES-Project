---
id: domain_model
title: Domain Model
sidebar_label: Domain Model
sidebar_position: 1
---

# Domain Model

The domain model represents the key concepts and relationships within the **Smart Training System**. It provides a high-level overview of the system's structure and the interactions between its components. 
- The application handles various entities such as Users, Teams, Sessions, Matches, and Sensor Data, each with their respective attributes and connections. These concepts are modeled using a logical representation of the domain in the form of ER diagram to showcase the data structure, relationships, and persistence layer. 
- The diagram below illustrates the logical data model. It highlights the integration of a relational database (PostgreSQL) for user, team, and session management, alongside a time-series database (TimeScaleDB) for handling sensor data. The relationships between the entities are explicitly defined, ensuring a clear understanding of the connections in the system. 

---

![Domain Model](/img/domain_model.png)