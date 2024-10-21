---
id: meeting_5
title: Meeting 5 - October 18, 2024
sidebar_label: Meeting 5 - October 18, 2024
---

# Meeting 5 - October 18, 2024

## Meeting Date
**October 18, 2024**

## Attendees
- Danilo Micael Gregório Silva
- Pedro Miguel Azevedo Pinto
- João Pedro Azevedo Pinto
- Tomás Santos Fernandes

## Agenda
- Review of the work already done.  
- Brainstorm of what needs to be done for the next release. 
- Distribution of tasks. 

## Key Discussion Points
- What is done: overview of the work already done.
- What is missing
    - Figma prototype: the team discussed the last details for the prototype (e.g.: interface for coach analysis of players data).
- Distribution of the remaining tasks (as follows). 

## Action Items
- **Danilo** is responsible for: 
  - Collaborate on the Figma prototype design.   
  - Rewrite Information Perspective.   
  - Cost/Priority attribution to User Stories.

- **Pedro** will:
  - Collaborate on the Figma prototype design  
  - Architecture:  
    - Break down broader components (see diagram) into more specific explanations for the project.   
    - Specify each service, for example:   
        - A service that manages all player data, adding and removing players.  
        - Include a PlayerService to handle player-specific data.  
        - Include a StatsService for handling player statistics.  
        - Include a LiveService for managing live data during games.  
        - Consider whether the service managing past statistics should also handle current ones. If they are separated, will this lead to redundant code?  
    - Break the system into microservices. If a service is removed from the project, it should be functional as a standalone component in another project. 
    - Make the interaction between models in report (sequence diagram)   

- **João** : 
  - Create issues on GitHub.
  - Add acceptance criteria to the user stories (GitHub).
  - Correct some specifications of features of the app on report/Docussaurus.
  - Collaborate on the Figma prototype design.

- **Tomás** will:  
  - Make the interaction between models in report (sequence diagram). 
  - Collaborate on the Figma prototype design.

## Next Steps
- Each team member will focus on the assigned tasks and finish them for review before October 21, 2024 (release deadline).

