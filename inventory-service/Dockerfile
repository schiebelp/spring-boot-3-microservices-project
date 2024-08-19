# Start with lightweight base image
FROM alpine:3.20.1

## Install OpenJDK 17
RUN apk add --no-cache openjdk17

# Define jar app
ARG JAR_FILE=build/libs/inventory-service-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} app.jar

# Start app on container startup
ENTRYPOINT ["java", "-jar", "app.jar"]