---
id: meeting_4
title: Meeting 4 - October 17, 2024
sidebar_label: Meeting 4 - October 17, 2024
---

# Meeting 4 - October 17, 2024

## Meeting Date
**October 17, 2024**

## Attendees
- Danilo Micael Gregório Silva
- Pedro Miguel Azevedo Pinto
- João Pedro Azevedo Pinto
- Tomás Santos Fernandes

## Agenda
- Prepare the sprints deliveries of iteractions 1.1 and 1.2.
- Brainstorm on what needs to be done for the next sprint.

## Key Discussion Points
- Sprint delivery (17/10)
    - Prepare all the work already done and organize the apresentation.
- Preparation of next sprint
    - Update report
        - As the initial idealization of the project was very complex for the given time to develop it, we decided to remove a few implementation ideas.
        - Make the interaction between models in report
        - Rewrite Information Perspective.
        - Merge report to Docussaurus documentation.
    - Discussion on the possibility of using Swagger
    - Define the REST API (endpoints, etc.)

## Action Items
- **Danilo** is responsible for: 
  - Rewrite Information Perspective.
  - Merge report to Docussaurus documentation.
  - Define the REST API (endpoints, etc.).
  - Create the API documentation.

- **Pedro** will:       
    - Break down broader components (see diagram) into more specific explanations for the project.  
    - Make the interaction between models in report  
    - Specify each service, for example:  
        - A service that manages all player data, adding and removing players.  
        - Include a PlayerService to handle player-specific data.  
        - Include a StatsService for handling player statistics.  
        - Include a LiveService for managing live data during games.  
        - Consider whether the service managing past statistics should also handle current ones. If they are separated, will this lead to redundant code?  
        - Break the system into microservices. If a service is removed from the project, it should be functional as a standalone component in another project.  
        - Etc.  

  - Define the REST API (endpoints, etc.).  
  - Create the API documentation.  

- **João** : 
  - Add acceptance criteria to the user stories(GitHub).
  - Define the REST API (endpoints, etc.).
  - Create the API documentation.

- **Tomás** will: 
  - Deploy increment (in containers) at the server environment.
  - Define the REST API (endpoints, etc.).
  - Create the API documentation.

## Next Steps
- Each member should complete every task assigned.

---

**Note**: This meeting focused on preparing the sprint of iteration 1.2. We also defined and destributed some of the tasks for the next sprint.