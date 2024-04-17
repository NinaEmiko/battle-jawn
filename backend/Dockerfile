# Use an appropriate base image
FROM maven:3.8.1-openjdk-17-slim AS builder

# Set the working directory in the container
WORKDIR /app/backend

# Copy the Maven project file
COPY pom.xml .

# Copy the rest of the project
COPY . .

# Build the application
RUN mvn clean package

# Use a lightweight base image for the final container
FROM openjdk:17-jdk-slim

# Set the working directory in the final container
WORKDIR /app

# Copy the built JAR file from the builder stage
COPY --from=builder /app/backend/target/Battle-Jawn-1.0.0.jar .

# Command to run the application
CMD ["java", "-jar", "Battle-Jawn-1.0.0.jar"]