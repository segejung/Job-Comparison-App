# Project Plan

**Author**: Team 118

## 1 Introduction

A job comparison application that allows you to catalog and compare job offers with a user's current job. These job entries are all ranked based on a number of factors including pay, benefits, etc.

## 2 Process Description

### Creating the Main Menu root Page / UIs

The main menu hosts the application's functionalities and is the landing page for the application. The user is brought to this activity upon start up and has the option to return at any point when accessing other functionalities or activities.

This activity does not need any inputs as it is the top level of the design.

The exit criteria of the activity is navigating to a different function, and as a result, a new UI. Seeing a new UI is the indication that the output of the activity was successful.

### Creating the Job Entry Page

Upon selection of the Job Entry, the user will be prompted with a new job entry prompt. This prompt will contain fields for each part of a job that need to be filled in by the user. (more)

The activity needs the user to input valid values for all of the required inputs for a job class. These fields are the following: Title, Company, Location (entered as city and state), Cost of living in the location, Yearly salary, Yearly bonus, Retirement benefits, Relocation stipend, and Training and development fund.

The user would be given 3 options on this activity: save, cancel, and return to main menu. The activity would produce an output of a confirmation message when the process has been completed successfully and one of these options is selected. 


### Job Scorer

Ranking jobs is a functionality required of the application. Job entries (entered through the aptly named job entry page) are given a score based on their qualities, or fields that they are given in order to give them a ranking. The ranking is planned to be done by the Job Scorer; a class that takes all the job offers and current jobs, and then ranks them based on the output of an equation. 

Entrance criteria for this activity is having the job entry page created. This activity needs all the jobs cataloged as input parameters in order to perform the ranking

Exit criteria for this activity would be the visual of the rankings of the jobs. Seeing the list of jobs ranked based on the function would be the indication that the activity performed successfully.


### Integrating the SQLite into the BackEnd

This activity has the purpose of establishing an application where its state can persist between runs. ()

Entrance criteria for this activity would be to have an application that has multiple states. This would allows for the application to start in a different state than the main menu.

Exit criteria for this activity would be restarting the application and seeing the application start up into a different state than the starting one.


### Designing the Test Suite for Integration and Unit Tests

For the purposes of testing and validating the application, a testing suite will be implemented. (more)

For the entrance criteria of this activity, the application must be able to compile and run successfully. The application must also be able to (more)

The activity of the test suite should have the exit criteria of completing the tests and resulting in a successful test. 



## 3 Team

> Names of our Team members:
- Daniel Lee
- Sege Jung
- William Cronin
- Nelson Raphael

> Roles held by our Team members:
- Project Manager, responsible for submitting the commit id for the teams work as well as dividing up the roles
- UX/UI designer & FrontEnd Engineer, responsible for designing the layouts for the application interfaces
- BackEnd Engineer, responsible for completing the SQLite integration for persistance as well architecting as any internal logic for managing data types and information
- Software Test Engineer, responsible for designing and implementing the test suite for doing input checks, described constraints from the design requirements, and functionality testing

> Table of Responsibilities:
- Realistically given the requirements of the project and the size of our team some of our roles must overlap in order for integration, testing, and design to work together. Therefore, we each wear two hats.
  | Daniel L.         | Sege J.                             | William C.         | Nelson R.     |
  | :---------        | :-------:                          | :----------:       | ----------:    |
  | Software Test Eng.| BackEnd Eng./                       | BackEnd Eng.       | UX/UI designer & FrontEnd Engineer|
  | /Project Mgr.     | UX/UI designer & FrontEnd Engineer  | /Software Test Eng.| /BackEnd Eng. |
