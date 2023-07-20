#
# Build stage
#
FROM maven:3.8.5-openjdk-17 AS build
COPY . .
RUN mvn clean package -DskipTests

#
# Package stage
#
FROM openjdk:17-jdk-alpine
COPY --from=build target/pokedex-0.0.1-SNAPSHOT.jar pokedex.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","pokedex.jar"]