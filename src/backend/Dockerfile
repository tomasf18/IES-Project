# Stage 1: Build the application
FROM maven:3.8.1-openjdk-17-slim AS build
WORKDIR /app
COPY ./core-app/pom.xml .
RUN mvn dependency:go-offline
COPY ./core-app/src src
RUN mvn clean package -DskipTests

# Stage 2: Start the application
FROM openjdk:17-jdk-alpine
WORKDIR /app
COPY --from=build /app/target/core-app-0.0.1-SNAPSHOT.jar /app/core-app.jar
EXPOSE 8080
CMD ["java", "-jar", "core-app.jar"]

