# Etapa de build com Maven
FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /app
COPY . .
RUN mvn clean install -DskipTests

# Etapa final com apenas o JDK
FROM eclipse-temurin:17-jdk
WORKDIR /app
COPY --from=build /app/target/urna-0.0.1-SNAPSHOT.jar app.jar
CMD ["java", "-jar", "app.jar"]
