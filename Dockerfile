FROM eclipse-temurin:17-jer-alpine

COPY build/libs/*.jar app.jar

ENTRYPOINT ["java", "-jar", "/app.jar"]