# Start with lightweight base image
FROM alpine:3.20.1

## Install OpenJDK 17
RUN apk add --no-cache openjdk17

# Copy the JAR file
COPY build/libs/inventory-service-0.0.1-SNAPSHOT.jar app1.jar

# Copy flyway migrations
ADD src/main/resources/db/migration/ db/migration/

# Start app on container startup
ENTRYPOINT ["java", "-jar", "app1.jar"]