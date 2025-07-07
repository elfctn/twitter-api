
FROM maven:3.8.5-openjdk-17 AS build


COPY . .


RUN mvn clean install



FROM openjdk:17-jdk-slim


COPY --from=build /target/twitterapi-0.0.1-SNAPSHOT.jar app.jar


EXPOSE 3000


ENTRYPOINT ["java","-jar","/app.jar"]
