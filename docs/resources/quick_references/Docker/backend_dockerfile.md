# Dockerfile for the Spring Boot application explained

## Stage 1: Build the application
1. Use the maven image as the base image, so then we can build the application, using `mvn`.
```dockerfile
FROM maven:3.8.1-openjdk-17-slim AS build 
```

2. Set the working directory inside the container to /app.
```dockerfile
WORKDIR /app
```

3. Copy the pom.xml file to the working directory.
```dockerfile
COPY ./core-app/pom.xml.
```

4. Download the dependencies (if any) specified in the pom.xml file.
```dockerfile
RUN mvn dependency:go-offline
```

5. Copy the source code to the working directory.
```dockerfile
COPY ./core-app/src src
```

6. Build the application, skipping the tests.
```dockerfile
RUN mvn clean package -DskipTests
```

## Stage 2: Start the application
7. Use the openjdk image as the base image, so then we can run the application, using `java`.
```dockerfile
FROM openjdk:17-jdk-alpine
```

8. Set the working directory inside the container to /app.
```dockerfile
WORKDIR /app
```

9. Copy the jar file from the build stage (previous one) to the working directory.
```dockerfile
COPY --from=build /app/target/core-app-0.0.1-SNAPSHOT.jar /app/core-app.jar
```

10. Expose the port 8080. This means that the container will listen on port 8080, this is, the port where the application will be available/accessible on the host machine.
- Here, it is not necessary, since we are already creating the port translation in the `compose.yml` file.
- 
```dockerfile
EXPOSE 8080
```

11. Inside the container, start the application.
```dockerfile
CMD ["java", "-jar", "core-app.jar"]
```

