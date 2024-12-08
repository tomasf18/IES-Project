---
id: meeting_11
title: Meeting 11 - November 23, 2024
sidebar_label: Meeting 11 - November 23, 2024
sidebar_position: 11
---

# Meeting 11 - November 23, 2024

## Meeting Date
**November 23, 2024**

## Attendees
- Danilo Micael Gregório Silva
- Pedro Miguel Azevedo Pinto
- João Pedro Azevedo Pinto
- Tomás Santos Fernandes

## Agenda
- Overview of completed work for current iteration
- Review of outstanding tasks for completion in the current iteration
- Discussion on new issues for the current iteration (4)
- Task assignments for team members

## Key Discussion Points
- **Overview of Completed Work for Current Iteration**: The team reviewed the tasks completed so far, highlighting areas of progress and any updates to the timeline or project goals.

- **Outstanding Tasks for Completion in the Current Iteration**: Discussion focused on tasks that are still pending and require completion by the end of the current iteration. Members agreed to prioritize these tasks in order to stay on track.

- **Issues for the Current Iteration**: The team discussed the upcoming requirements and defined the issues to be created and tackled in the next iteration:
  - **Data Ingestion Module**: Build a data ingestion pipeline to handle incoming data efficiently.
  - **Logger Sink - Kafka Consumer**: Develop a Kafka consumer to process and log data.
  - **Elasticsearch Configuration Setup**: Configure Elasticsearch to manage and index data effectively, also configuring the connection with Nginx.
  - **Data Generator Kafka Producer**: Create a Kafka producer to generate synthetic data for testing.
  - **Data Generator Containerization**: Set up containerization for the data generator to streamline deployment.
  - **HTTPs Configuration with CertBot**: Implement secure HTTPs configuration using CertBot for SSL certificates.
  - **Nginx Configuration**: Configure Nginx to act as a reverse proxy for the services.
  - **Setup Queries in Kibana**: Define and test Kibana queries to support data visualization and analysis of endpoints accesses.

- **Bugs/Goals discussed**:
  - **Use of Websocket**: The team discussed the use of Websocket for real-time data visualization and monitoring. This feature will be implemented in the current iteration.
  - **Use of Kafka**: The team discussed the use of Kafka for data processing and logging. The implementation of Kafka will be a key feature in the current iteration.

## Action Items
- **Danilo** will focus on:
  - Developing frontend components and pages.
  - Implementing Websocket for real-time data visualization.
  - Websocket integration with Kafka.

- **Pedro** will focus on:
  - Configuration of Elasticsearch and Kibana.
  - Configuration of Nginx for reverse proxy.
  - Implementing HTTPs configuration with CertBot.
  - Integration of Elasticsearch with Nginx and Kafka.

- **João** will focus on:
  - Sensors data generation and data ingestion pipeline.
  - Implementing Kafka for data processing.
  - Consumer in Spring Boot for Kafka.

- **Tomás** is responsible for:
  - Deploying the final application to the VM server.
  - Creating endpoints with Elasticsearch and Kibana.
  - Helping with the frontend development tasks.

## Next Steps
- Continue working on the tasks assigned for the current iteration.
- Address any blockers or issues that arise promptly.

---

**Note**: This meeting provided a structured review of the current iteration’s progress, outlined remaining tasks, and set the groundwork for the next iteration by assigning new issues. Each member is aligned on their roles to ensure continuous progress in the project.