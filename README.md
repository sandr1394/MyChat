# MyChat

Web chat which allows to communicate in real time. 

### Technologies

***Front-End***: 
- So far in development

***Back-End***:

- Spring Boot
- PostgreSQL
- Hibernate
- Lombok
- Swagger
- log4j

### How to run
```sh
To launch class Application.java in src folder. The application starts on localhost port 8085. 
After launching swagger gives access to web page by link http://localhost:8085/swagger-ui.html,
where all application's api are described.
```

### Database description

Postgre database is initialized with some sample user. Properties of DB connection are described in application.yml.   
The model of entities interaction is presented below:
![chat](https://user-images.githubusercontent.com/46102529/56516875-c6079a00-6565-11e9-9cab-2e8382a140a4.jpg)

### Additional information
The application contains validation of input data with javax.validation and logging with log4j. 
All logs are available in file "Logs" in the project root.
