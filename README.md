# REST API for City, Country, Continent
REST API sample implementation for city, country and continent using Spring Boot framework
## Features
- Docker based
- Single jar implementation
- In-memory H2 database
- JWT token for api authentication 
- Robot Test suite, Html Test Reports
## API Documentation
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
## Docker
- Docker compose command: ```docker-compose up -d```
## System Requirements
- Docker latest version to build image and run the container
- JDK/JRE 1.8 with JAVA_HOME set for compilation
- Apache Maven 3.3 for compiling Spring Boot application 
- Python 3.0 with Robot library 
