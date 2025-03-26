FROM openjdk:21-slim
WORKDIR /app

# Copy the executable jar file.
COPY build/libs/agora-backend-0.0.1-SNAPSHOT.jar .

# Copy the .env file into the container.
COPY .env .

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "agora-backend-0.0.1-SNAPSHOT.jar"]