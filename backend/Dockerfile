
FROM openjdk:17-jdk-slim

WORKDIR /app

COPY ./build/libs/backend-0.0.1.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
