FROM ubuntu:latest AS build

RUN apt-get update
RUN apt-get update && apt-get install -y openjdk-17-jdk maven

COPY . .

RUN mvn clean install -DskipTests

FROM openjdk:17-jdk-slim

WORKDIR /app

EXPOSE 8080

COPY --from=build /target/*.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]