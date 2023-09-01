# Netflix Pathways Bootcamp Final Project - *Game Store*

Submitted by: **Crystal Chavez and Adrian Castro**

Project Description: ****

## Project Delegation

Crystal:
- Creating Github Repository
- Creating Github Project
- Invoice controller, repository, controller tests, and repository tests
- Console controller, repository, controller tests, and repository tests
- Adding validation annotations in models
- Fee controller
- Tax controller
- Invoice View Model
- Service Layer
- GraphQL
- OpenAPI Documentation

Adrian:
- Make Game, T-Shirt, Console, Invoice, Fee, and Tax models
- Game controller, repository, controller tests, and repository tests
- T-shirt controller, repository, controller tests, and repository tests
- Adding validation annotations in models
- Adding validation annotations to all controllers
- CustomErrorHandler and ControllerExceptionHandler for 422 and 404 errors
- README
- Recording JSR303 Tests

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
- [ ] Controller Advice returns 404 status codes where appropriate.
- [ ] Controller Advice handles all possible exceptions thrown by Controller, Repository, and Service Layer code.

Deployment:
- [x] Project code has been synced to a GitHub repository.
- [ ] Application has been deployed to AWS and is receiving requests and responding accordingly.
- [ ] Application database has been deployed to Amazon RDS and is receiving requests and responding accordingly.
- [ ] Project code has been deployed to a CircleCI CI/CD pipeline that includes a "build and test" job.

## Video Walkthrough

Here's a walkthrough of implemented required features, including JSR303 tests on insomnia:

👉🏿<img src='http://i.imgur.com/link/to/your/gif/file.gif' title='Video Walkthrough' width='' alt='Video Walkthrough' />

<!-- Replace this with whatever GIF tool you used! -->
GIF created with LICEcap


## Notes



## License

Copyright 2023 Crystal Chavez and Adrian Castro

Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at

> http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.
