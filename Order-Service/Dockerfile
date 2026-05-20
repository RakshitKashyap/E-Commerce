# --- Stage 1: Build (The "Kitchen") ---
# We use the standard Maven image which supports both Mac and Intel
FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /app

# Copy pom and fetch dependencies (cached layer)
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Copy source and build
COPY src ./src
RUN mvn clean package -DskipTests

# --- Stage 2: Run (The "Delivery") ---
# Swapping to Amazon Corretto 17 - it supports your Mac chip natively
FROM amazoncorretto:17-alpine
WORKDIR /app

# Copy the JAR from the build stage
COPY --from=build /app/target/*.jar app.jar

EXPOSE 8000

ENTRYPOINT ["java", "-jar", "app.jar"]