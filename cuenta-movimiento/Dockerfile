# Construcción del proyecto
FROM maven:3.9.4-eclipse-temurin-17 AS builder

# Crear directorio de trabajo en la imagen
WORKDIR /app

# Copiar archivos del proyecto
COPY pom.xml .
COPY src ./src

# Compilar el proyecto
RUN mvn clean package -DskipTests

#Imagen liviana de ejecución
FROM eclipse-temurin:17-jdk-alpine

# Crear directorio donde se ejecutará la app
WORKDIR /app

# Copiar el .jar desde la etapa anterior
COPY --from=builder /app/target/*.jar app.jar

# Puerto expuesto
EXPOSE 8081

# Comando para ejecutar la app
ENTRYPOINT ["java", "-jar", "app.jar"]
