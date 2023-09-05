# Netflix Pathways Bootcamp Final Project - *Game Store*

Submitted by: **Crystal Chavez and Adrian Castro**

Project Description: **A simple back-end API (via REST and GraphQL) inventory management web service for a video game store, developed using agile techniques in a group setting of 2 learners. We were responsible for designing and documenting the API and implementing the controllers, service, layering, repository, Java data objects, and unit tests for the application based on the provided database structure.**

Tools Used: Java, RESTful Web Services, JPA, Spring Boot, Spring MVC, Spring Initializr, MockMVC, Docker, DBeaver, AWS, Amazon RDS, CircleCI

## Methodology 
- Managed our work using GitHub Project
- Estimated our work using story points
- Used test-driven approach (red/green/refactor)
- Used JUnit for unit and integration tests
- Included a service layer
- Unit test suit used mock objects where appropriate
- Used JSR303 for input validation

## User Stories
Users should be able to:
- Create, read, update, and delete game information.
- Search for games by studio, ESRB rating, and title.
- Create, read, update, and delete console information.
- Search for consoles by manufacturer.
- Create, read, update, and delete T-shirt information.
- Search for games by color and size.
- Purchase a specified quantity of products (games, consoles, T-shirts) and an invoice will be created that includes any taxes and processing fees.

## Project Delegation

Crystal:
- Creating Github Repository and Github Project
- Invoice controller, repository, controller tests, and repository tests
- Console controller, repository, controller tests, and repository tests
- Adding validation annotations in models
- Fee and Tax controller
- Invoice View Model
- Service Layer
- GraphQL
- OpenAPI Documentation
- AWS Deployment

Adrian:
- Make Game, T-Shirt, Console, Invoice, Fee, and Tax models
- Game controller, repository, controller tests, and repository tests
- T-shirt controller, repository, controller tests, and repository tests
- Adding validation annotations in models
- Adding validation annotations to all controllers
- CustomErrorHandler and ControllerExceptionHandler for 422 and 404 errors
- README

## Required Features

The following **required** functionality is completed:

<!-- 👉🏿👉🏿👉🏿 Make sure to check off completed functionality below -->
Game REST API:
- [x] **Code is clean and follows general patterns as presented in class.**
- [x] **The repository supports the following functionality: Create, Read, Read All, Update, Delete, By Studio, By ESRB, By Title**
- [x] **Repository test cases cover the following functionality: Create, Read, Read All, Update, Delete, By Studio, By ESRB, By Title**
- [x] **REST API endpoints and OpenAPI documentation are present for each of the following: Create, Read, Read All, Update, Delete, By Studio, By ESRB, By Title**

T-Shirt REST API:
- [x] **Code is clean and follows general patterns as presented in class.**
- [x] **The repository supports the following functionality:** Create, Read, Read All, Update, Delete, By Color, By Size
- [x] **Repository test cases cover the following functionality:** Create, Read, Read All, Update, Delete, By Studio, By ESRB, By Title
- [x] **REST API endpoints and OpenAPI documentation are present for each of the following:** Create, Read, Read All, Update, Delete, By Color, By Size

Console REST API:
- [x] **Code is clean and follows general patterns as presented in class.**
- [x] **The repository supports the following functionality:** Create, Read, Read All, Update, Delete, By Manufacturer
- [x] **Repository test cases cover the following functionality:** Create, Read, Read All, Update, Delete, By Manufacturer
- [x] **REST API endpoints and OpenAPI documentation are present for each of the following:** Create, Read, Read All, Update, Delete, By Manufacturer

Invoice REST API:
- [x] **Code is clean and follows general patterns as presented in class.**
- [x] **The repository supports the following functionality:** Create, Read, Read All, By Customer Name
- [x] **Repository test cases cover the following functionality:** Create, Read, Read All, By Customer Name
- [x] **REST API endpoints and OpenAPI documentation are present for each of the following:** Create, Read, Read All, By Customer Name
      
GraphQL API:
- [x] **Code is clean and follows general patterns as presented in class.**
- [x] **Get all Games, as well as by ID, Title, ESRB Rating, and Studio.**
- [x] **Get all Consoles, as well as by ID and Manufacturer.**

REST API Controller Advice and Validation:
- [x] Code is clean and follows general patterns as presented in class.
- [x] JSR 303 validations match database constraints and messages are clear and concise.
- [x] Implementation utilizes the ResponseEntity pattern presented in class.
- [x] Controller Advice returns 422 status codes where appropriate.
- [x] Controller Advice returns 404 status codes where appropriate.
- [x] Controller Advice handles all possible exceptions thrown by Controller, Repository, and Service Layer code.

Deployment:
- [x] Project code has been synced to a GitHub repository.
- [ ] Application has been deployed to AWS and is receiving requests and responding accordingly.
- [ ] Application database has been deployed to Amazon RDS and is receiving requests and responding accordingly.
- [ ] Project code has been deployed to a CircleCI CI/CD pipeline that includes a "build and test" job.

## Video Walkthrough

Here's a walkthrough of JSR303 tests for 404 errors, since there are no tests for it in the code:

<img src='https://github.com/crystalchavez99/Final-Project/blob/main/jsr303_walkthrough.gif' title='Video Walkthrough' width='' alt='Video Walkthrough' />

<!-- Replace this with whatever GIF tool you used! -->
GIF created with LICEcap


## Notes
One challenge we faced had to do with the delegation of responsibilities, as we originally had 3 members in our group. Our third group mate let us know that they were unable to complete their part of the project the day it was due, so we quickly had to split everything that was unaccounted for between us.

Another challenge we faced was getting the invoice API set up, as it involved two other models, controllers, and a service layer. There was a lot of debugging involved and was the biggest part of the project.

The last challenge we faced was getting the controller exception handler to account for both 422 and 404 errors, since having both would break all our tests. In the interest of saving time due to the first challenge above, we decided to only implement the 422 error handler.


## License

Copyright 2023 Crystal Chavez and Adrian Castro

Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at

> http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.
