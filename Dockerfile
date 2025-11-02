FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /app

COPY pom.xml .
COPY src ./src

RUN mvn clean package -DskipTests

FROM eclipse-temurin:17-jdk
WORKDIR /app

COPY --from=build /app/target/quarkus-app /app/

ENV PORT=10000
EXPOSE 10000

CMD ["java", "-jar", "quarkus-run.jar"]