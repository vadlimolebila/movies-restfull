# Movie-RESTFull API

# Movie-RESTFull API is example of Spring-Boot-REST-JPA-MySQL (Movies-RestFull)

### 1. You can clone it from github by running following command

```
  $ git clone https://github.com/vadlimolebila/movies-restfull.git
```

### 2. Import project into IntelliJ IDE
```
  File -> Open -> Browse Project from cloned location
```
### 3. Import src/main/java/resources/database/movies.sql into MySQL database

### 4. Execute Maven clean install package by running the following command
```
  $ mvn clean install package
```

### 5. Update database credential and other configuration into application.properties available in src/main/java/resources
```
spring:
  application:
    name: movies-restfull-application
  datasource:
    url: jdbc:mysql://localhost/be_employer?useSSL=false
    driver-class-name: com.mysql.jdbc.Driver
    username: root
    password: password
  jpa:
    hibernate:
      ddl-auto: update
server:
  port: 8080
```
### 6. Right click on Application.java file and run as Java Application

## Once Sprint Boot Application will be started successfully then we
can call following Endpoints by using POSTMAN

### 7. To get list of movies call following endpoint with GET Request
```
  http://localhost:8080/movies
```
### 8.To Create New Movie use following url with POST Request
```
  http://localhost:8080/movies
```
### set content type as in header as `application/json`
### set request body as raw with JSON payload
```
  {
    "title": "movie 1",
    "description": "description movie 1",
    "rating": 7.0,
    "image": "image 1",
    "createdAt": "2023-07-21 23:02:17",
    "updatedAt": "2023-07-21 23:02:17"
  }
```
### 9.To get a particular movie, use following url with `GET` request type in postman
```
  http://localhost:8080/movies/<id>
```
### 10.To update Movie in database, use following url with `PUT` request type in postman
```
	http://localhost:8080/movies/<id>
```
### set content type as in header as `application/json`
### set request body as raw with JSON payload

```
 {
    "title": "movie 1",
    "description": "description movie 1",
    "rating": 7.0,
    "image": "image 1",
    "createdAt": "2023-07-21 23:02:17",
    "updatedAt": "2023-07-21 23:02:17"
  }
```
### 11.To delete a particular Movie from database, use following url with `DELETE` request type in postman
```
  http://localhost:8080/movies/<id>
```

### Note - Replace <id> with actual id 
