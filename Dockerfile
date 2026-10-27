# Multi-stage Dockerfile for Smart Travel Assistance Spring Boot Backend
# Java Version: 17 | Spring Boot: 3.2.5

# -------------------------------------------------------------
# Stage 1: Build the executable JAR using Maven & Temurin JDK 17
# -------------------------------------------------------------
FROM maven:3.9.6-eclipse-temurin-17-alpine AS builder

WORKDIR /build

# Pre-fetch dependencies to leverage Docker layer caching
COPY pom.xml .
RUN mvn dependency:go-offline -B || true

# Copy source code and build production jar
COPY src ./src
RUN mvn clean package -DskipTests

# -------------------------------------------------------------
# Stage 2: Minimal, secure production runtime using Temurin JRE 17
# -------------------------------------------------------------
FROM eclipse-temurin:17-jre-alpine

# Run as a dedicated non-root user for container security
RUN addgroup -S spring && adduser -S spring -G spring

WORKDIR /app

# Copy the built JAR from builder stage
COPY --from=builder --chown=spring:spring /build/target/*.jar /app/app.jar

# Set container environment defaults
ENV PORT=8080 \
    JAVA_OPTS="-XX:+UseContainerSupport -XX:MaxRAMPercentage=75.0 -Djava.security.egd=file:/dev/./urandom"

# Default container listening port (Render will override via PORT environment variable)
EXPOSE 8080

USER spring:spring

# Execute Spring Boot application listening on $PORT
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -Dserver.port=${PORT} -jar /app/app.jar"]
