# Stage 1: Build
FROM maven:3.9-eclipse-temurin-21 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src

RUN mvn -B -DskipTests package 

FROM eclipse-temurin:21-jre-alpine
WORKDIR /app

ARG JAR_FILE_NAME="target/ConfigServer-0.0.1-SNAPSHOT.jar"

COPY --from=build /app/${JAR_FILE_NAME} app.jar

EXPOSE 8888 
ENTRYPOINT ["java","-jar","/app.jar"]