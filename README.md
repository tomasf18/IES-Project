# STS - Smart Training System

### `Grade: 19.7/20`

## Project Abstract

**Smart Training System** is a web-based platform designed to assist coaches in monitoring and analyzing player performance in real-time. The application provides key physical statistics, including heart rate, body temperature, and respiratory rate. Utilizing distributed data streams from sensors, the platform enhances real-time decision-making during training and matches.

**Key Features:**

- **Real-Time Data Integration**: Seamlessly integrates data from various sensors to provide up-to-the-minute statistics.
- **Role-Based Access Control**: Ensures that different users have appropriate access levels based on their roles.
- **Comprehensive REST API**: Facilitates easy integration with the web portal and future mobile applications, allowing for scalability and flexibility.

This system empowers coaches with actionable insights, enabling them to make informed decisions that improve team performance and player development.
For detailed information, please refer to the **[full documentation of the project](#3-accessing-the-full-documentation)** or to the **[Project Report](docs/reports/IESProjectSpecificationReport.pdf)**.

[[click] High resolution architecture diagram](/docs/resources/architecture/2.0.1/architecture.2.0.1.svg)  
![System Architecture Diagram](/docs/resources/architecture/2.0.1/architecture.2.0.1.jpg)

---

## Methodologies and Practices

This project had as its main goal to be developed using widely adopted software engineering methodologies to ensure enterprise-level quality and collaboration:

- **Agile Development**:  
  The team followed an agile methodology, using iterative sprints to plan, develop, and deliver increments of the system.  
  - **Backlog Management**: User stories were prioritized and tracked using GitHub Projects with Scrum boards.  
  - **Sprint Planning and Reviews**: Each sprint was associated with specific deliverables, documented through iteration backlogs and meeting minutes.

- **Feature-Branching Workflow**:  
  Development was managed using the GitHub Flow. Feature branches were linked to user stories, and changes were integrated through pull requests with code reviews.

- **Container-Based Deployment**:  
  Docker was used to containerize services, ensuring modularity and ease of deployment. The architecture followed a multi-layer approach, with separate containers for data ingestion, processing, and the web interface.

- **REST API**:  
  A RESTful API was designed and documented using OpenAPI/Swagger, allowing seamless interaction between system components and external integrations.


### Repository Statistics

As stated, this project was developed in a collaborative environment, using GitHub as the primary platform for version control, issue tracking, and collaboration. Below are some key statistics from the original private repository:

- **Commits**: 610+
- **Branches**: 160
- **Releases**: 9
- **Contributors**: 4
- **Issues**: 270+
- **Pull Requests**: 190+

These statistics highlight the scale of the project and the collaborative effort that went into its development.

#### Visual Overview

**Main Repository Page**  
![MainPage](/docs/resources/readme/MainPage.png)

**Issues Management**  
![Issues](/docs/resources/readme/Issues.png)

**Pull Request Workflow**  
![PullRequests](/docs/resources/readme/PullRequests.png)

---

## Getting Started

### 1. Running the Project

To start the Smart Training System application locally, follow these steps:

1. Navigate to the `src/` directory:
```bash
cd src/
```
2. Build and run the Docker containers:
```bash
docker compose up --build
```
3. Access the application in your browser at:
```
http://localhost
```
4. To stop the application, press `Ctrl + C` in the terminal and run:
```bash
docker compose down
```

---

### 2. Accessing the REST API Documentation (Swagger)

The REST API documentation is generated using Swagger and provides detailed information about the available endpoints and request/response formats. To access the Swagger UI:

1. On [compose file](src/compose.yml), you need to open the port `8080` for the `backend` service, so that the Swagger UI can be accessed. It is closed by default, for security reasons, since we are using Nginx as a reverse proxy.
This can be made by uncommenting the following lines (129-130):
```yaml
    # ports:
    #   - "${BACKEND_LOCAL_PORT}:${BACKEND_CONTAINER_PORT}"
```

2. Rebuild and run the Docker containers:
```bash
docker compose down
docker compose up --build
```

3. Access the Swagger UI in your browser at:
```
http://localhost:8080/swagger-ui/index.html
```

---

### 3. Generating Sensor Data

The system relies on sensor data to simulate real-time performance metrics. To generate and send this data to Kafka for consumption by the application:

1. Navigate to the `data-generation` directory:
```bash
cd src/data-generation/
```
2. Activate the Poetry environment:
```bash
poetry shell
```
3. Install the necessary dependencies:
```bash
poetry install
```
4. Navigate to the `data_generation/` folder:
```bash
cd data_generation/
```
5. Run the `producer.py` script to generate and send sensor data. Replace `<sensorID_1>` and `<sensorID_2>` with the desired sensor IDs:
```bash
python3 producer.py <sensorID_1> <sensorID_2>
```
6. To stop the data generation, press `Ctrl + C` in the terminal.

---

### 4. Accessing the Full Documentation

The project includes comprehensive documentation hosted locally. To view the documentation:

1. Navigate to the documentation directory:
```bash
cd docs/smart-training-system-documentation/
```
2. Install the required dependencies:
```bash
npm install
```
3. Start the documentation server:
```bash
npm start
```
4. Access the documentation in your browser at:
```
http://localhost:4000
```
5. To stop the documentation server, press `Ctrl + C` in the terminal.

---

## Bookmarks

**GitHub Repository (private | source of the project)**  
- [STS - Smart Training System Repository](https://github.com/detiuaveiro/ies-24-25-group-project-201)

**Mockup**  
- [STS - Smart Training System Prototype](https://www.figma.com/design/BzGZhhuvqFLRJjauDoeD33/STS---Smart-Training-System-Prototype?node-id=320-2988&node-type=canvas&t=4w5HPUCX1WpmwqUH-0)

---

## Our Team

| <div align="center"><a href="https://github.com/tomasf18"><img src="https://avatars.githubusercontent.com/u/122024767?v=4" width="150px;" alt="Tomás Santos"/></a><br/><strong>Tomás Santos</strong><br/>112981<br/><hr/>DevOps</div> | <div align="center"><a href="https://github.com/DaniloMicael"><img src="https://avatars.githubusercontent.com/u/115811245?v=4" width="150px;" alt="Danilo Silva"/></a><br/><strong>Danilo Silva</strong><br/>113384<br/><hr/>Team Manager</div> | <div align="center"><a href="https://github.com/pedropintoo"><img src="https://avatars.githubusercontent.com/u/120741472?v=4" width="150px;" alt="Pedro Pinto"/></a><br/><strong>Pedro Pinto</strong><br/>115304<br/><hr/>Architect</div> | <div align="center"><a href="https://github.com/jpapinto"><img src="https://avatars.githubusercontent.com/u/81636006?v=4" width="150px;" alt="João Pinto"/></a><br/><strong>João Pinto</strong><br/>104384<br/><hr/>Product Owner</div> |
| --- | --- | --- | --- |
