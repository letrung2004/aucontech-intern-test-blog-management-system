# Build stage
FROM maven:3-openjdk-17 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# Run stage
FROM openjdk:17-jdk-slim
WORKDIR /app
COPY --from=build /app/target/aucontech-intern-test-blog-management-system-0.0.1-SNAPSHOT.war aucontech-intern-test-blog-management-system.war
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "aucontech-intern-test-blog-management-system.war"]