# build del proyecto con Maven y Java 17
FROM maven:3.9.4-eclipse-temurin-17 AS builder

WORKDIR /app

COPY pom.xml .
COPY src ./src

RUN mvn clean package -DskipTests

# imagen liviana
FROM eclipse-temurin:17-jdk-alpine

WORKDIR /app

COPY --from=builder /app/target/*.jar app.jar

# Expone el puerto
EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
