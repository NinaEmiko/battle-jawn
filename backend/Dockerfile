# Use the base image with Maven and JDK
FROM maven:3.8.1-openjdk-17-slim AS builder

# Set the working directory in the container
WORKDIR /app

# Copy the backend project files to the container
COPY ./battlejawn/backend/ ./backend/

# Build the backend project
RUN mvn -f ./backend/pom.xml clean package -DskipTests

# Set up the final image
FROM openjdk:17-jdk-slim

# Set the working directory in the container
WORKDIR /app

# Copy the built JAR file from the builder stage
COPY --from=builder /app/battlejawn/backend/target/Battle-Jawn-1.0.0.jar .

# Expose the port the app runs on
EXPOSE 8080

# Command to run the application
CMD ["java", "-jar", "Battle-Jawn-1.0.0.jar"]