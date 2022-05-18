# REST API for City, Country, Continent
REST API sample implementation for city, country and continent using Spring Boot framework
## Features
- Docker image
- JSON request/response
- Single jar implementation (bundled Tomcat web-server)
- In-memory H2 database
- JWT token for api authentication 
- Robot Test suite, Html Test Reports
## API Documentation
### Authentication
Authentication using JWT Tokens. Token is valid for 60 minutes.
- POST		/auth

Sample POST Body:
```
{
    "username": "admin",
    "password":"admin"
}
```
Sample Output:
```
{
    "jwt": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbiIsImlhdCI6MTY1MjkwMDc4MywiZXhwIjoxNjUyOTA0MzgzfQ.8GN0iyDe0G2VPcyCA_3z5PMUDcWeytOiTJFk_x3k3bI"
}
```
### City
- GET 		/v1/cities/{id}
- GET 		/v1/cities/
- POST 		/v1/cities/
- PUT 		/v1/cities/
- DELETE 	/v1/cities/{id}
- GET		/v1/cities/continent/{id}
- GET		/v1/cities/country/{id}
### Continent
- GET		/v1/continents/
- GET		/v1/continents/{id}
- POST		/v1/continents/
- PUT		/v1/continents/
- DELETE	/v1/continents/{id}
### Country
- GET		/v1/countries/
- GET		/v1/countries/{id}
- POST		/v1/countries/
- PUT		/v1/countries/
- DELETE	/v1/countries/{id}
## Compiling Application
- Spring Boot application is compiled using the Maven tool by following command:
  ```
  mvn clean
  mvn install
  ```
- target/ folder contains the compiled application jar
## Docker
- Docker compose command: `docker-compose up -d`
## H2 Database Console
- H2 database console can be accessed from the Browser http://localhost:8080/h2-console/
## System Requirements
- Docker latest version to build image and run the container
- JDK/JRE 1.8 with JAVA_HOME set for compilation
- Apache Maven 3.3 for compiling Spring Boot application 
- Python 3.0 with Robot library 
