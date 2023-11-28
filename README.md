# TASK MANAGER APP
Welcome to the Task Manager App!. A Simple Task manager App.

## Table of Contents
* Services
* Getting Started
  * Prerequisites
  * Running the application
* Running Test
* Swagger Documentation
* Assumptions


## Services

### Backend Service
The Generator Service is responsible for the APIs that the user interface relies on.


### User Interface
The User interface is used by the client to manage their daily task.


## Getting Started

### Prerequisite
- Java Development Kit [(JDK)](https://www.oracle.com/java/technologies/downloads/) 17 or higher
- Node.js: Make sure you have Node.js installed on your machine. You can download it from [nodejs.org](https://nodejs.org/en) preferably the LTS version .
- Apache Maven 3.6.0 or higher
- npm (Node Package Manager): This comes bundled with Node.js, so there's no need to install it separately
- [IntelliJ IDEA](https://www.jetbrains.com/idea/download/?section=mac) with the Spring Boot plugin or any other suitable IDE that can run spring boot
- Web Browser: For testing and running the User interface application, you'll need a modern web browser like Google Chrome, Mozilla Firefox, or Microsoft Edge.


### Running the application
- Clone the [repository](https://github.com/nnamdi16/task-manager-app)
- In the frontend folder, run the following commands below:
```bash 
cd frontend
npm install
 ```
- There is a .env.example file in the front end folder. Create your own copy and name it .env 
so that the nuxt application can access the environment variables.
- To run the entire application, run the command below in the root directory of the project based on you operating system (OS)

```bash
For mac/linux
./start.sh

For Windows
./start.bat
```
NB: By default the frontend runs on port 3000, the backend service runs on port 8081. You can modify these ports in the 
application.properties file of each of the services while for the frontend in the package.json file.



## Running Test
#### Spring Boot Application
To run the test for the spring boot application, run the command below:

```bash
mvn clean test
```

## Documentation
The REST endpoint for both the generator service and the validator services are documented using swagger.
The swagger documentation UI is seen below:
- [Backend Service Service - http://localhost:8081/swagger-ui/index.html](http://localhost:8081/swagger-ui/index.html)


## Assumptions
- User authentication is not required.

