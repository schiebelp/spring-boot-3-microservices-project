# Start with lightweight base image
FROM alpine:3.20.1

RUN apk --no-cache --update add openjdk21-jre

# Define jar app
ARG JAR_FILE=build/libs/product-service-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} app.jar

# Start app on container startup
ENTRYPOINT ["java", "-jar", "app.jar"]