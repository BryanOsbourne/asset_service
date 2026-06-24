FROM eclipse-temurin:26-jdk-jammy AS builder

WORKDIR /app

COPY pom.xml .
COPY src ./src

RUN mvn clean package

FROM eclipse-temurin:26-jdk-jammy

WORKDIR /app

COPY --from=builder /app/target/*.jar assets-service-1.0.0.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "assets-service-1.0.0.jar"]