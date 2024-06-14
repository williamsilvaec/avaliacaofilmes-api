FROM maven:3.9.7 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package

FROM openjdk:21-jdk-slim

WORKDIR /app
COPY --from=build /app/target/avaliacaofilmes-api.jar .
EXPOSE 8080
CMD ["java", "-jar", "avaliacaofilmes-api.jar"]